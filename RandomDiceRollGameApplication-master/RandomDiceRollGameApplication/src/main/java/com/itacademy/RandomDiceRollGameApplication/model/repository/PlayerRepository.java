package com.itacademy.RandomDiceRollGameApplication.model.repository;

import com.itacademy.RandomDiceRollGameApplication.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByName(String name);
    Boolean existsByName(String name);

}
