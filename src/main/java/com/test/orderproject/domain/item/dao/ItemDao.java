package com.test.orderproject.domain.item.dao;

import com.test.orderproject.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<Item, Long>, ItemCustomDao {
}
