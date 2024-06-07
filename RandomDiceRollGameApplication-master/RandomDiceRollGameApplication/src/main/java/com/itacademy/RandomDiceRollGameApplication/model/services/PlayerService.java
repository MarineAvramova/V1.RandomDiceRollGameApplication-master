package com.itacademy.RandomDiceRollGameApplication.model.services;

import com.itacademy.RandomDiceRollGameApplication.model.dto.PlayerDto;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface PlayerService {
    PlayerDto registerPlayer(PlayerDto playerDto);
    PlayerDto updatePlayer (Long playerId, PlayerDto playerDto);
    List<PlayerDto> getAllPlayers();

    double calculateSuccessRate(Long playerId);
    double calculateAverageSuccessPercentage();
}
