package com.test.orderproject.domain.order.dao;

import com.test.orderproject.Utils;
import com.test.orderproject.domain.item.domain.Item;
import com.test.orderproject.domain.member.domain.User;
import com.test.orderproject.domain.order.domain.Order;
import com.test.orderproject.domain.order.domain.OrderStatusEnum;
import com.test.orderproject.global.config.QueryDslConfig;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import(QueryDslConfig.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("OrderDao 테스트")
@Slf4j
class OrderDaoTest {

    @Autowired
    OrderDao orderDao;

    @Test
    @DisplayName("save")
    public void save() {
        // given
        User user = Utils.createUser("테스트 이메일", "테스트 비밀번호");
        Item item = Utils.createItem("테스트 아이템 이름", 10000L, 10L);
        Order order = Utils.createOrder(user, item);

        // when
        Order saveOrder = orderDao.save(order);

        // then
        Assertions.assertThat(saveOrder.getId()).isEqualTo(order.getId());
        Assertions.assertThat(saveOrder.getStatus()).isEqualTo(OrderStatusEnum.BEFORE_PAYMENT);
    }
}