package com.test.orderproject.domain.member.api;

import com.test.orderproject.domain.member.dao.UserDao;
import com.test.orderproject.domain.member.domain.User;
import com.test.orderproject.domain.member.dto.LoginRequest;
import com.test.orderproject.domain.member.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthApi {

    private final UserDao userDao;

    @PostMapping("/signup")
    public Long signup(@RequestBody SignUpRequest request){
        User user = User.builder()
                        .email(request.getEmail())
                                .password(request.getPassword())
                                        .build();
        userDao.save(user);
        return user.getId();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){
        User user = userDao.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok(user.getId());
    }
}
