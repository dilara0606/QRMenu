package com.QRMenu.menu.dto;

import com.QRMenu.menu.entity.CategoriesItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    Integer id;
    String name;
    String imageUrl;
    LocalDate createdAt;
    LocalDate updatedAt;
    List<CategoriesItemDto> categoriesItemDtoList;
}
