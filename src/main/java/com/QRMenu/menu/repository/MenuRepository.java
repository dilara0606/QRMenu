package com.QRMenu.menu.repository;

import com.QRMenu.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    
    Menu findByid(Integer id);
}
