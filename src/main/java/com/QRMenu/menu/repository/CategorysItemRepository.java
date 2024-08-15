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
    @Query("DELETE FROM CategoriesItem ic WHERE ic.item.id = :itemId AND ic.category.id = :categoryId")
    void deleteByItemIdAndCategoryId(Integer itemId, Integer categoryId);;

    List<CategoriesItem> findByItemId(Integer itemId);

    void deleteByItemId(Integer id);

    void deleteByCategoryId(Integer id);
}
