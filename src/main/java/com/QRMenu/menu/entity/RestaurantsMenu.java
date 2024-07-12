package com.QRMenu.menu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "restaurant_menu")
public class RestaurantsMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    Menu menu;
}
