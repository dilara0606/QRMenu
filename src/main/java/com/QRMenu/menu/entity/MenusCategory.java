package com.QRMenu.menu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "menus_category")
public class MenusCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    Menu menu;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;
}
