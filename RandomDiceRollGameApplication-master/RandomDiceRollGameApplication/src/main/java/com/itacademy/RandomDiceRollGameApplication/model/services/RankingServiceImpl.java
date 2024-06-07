package com.itacademy.RandomDiceRollGameApplication.model.services;

import com.itacademy.RandomDiceRollGameApplication.exceptions.PlayerNotFound;
import com.itacademy.RandomDiceRollGameApplication.mapper.PlayerMapper;
import com.itacademy.RandomDiceRollGameApplication.model.domain.Player;
import com.itacademy.RandomDiceRollGameApplication.model.dto.PlayerDto;
import com.itacademy.RandomDiceRollGameApplication.model.repository.GameRepository;
import com.itacademy.RandomDiceRollGameApplication.model.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService{

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    @Override
    public double getAveragePlayer() {
        List<Player>players = playerRepository.findAll();
        if (players.isEmpty()){
            throw new PlayerNotFound("Empty list of players");
        }
        double totalSuccessRate = players.stream()
                .mapToDouble(player -> playerService.calculateSuccessRate(player.getPlayerId()))
                .sum();
        return totalSuccessRate / players.size();
    }

    @Override
    public PlayerDto getWinnerPlayer() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new PlayerNotFound("Empty list of players");
        }
        Player winner = players.stream()
                .max((p1, p2) -> Double.compare(playerService.calculateSuccessRate(p1.getPlayerId()), playerService.calculateSuccessRate(p2.getPlayerId()))) // Use playerService
                .orElseThrow(() -> new PlayerNotFound("No players found"));
        return playerMapper.mapToPlayerDto(winner);
    }

    @Override
    public PlayerDto getLoserPlayer() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new PlayerNotFound("Empty list of players");
        }
        Player loser = players.stream()
                .min((p1, p2) -> Double.compare(playerService.calculateSuccessRate(p1.getPlayerId()), playerService.calculateSuccessRate(p2.getPlayerId()))) // Use playerService
                .orElseThrow(() -> new PlayerNotFound("No players found"));
        return playerMapper.mapToPlayerDto(loser);
    }
}
