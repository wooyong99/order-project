package com.test.orderproject.domain.member.domain;

import com.test.orderproject.domain.member.model.BaseEntity;
import com.test.orderproject.domain.order.domain.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@ToString
public class User extends BaseEntity {

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST,
            CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Order> orders = new ArrayList<>();
    private String email;
    private String password;

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
