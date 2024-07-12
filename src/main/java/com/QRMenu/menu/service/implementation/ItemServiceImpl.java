package com.QRMenu.menu.service.implementation;

import com.QRMenu.menu.entity.Item;
import com.QRMenu.menu.repository.ItemRepository;
import com.QRMenu.menu.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Override
    public void saveItem(Item item) {
        item.setCreatedAt(LocalDate.now());
        itemRepository.save(item);
    }
}
