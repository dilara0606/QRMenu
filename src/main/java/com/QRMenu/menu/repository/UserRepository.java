package com.QRMenu.menu.repository;

import com.QRMenu.menu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);

    Boolean existsByEmail(String email);

    User findByemail(String email);

    User findByid (Integer id);

}
