package com.QRMenu.menu.repository;

import com.QRMenu.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    
    Menu findByid(Integer id);
    Optional<Menu> findByIsActiveTrue();
}
