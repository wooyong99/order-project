package com.test.orderproject.domain.member.application;

import com.test.orderproject.domain.member.dao.UserDao;
import com.test.orderproject.domain.member.domain.User;
import com.test.orderproject.domain.member.dto.LoginRequest;
import com.test.orderproject.domain.member.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDao userDao;

    @Transactional
    public void signup(SignUpRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userDao.save(user);
    }

    public void login(LoginRequest request) {
        userDao.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
