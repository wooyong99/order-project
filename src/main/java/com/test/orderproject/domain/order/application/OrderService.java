package com.test.orderproject.domain.order.application;

import com.test.orderproject.domain.item.dao.ItemDao;
import com.test.orderproject.domain.item.domain.Item;
import com.test.orderproject.domain.member.dao.UserDao;
import com.test.orderproject.domain.member.domain.User;
import com.test.orderproject.domain.order.dao.OrderDao;
import com.test.orderproject.domain.order.domain.Order;
import com.test.orderproject.domain.order.dto.OrderCreateRequest;
import jakarta.transaction.Transactional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final int MERCHANTUI_LENGTH = 6;
    private static final Random rnd = new Random();
    private final UserDao userDao;
    private final ItemDao itemDao;
    private final OrderDao orderDao;

    // 주문 생성
    @Transactional
    public String save(long itemId, OrderCreateRequest request) {
        String merchantUid = createMerchantUid();

        User user = userDao.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        Item item = itemDao.findById(itemId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        Order order = Order.builder()
                .user(user)
                .item(item)
                .price(request.getPrice())
                .merchantUid(merchantUid)
                .build();

        validateStock(item.getStock());

        Order saveOrder = orderDao.save(order);
        return saveOrder.getMerchantUid();
    }

    private void validateStock(Long stock) {
        if (stock < 1) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
    }

    private String createMerchantUid() {
        StringBuilder merchantUid = new StringBuilder("");
        for (int i = 0; i < MERCHANTUI_LENGTH; i++) {
            merchantUid.append(rnd.nextInt(0, 10));
        }
        return merchantUid.toString();
    }
}
