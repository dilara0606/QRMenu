package com.QRMenu.menu.repository;

import com.QRMenu.menu.entity.CategoriesItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorysItemRepository extends JpaRepository<CategoriesItem, Integer> {

    List<CategoriesItem> findByCategoryId(Integer categoryId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CategoriesItem ic WHERE ic.item = :itemId AND ic.category = :categoryId")
    void deleteByItemIdAndCategoryId(Integer itemId, Integer categoryId);;
}
