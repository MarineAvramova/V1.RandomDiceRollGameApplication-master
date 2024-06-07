package com.itacademy.RandomDiceRollGameApplication.model.repository;

import com.itacademy.RandomDiceRollGameApplication.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByName (String name);
    //Optional<User> findUserByEmail(String userEmail);
    boolean existsByNameIgnoreCase (String name);
}

