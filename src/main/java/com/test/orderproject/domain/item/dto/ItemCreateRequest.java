package com.test.orderproject.domain.item.dto;


import com.test.orderproject.domain.item.domain.Item;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private Long price;

    @NotBlank
    private Long stock;

    public Item toEntity() {
        return Item.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }
}
