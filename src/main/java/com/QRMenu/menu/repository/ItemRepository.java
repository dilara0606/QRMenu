package com.QRMenu.menu.repository;

import com.QRMenu.menu.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByid(Integer itemId);
}
