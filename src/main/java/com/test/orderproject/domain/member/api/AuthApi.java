package com.test.orderproject.domain.member.api;

import com.test.orderproject.domain.member.application.AuthService;
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

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignUpRequest request){

        authService.signup(request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){

        authService.login(request);

        return ResponseEntity.ok().build();
    }
}
