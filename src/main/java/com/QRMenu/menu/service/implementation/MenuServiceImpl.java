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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    @Value("${server.upload.directory}")
    private String uploadDir;
    private final MenuRepository repository;

    @Override
    public void saveMenu(Menu menu) {

        menu.setActive(false);
        menu.setCreatedAt(LocalDate.now());
        menu.setUpdatedAt(LocalDate.now());

        String imageUrl = menu.getImageUrl();

        if (imageUrl != null) {
            if (imageUrl.startsWith("data:image/")) {
                // Base64 formatındaki resmi işleme
                String[] parts = imageUrl.split(",");
                String imageString = parts.length > 1 ? parts[1] : ""; // Check if parts array has at least 2 elements

                String fileName = "menu_image_" + System.currentTimeMillis() + ".jpg";
                String filePath = uploadDir + "/" + fileName;

                try (OutputStream outputStream = new FileOutputStream(filePath)) {
                    byte[] imageBytes = Base64.getDecoder().decode(imageString);
                    outputStream.write(imageBytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                menu.setImageUrl(filePath);
            } else {
                // URL formatındaki resmi işleme
                String fileName = "menu_image_" + System.currentTimeMillis() + ".jpg";
                String filePath = uploadDir + "/" + fileName;

                try (InputStream inputStream = new URL(imageUrl).openStream();
                     OutputStream outputStream = new FileOutputStream(filePath)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                menu.setImageUrl(filePath);
            }
        }

        repository.save(menu);
    }

    @Override
    public MenuDto activateMenu(Integer id) {
        List<Menu> allMenus = repository.findAll();
        for (Menu menu : allMenus) {
            menu.setActive(false);
        }
        repository.saveAll(allMenus);

        Menu menuToActivate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found with id: " + id));

        menuToActivate.setActive(true);

        return MenuMapper.convert(repository.save(menuToActivate));
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

        String imageUrl = menu.getImageUrl();

        if (imageUrl != null) {
            if (imageUrl.startsWith("data:image/")) {
                // Base64 formatındaki resmi işleme
                String[] parts = imageUrl.split(",");
                String imageString = parts.length > 1 ? parts[1] : ""; // Check if parts array has at least 2 elements

                String fileName = "menu_image_" + System.currentTimeMillis() + ".jpg";
                String filePath = uploadDir + "/" + fileName;

                try (OutputStream outputStream = new FileOutputStream(filePath)) {
                    byte[] imageBytes = Base64.getDecoder().decode(imageString);
                    outputStream.write(imageBytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                oldMenu.setImageUrl(filePath);
            } else {
                // URL formatındaki resmi işleme
                String fileName = "menu_image_" + System.currentTimeMillis() + ".jpg";
                String filePath = uploadDir + "/" + fileName;

                try (InputStream inputStream = new URL(imageUrl).openStream();
                     OutputStream outputStream = new FileOutputStream(filePath)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                oldMenu.setImageUrl(filePath);
            }
        }

            //fronttan namei bak!
            oldMenu.setUpdatedAt(LocalDate.now());
            oldMenu.setName(menu.getName());
            oldMenu.setDescription(menu.getDescription());

            return MenuMapper.convert(repository.save(oldMenu));

    }

    @Override
    public MenuDto getActiveMenu() {
        Menu activeMenu = repository.findByIsActiveTrue()
                .orElseThrow(() -> new NoSuchElementException("Active menu not found"));
        return MenuMapper.convert(activeMenu);
    }

    @Override
    public List<MenuDto> getAll() {
        return MenuMapper.convertList(repository.findAll());
    }

}
