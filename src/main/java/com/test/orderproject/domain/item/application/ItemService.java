package com.test.orderproject.domain.item.application;


import com.test.orderproject.domain.item.dao.ItemDao;
import com.test.orderproject.domain.item.domain.Item;
import com.test.orderproject.domain.item.dto.ItemCreateRequest;
import com.test.orderproject.domain.item.dto.ItemDetailResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemDao itemDao;

    public Long save(ItemCreateRequest request) {
        Item item = request.toEntity();
        Item saveItem = itemDao.save(item);
        return saveItem.getId();
    }

    public ItemDetailResponse findItem(Long itemId) {
        Item item = itemDao.findById(itemId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        ItemDetailResponse response = item.toResponseDto();
        return response;
    }

    public List<ItemDetailResponse> findItemList(Pageable pageable) {
        return itemDao.findAll(pageable).stream().map(item -> item.toResponseDto()).toList();
    }
}
