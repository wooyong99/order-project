package com.test.orderproject.domain.payment.api;

import com.test.orderproject.domain.payment.application.PaymentService;
import com.test.orderproject.domain.payment.dto.PaymentValidateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentApi {

    private final PaymentService paymentService;

    // 주문 결제 확인
    @PostMapping("/payment/{merchantUid}")
    public ResponseEntity validateMerchantUID(@PathVariable String merchantUid,
                                              @RequestBody PaymentValidateRequest request) {
        paymentService.validatePayment(merchantUid, request);

        return ResponseEntity.ok().build();
    }

    // 주문 결제 취소
    @DeleteMapping("/payment/{merchantUid}")
    public ResponseEntity refundPayment(@PathVariable String merchantUid) {

        paymentService.canclePayment(merchantUid);
        return null;
    }
}
