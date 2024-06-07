package com.itacademy.RandomDiceRollGameApplication.controllers;

import com.itacademy.RandomDiceRollGameApplication.model.dto.GameDto;
import com.itacademy.RandomDiceRollGameApplication.model.services.GameService;
import com.itacademy.RandomDiceRollGameApplication.model.services.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
@Slf4j
public class GameController {
    private final GameService gameService;
    private final PlayerService playerService;

    @PostMapping("/{id}/games")
    public ResponseEntity<GameDto> createGame(@PathVariable Long id) {//@RequestBody GameDto gameDto
        log.info("Received request to create a new game");
        GameDto newGame = gameService.createGame(id);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    @GetMapping("/{playerId}/success-rate")
    public ResponseEntity<Double> getSuccessRate(@PathVariable Long playerId) {
        log.info("Received request to fetch success rate for player with ID: {}", playerId);
        double successRate = playerService.calculateSuccessRate(playerId);
        return new ResponseEntity<>(successRate, HttpStatus.OK);
    }
    @GetMapping("/{id}/games")
    public ResponseEntity<?>getGames(@PathVariable Long id) {
        log.info("Received request to fetch games for player with ID: {}", id);
        List<GameDto> games = gameService.getGamesByPlayerId(id);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
    @DeleteMapping("/{id}/games")
    public ResponseEntity<?> deleteGames(@PathVariable Long id) {
        log.info("Received request to delete games for player with ID: {}", id);

        gameService.deleteGames(id);
        return new ResponseEntity<>("Games have been deleted", HttpStatus.OK);
    }
}
