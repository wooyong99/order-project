package com.test.orderproject.domain.order.api;

import com.test.orderproject.domain.order.application.OrderService;
import com.test.orderproject.domain.order.dto.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderApi {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping("/items/{itemId}/orders")
    public ResponseEntity getMerchantUID(@PathVariable long itemId, @RequestBody OrderCreateRequest request) {

        String merchantUid = orderService.save(itemId, request);

        return ResponseEntity.ok(merchantUid);
    }
}
