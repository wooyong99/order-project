package com.test.orderproject.domain.item.dao;

import com.test.orderproject.Utils;
import com.test.orderproject.domain.item.domain.Item;
import com.test.orderproject.global.config.QueryDslConfig;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@Import(QueryDslConfig.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("ItemDao 테스트")
@Slf4j
class ItemDaoTest {

    @Autowired
    ItemDao itemDao;

    @Test
    @DisplayName("save 테스트")
    public void save() {
        // given
        Item item = Item.builder()
                .name(Utils.randomUUID())
                .price(Utils.randomLongValue(1000, 10000))
                .stock(Utils.randomLongValue(1, 10)).build();

        // when
        Item saveItem = itemDao.save(item);

        // then
        Assertions.assertThat(saveItem.getId()).isEqualTo(item.getId());
    }

    @Test
    @DisplayName("save 테스트 - 재고가 0 이하일 경우 예외를 발생시킨다.")
    public void shouldThrowException_whenStockIsZeroOrNegative() {
        // given
        Item item = Item.builder()
                .name(Utils.randomUUID())
                .price(Utils.randomLongValue(1000, 10000))
                .stock(0L)
                .build();

        // when

        // then
        Assertions.assertThatThrownBy(() -> itemDao.save(item)).isInstanceOf(InvalidDataAccessApiUsageException.class);
    }

    @Test
    @DisplayName("save 테스트 - 가격이 0 이하일 경우 예외를 발생시킨다.")
    public void shouldThrowException_whenPriceIsZeroOrNegative() {
        // given
        Item item = Item.builder()
                .name(Utils.randomUUID())
                .price(0L)
                .stock(Utils.randomLongValue(1, 10))
                .build();

        // when

        // then
        Assertions.assertThatThrownBy(() -> itemDao.save(item)).isInstanceOf(InvalidDataAccessApiUsageException.class);
    }
}