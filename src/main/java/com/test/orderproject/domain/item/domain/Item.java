package com.test.orderproject.domain.item.domain;


import com.test.orderproject.domain.item.dto.ItemDetailResponse;
import com.test.orderproject.domain.member.model.BaseEntity;
import com.test.orderproject.domain.order.domain.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
@ToString
public class Item extends BaseEntity {

    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST,
            CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Order> orders = new ArrayList<>();
    private String name;
    private Long price;
    private Long stock;

    @Builder
    public Item(String name, Long price, Long stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @PrePersist
    private void validatePriceAndStock() {
        if (price < 1) {
            throw new IllegalArgumentException("상품 가격은 0원 이하로 등록할 수 없습니다.");
        }
        if (stock < 1) {
            throw new IllegalArgumentException("상품 재고는 0 이하로 등록할 수 없습니다.");
        }
    }

    public ItemDetailResponse toResponseDto() {
        return ItemDetailResponse.builder()
                .itemId(getId())
                .name(name)
                .price(price)
                .stock(stock)
                .createAt(getCreatedAt())
                .build();
    }

    public void decreaseStock() {
        this.stock--;
    }
}
