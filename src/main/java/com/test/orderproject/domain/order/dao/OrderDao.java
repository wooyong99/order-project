package com.test.orderproject.domain.order.dao;

import com.test.orderproject.domain.order.domain.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Long>, OrderCustomDao {
    Optional<Order> findByMerchantUid(String merchantUid);
}
