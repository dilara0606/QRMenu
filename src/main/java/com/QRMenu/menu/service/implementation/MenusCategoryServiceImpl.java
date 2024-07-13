package com.QRMenu.menu.service.implementation;

import com.QRMenu.menu.dto.MenusCategoryDto;
import com.QRMenu.menu.entity.Category;
import com.QRMenu.menu.entity.Menu;
import com.QRMenu.menu.entity.MenusCategory;
import com.QRMenu.menu.mapper.MenusCategoryMapper;
import com.QRMenu.menu.repository.CategoryRepository;
import com.QRMenu.menu.repository.MenuRepository;
import com.QRMenu.menu.repository.MenusCategoryRepository;
import com.QRMenu.menu.service.MenusCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenusCategoryServiceImpl implements MenusCategoryService {

    private final MenusCategoryRepository repository;
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public MenusCategoryDto addCategory(Integer menuId, Integer categoryId) {

        Menu menu = menuRepository.findByid(menuId);
        Category category = categoryRepository.findByid(categoryId);

        MenusCategory menusCategory = new MenusCategory();
        menusCategory.setMenu(menu);
        menusCategory.setCategory(category);
        repository.save(menusCategory);

        return MenusCategoryMapper.convert(menusCategory);
    }

    @Override
    public void deleteCategory(Integer menuId, Integer categoryId) {
        repository.deletedBymenuIdAndcategoryId(menuId, categoryId);
    }
}
