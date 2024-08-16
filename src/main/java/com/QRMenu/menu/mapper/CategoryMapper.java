package com.QRMenu.menu.mapper;

import com.QRMenu.menu.dto.CategoryDto;
import com.QRMenu.menu.dto.ItemDto;
import com.QRMenu.menu.entity.Category;
import com.QRMenu.menu.entity.Item;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    
    public static CategoryDto convert(Category category)
    {
        String mediaPath = category.getImageUrl();
        String mediaUrl = convertToLocalUrl(mediaPath);

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .imageUrl(mediaUrl)
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .categoriesItemDtoList(CategoriesItemMapper.convertListWithoutCategoryDto(category.getCategoriesItemList()))
                .build();
    }

    public static CategoryDto convertWithoutCategoriesItem(Category category)
    {
        String mediaPath = category.getImageUrl();
        String mediaUrl = convertToLocalUrl(mediaPath);

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .imageUrl(mediaUrl)
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    private static String convertToLocalUrl(String mediaPath) {
        Path imagePath = Paths.get(mediaPath);
        Path desktopPath = Paths.get("C:/Users/Dilara/Desktop");
        Path relativePath = desktopPath.relativize(imagePath);
        String relativePathStr = relativePath.toString().replace("\\", "/");
        return "http://localhost:8088/api/v1/images/" + relativePathStr;
    }
    
   public static List<CategoryDto> ConvertList(List<Category> categoryList)
   {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category:categoryList)
        {
            categoryDtoList.add(convert(category));
        }
        return categoryDtoList;
   }
}
