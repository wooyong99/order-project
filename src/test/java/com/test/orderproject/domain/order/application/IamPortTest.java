package com.test.orderproject.domain.order.application;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class IamPortTest {

    @Autowired
    IamportClient iamportClient;

    @Test
    public void test() throws IamportResponseException, IOException {
        // given
        CancelData data = new CancelData("imp_697022266290", true);
        IamportResponse<Payment> response = iamportClient.cancelPaymentByImpUid(data);
        System.out.println(response.getCode());
        System.out.println(response.getResponse().getAmount());
        System.out.println(response.getResponse().getMerchantUid());

        // when
    }
}
