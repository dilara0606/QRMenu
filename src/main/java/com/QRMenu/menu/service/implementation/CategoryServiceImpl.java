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
import java.io.OutputStream;
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

        if(category.getImageUrl() != null){
            String base64Image = category.getImageUrl();
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
            oldCategory.setImageUrl(filePath);
        }

        //fronttan namei bak!
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);

        return CategoryMapper.convert(oldCategory);
    }
}
