package com.QRMenu.menu.service.implementation;

import com.QRMenu.menu.dto.CategoryDto;
import com.QRMenu.menu.entity.Category;
import com.QRMenu.menu.mapper.CategoryMapper;
import com.QRMenu.menu.repository.CategoryRepository;
import com.QRMenu.menu.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Value("${server.upload.directory}")
    private String uploadDir;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto saveCategory(Category category) {
        category.setCreatedAt(LocalDate.now());
        category.setUpdatedAt(LocalDate.now());

        String imageUrl = category.getImageUrl();

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
                category.setImageUrl(filePath);
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
                category.setImageUrl(filePath);
            }
        }

        categoryRepository.save(category);
        return CategoryMapper.convert(category);
    }

    @Override
    public List<CategoryDto> getCategories() {
        return CategoryMapper.ConvertList(categoryRepository.findAll());
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto editCategory(Integer id, Category category) {

        Category oldCategory = categoryRepository.findByid(id);

        String imageUrl = category.getImageUrl();

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
                oldCategory.setImageUrl(filePath);
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
                oldCategory.setImageUrl(filePath);
            }
        }

        //fronttan namei bak!
        oldCategory.setName(category.getName());
        oldCategory.setUpdatedAt(LocalDate.now());
        categoryRepository.save(oldCategory);

        return CategoryMapper.convert(oldCategory);
    }
}
