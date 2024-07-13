package com.QRMenu.menu.service.implementation;

import com.QRMenu.menu.dto.MenuDto;
import com.QRMenu.menu.entity.Category;
import com.QRMenu.menu.entity.Item;
import com.QRMenu.menu.entity.Menu;
import com.QRMenu.menu.entity.MenusCategory;
import com.QRMenu.menu.mapper.ItemMapper;
import com.QRMenu.menu.mapper.MenuMapper;
import com.QRMenu.menu.repository.CategoryRepository;
import com.QRMenu.menu.repository.MenuRepository;
import com.QRMenu.menu.repository.MenusCategoryRepository;
import com.QRMenu.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    @Value("${server.upload.directory}")
    private String uploadDir;
    private final MenuRepository repository;

    @Override
    public void saveMenu(Menu menu) {

        menu.setActive(false);
        menu.setCreatedAt(LocalDateTime.now());
        menu.setUpdatedAt(LocalDateTime.now());

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

    @Override
    public void deleteMenu(Integer id) {
       repository.deleteById(id);
    }

    @Override
    public MenuDto editMenu(Integer id, Menu menu) {
        Menu oldMenu = repository.findByid(id);

        if(menu.getImageUrl() != null){
            String base64Image = menu.getImageUrl();
            String[] parts = base64Image.split(",");
            String imageString = parts[1];

            String fileName = "menu_image_" + System.currentTimeMillis() + ".jpg";
            String filePath = uploadDir + "/" + fileName;

            try (OutputStream outputStream = new FileOutputStream(filePath)) {
                byte[] imageBytes = Base64.getDecoder().decode(imageString);
                outputStream.write(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            oldMenu.setImageUrl(filePath);
        }

        //fronttan namei bak!
        oldMenu.setUpdatedAt(LocalDateTime.now());
        oldMenu.setName(menu.getName());
        oldMenu.setDescription(menu.getDescription());

        return MenuMapper.convert(oldMenu);
    }

}
