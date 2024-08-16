package com.test.orderproject.domain.payment.application;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.test.orderproject.domain.item.dao.ItemDao;
import com.test.orderproject.domain.item.domain.Item;
import com.test.orderproject.domain.order.dao.OrderDao;
import com.test.orderproject.domain.order.domain.Order;
import com.test.orderproject.domain.payment.dto.PaymentValidateRequest;
import jakarta.transaction.Transactional;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final IamportClient iamportClient;

    private final OrderDao orderDao;

    private final ItemDao itemDao;

    // 주문 결제 확인
    @Transactional
    public void validatePayment(String merchantUid, PaymentValidateRequest request) {
        Order order = orderDao.findByMerchantUid(merchantUid)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문번호입니다."));

        IamportResponse<Payment> paymentIamportResponse = null;
        try {
            paymentIamportResponse = iamportClient.paymentByImpUid(
                    request.getImpUid());

            if (paymentIamportResponse.getCode() != 0) {
                throw new IllegalArgumentException("결제 내역이 존재하지 않습니다.");
            }

            if (paymentIamportResponse.getResponse().getAmount().longValue() != order.getPrice()) {
                CancelData data = new CancelData(request.getImpUid(), true);
                IamportResponse<Payment> response = iamportClient.cancelPaymentByImpUid(data);
                throw new IllegalArgumentException("결제 금액이 일치하지 않습니다.");
            }
        } catch (IamportResponseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("결제 내역이 존재하지 않습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("결제 내역이 존재하지 않습니다.");
        }
        Item item = order.getItem();
        item.decreaseStock();
        itemDao.save(item);
        order.increaseStatus();
        orderDao.save(order);
    }

    public void canclePayment(String merchantUid) {
        CancelData data = new CancelData(merchantUid, true);
        try {
            // impuid로 수정 예정
            IamportResponse<Payment> response = iamportClient.cancelPaymentByImpUid(data);
        } catch (IamportResponseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
