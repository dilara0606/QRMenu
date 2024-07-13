package com.QRMenu.menu.repository;

import com.QRMenu.menu.entity.MenusCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenusCategoryRepository extends JpaRepository<MenusCategory, Integer> {

    void deletedBymenuIdAndcategoryId(Integer menuId, Integer categoryId);
}
