package com.itacademy.RandomDiceRollGameApplication.model.services;

import com.itacademy.RandomDiceRollGameApplication.model.dto.GameDto;

import java.util.List;

public interface GameService {
    GameDto createGame(Long playerId);
    List<GameDto> getGamesByPlayerId(Long playerId);
    void deleteGames(Long playerId);
}
