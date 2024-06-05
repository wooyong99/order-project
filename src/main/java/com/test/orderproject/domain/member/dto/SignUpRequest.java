package com.test.orderproject.domain.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequest {

    private String email;

    private String password;
}
