package com.QRMenu.menu.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String imageUrl;

    @CreatedDate
    LocalDate createdAt;
    @LastModifiedDate
    LocalDate updatedAt;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<CategoriesItem> categoriesItemList;
}
