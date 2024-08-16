package com.test.orderproject.domain.item.api;

import com.test.orderproject.domain.item.application.ItemService;
import com.test.orderproject.domain.item.dto.ItemCreateRequest;
import com.test.orderproject.domain.item.dto.ItemDetailResponse;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemApi {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity saveItem(@RequestBody ItemCreateRequest request) {
        Long itemId = itemService.save(request);
        return ResponseEntity.created(URI.create("/items/" + itemId)).build();
    }

    @GetMapping("/{itemId}")
    public ResponseEntity detailItem(@PathVariable Long itemId) {
        ItemDetailResponse response = itemService.findItem(itemId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity listItem(@PageableDefault Pageable pageable) {
        List<ItemDetailResponse> response = itemService.findItemList(pageable);

        return ResponseEntity.ok(response);
    }
}
