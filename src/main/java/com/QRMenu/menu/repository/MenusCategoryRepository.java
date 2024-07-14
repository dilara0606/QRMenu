package com.QRMenu.menu.repository;

import com.QRMenu.menu.entity.MenusCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenusCategoryRepository extends JpaRepository<MenusCategory, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM MenusCategory ic WHERE ic.menu = :menuId AND ic.category = :categoryId")
    void deleteByMenuIdAndCategoryId(Integer menuId, Integer categoryId);;
}
