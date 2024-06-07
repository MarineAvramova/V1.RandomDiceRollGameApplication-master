package com.itacademy.RandomDiceRollGameApplication.controllers;

import com.itacademy.RandomDiceRollGameApplication.model.dto.PlayerDto;
import com.itacademy.RandomDiceRollGameApplication.model.services.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players/ranking")
@RequiredArgsConstructor
@Slf4j
public class RankingController {
    private final RankingService rankingService;

    @GetMapping
    public ResponseEntity<Double> getAveragePlayerSuccessRate() {
        log.info("Received request to fetch average player success rate");
        double averageSuccessRate = rankingService.getAveragePlayer();
        return new ResponseEntity<>(averageSuccessRate, HttpStatus.OK);
    }

    @GetMapping("/loser")
    public ResponseEntity<PlayerDto> getLoserPlayer() {
        log.info("Received request to fetch the player with the worst success rate");
        PlayerDto loserPlayer = rankingService.getLoserPlayer();
        return new ResponseEntity<>(loserPlayer, HttpStatus.OK);
    }

    @GetMapping("/winner")
    public ResponseEntity<PlayerDto> getWinnerPlayer() {
        log.info("Received request to fetch the player with the best success rate");
        PlayerDto winnerPlayer = rankingService.getWinnerPlayer();
        return new ResponseEntity<>(winnerPlayer, HttpStatus.OK);
    }
}
