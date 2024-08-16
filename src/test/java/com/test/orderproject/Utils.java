package com.test.orderproject;

import com.test.orderproject.domain.item.domain.Item;
import com.test.orderproject.domain.member.domain.User;
import com.test.orderproject.domain.order.domain.Order;
import java.util.Random;
import java.util.UUID;

public class Utils {

    public static Random rnd = new Random();

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public static Long randomLongValue(int start, int end) {
        return rnd.nextLong(start, end);
    }

    public static User createUser(String email, String password) {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }

    public static Item createItem(String name, Long price, Long stock) {
        return Item.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }

    public static Order createOrder(User user, Item item) {
        return Order.builder()
                .user(user)
                .item(item)
                .build();
    }
}
