package com.test.orderproject.domain.order.application;

import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MerchantUidTest {

    Random rnd = new Random();

    @Test
    @DisplayName("6자리 merchantUid를 생성합니다.")
    public void createMerchantUid() {
        int length = 6;
        StringBuilder merchantUid = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            merchantUid.append(rnd.nextInt(0, 10));
        }

        Assertions.assertThat(merchantUid.length()).isEqualTo(length);
        for (char c : merchantUid.toString().toCharArray()) {
            Assertions.assertThat(Character.isDigit(c)).isTrue();
        }
    }
}
