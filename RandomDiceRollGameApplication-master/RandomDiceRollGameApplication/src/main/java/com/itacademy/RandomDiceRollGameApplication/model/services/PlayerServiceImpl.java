package com.itacademy.RandomDiceRollGameApplication.model.services;

import com.itacademy.RandomDiceRollGameApplication.exceptions.PlayerAlreadyExists;
import com.itacademy.RandomDiceRollGameApplication.exceptions.PlayerNotFound;
import com.itacademy.RandomDiceRollGameApplication.mapper.GameMapper;
import com.itacademy.RandomDiceRollGameApplication.mapper.PlayerMapper;
import com.itacademy.RandomDiceRollGameApplication.model.domain.Game;
import com.itacademy.RandomDiceRollGameApplication.model.domain.Player;
import com.itacademy.RandomDiceRollGameApplication.model.domain.User;
import com.itacademy.RandomDiceRollGameApplication.model.dto.PlayerDto;
import com.itacademy.RandomDiceRollGameApplication.model.repository.GameRepository;
import com.itacademy.RandomDiceRollGameApplication.model.repository.PlayerRepository;
import com.itacademy.RandomDiceRollGameApplication.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@AllArgsConstructor
@Slf4j

public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    @Override
    public PlayerDto registerPlayer(PlayerDto playerDto) {
      log.info("Registering new player {} to the database", playerDto.getName());

      // String name = authentication.getName();
      // User loggedUser = userRepository.findUserByName(name).orElseThrow();
        if(playerDto.getName() == null || playerDto.getName().isBlank()
                || playerDto.getName().equalsIgnoreCase("ANONYMOUS")){
            playerDto.setName("ANONYMOUS");
           // User loggedUser = userRepository.findUserByName(name).orElseThrow();
        } else if (playerRepository.existsByName(playerDto.getName())){
            throw new PlayerAlreadyExists("Player already exists.");
        }
        Clock clock = Clock.systemDefaultZone();
        Player player = playerMapper.mapToPlayer(playerDto);
        player.setRegisterDate(LocalDateTime.now(clock));

        Player savedPlayer = playerRepository.save(player);
        return playerMapper.mapToPlayerDto(savedPlayer);
    }

    @Override
    public PlayerDto updatePlayer(Long playerId, PlayerDto playerDto) {
        log.info("Updating existing player {} in the database", playerDto.getPlayerId());
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(()-> new PlayerNotFound("Player with this id doesn't exist"));

      existingPlayer.setName(playerDto.getName());
      playerRepository.save(existingPlayer);
        return playerMapper.mapToPlayerDto(existingPlayer);
    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        log.info("Fetching all players ");
        List<Player> players = playerRepository.findAll();
        return players.stream().map(playerMapper::mapToPlayerDto)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateSuccessRate(Long playerId) {
        log.info("Calculating success rate for player with ID: {}", playerId);
        List<Game> games = gameRepository.findByPlayer_PlayerId(playerId);
        long totalGames = games.size();
        long wins = games.stream().filter(Game::isGameWon).count();
        return totalGames == 0 ? 0 : (double) wins / totalGames * 100;
    }

    @Override
    public double calculateAverageSuccessPercentage() {
        List<Player> players = playerRepository.findAll();
        double totalSuccessPercentage = players.stream()
                .mapToDouble(player -> calculateSuccessRate(player.getPlayerId()))
                .sum();
        return totalSuccessPercentage / players.size();
    }
    public Player findPlayerById(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFound("Player not found."));
    }

}
