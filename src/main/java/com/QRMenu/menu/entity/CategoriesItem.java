package com.QRMenu.menu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categorys_items")
public class CategoriesItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    Item item;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;
}
