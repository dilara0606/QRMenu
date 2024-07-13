package com.QRMenu.menu.repository;

import com.QRMenu.menu.entity.CategoriesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorysItemRepository extends JpaRepository<CategoriesItem, Integer> {

    List<CategoriesItem> findByCategoryId(Integer categoryId);

    void deletedByitemIdAndcategoryId(Integer itemId, Integer categoryId);
}
