package com.QRMenu.menu.dto;

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
public class MenuDto {

    Integer id;
    String name;
    String description;
    LocalDate createdAt;
    LocalDate updatedAt;
    boolean isActive;
    String imageUrl;
    List<MenusCategoryDto> menusCategoryDtoList;
}
