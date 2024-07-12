package com.QRMenu.menu.service.implementation;

import com.QRMenu.menu.dto.MenuDto;
import com.QRMenu.menu.entity.Category;
import com.QRMenu.menu.entity.Menu;
import com.QRMenu.menu.entity.MenusCategory;
import com.QRMenu.menu.mapper.MenuMapper;
import com.QRMenu.menu.repository.CategoryRepository;
import com.QRMenu.menu.repository.MenuRepository;
import com.QRMenu.menu.repository.MenusCategoryRepository;
import com.QRMenu.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    
    private final MenuRepository repository;
    private final CategoryRepository categoryRepository;
    private final MenusCategoryRepository menusCategoryRepository;

    @Override
    public void saveMenu(Menu menu) {
        menu.setActive(false);
        menu.setCreatedAt(LocalDateTime.now());
        repository.save(menu);
    }

    @Override
    public MenuDto activateMenu(Integer id) {
        Menu menu = repository.findByid(id);
        
        if (!menu.isActive()){
            menu.setActive(true); 
        }
        
        repository.save(menu);
        return MenuMapper.convert(menu);
    }

    @Override
    public MenuDto deactivateMenu(Integer id) {
        Menu menu = repository.findByid(id);

        if (menu.isActive()){
            menu.setActive(false);
        }

        repository.save(menu);
        return MenuMapper.convert(menu);
    }

}
