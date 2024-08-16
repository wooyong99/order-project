package com.test.orderproject.domain.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.h2.util.json.JSONObject;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCancleRequest {

    private String code;

    private String message;

    private JSONObject response;
}
