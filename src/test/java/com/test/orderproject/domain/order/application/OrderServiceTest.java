package com.test.orderproject.domain.order.application;

import com.test.orderproject.domain.member.domain.User;
import com.test.orderproject.domain.order.dto.OrderCreateRequest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    public void test() {
        // given
        User user  = null;
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        OrderCreateRequest orderCreateRequest = OrderCreateRequest.builder()
                .userId(user.getId())
                .price(1000)
                .build();

        IntStream.rangeClosed(1,threadCount)
                .forEach(() -> {
                    executorService.submit(() -> {
                        orderService.save()
                    })
                });

        // when

        // then
    }

}