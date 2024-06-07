package com.itacademy.RandomDiceRollGameApplication.controllers;

import com.itacademy.RandomDiceRollGameApplication.model.dto.PlayerDto;
import com.itacademy.RandomDiceRollGameApplication.model.services.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
@Slf4j
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/add")
    public ResponseEntity<PlayerDto> registerPlayer(@RequestBody PlayerDto playerDto) {
        log.info("Received request to register player: {}", playerDto.getName());
        PlayerDto newPlayer = playerService.registerPlayer(playerDto);//, authentication
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{playerID}")
    public ResponseEntity<PlayerDto> updatePlayer(@PathVariable Long playerID, @RequestBody PlayerDto playerDto) {
        log.info("Received request to update player with ID: {}", playerID);
        PlayerDto playertoUpdate = playerService.updatePlayer(playerID, playerDto);
        return new ResponseEntity<>(playertoUpdate, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
     public ResponseEntity<List<PlayerDto>> getAllPlayers() {
    log.info("Received request to fetch all players");
    List<PlayerDto> searchedPlayers = playerService.getAllPlayers();
    return ResponseEntity.ok(searchedPlayers);
}

    @GetMapping("/average-success")
    public ResponseEntity<Double> getAverageSuccessPercentage() {
        log.info("Received request to fetch average success percentage");
        double averageSuccessPercentage = playerService.calculateAverageSuccessPercentage();
        return ResponseEntity.ok(averageSuccessPercentage);
    }
}
