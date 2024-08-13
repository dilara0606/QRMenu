package com.QRMenu.menu.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String description;

    @CreatedDate
    LocalDate createdAt;
    @LastModifiedDate
    LocalDate updatedAt;

    boolean isActive;
    String imageUrl;

    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
    private List<MenusCategory> menusCategoryList;
}
