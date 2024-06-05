package com.test.orderproject.domain.member.domain;

import com.test.orderproject.domain.member.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    private String email;

    private String password;

    @Builder
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}
