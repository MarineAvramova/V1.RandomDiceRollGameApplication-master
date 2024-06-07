package com.itacademy.RandomDiceRollGameApplication.model.services;

import com.itacademy.RandomDiceRollGameApplication.exceptions.GameNotFound;
import com.itacademy.RandomDiceRollGameApplication.mapper.GameMapper;
import com.itacademy.RandomDiceRollGameApplication.model.domain.Game;
import com.itacademy.RandomDiceRollGameApplication.model.domain.Player;
import com.itacademy.RandomDiceRollGameApplication.model.dto.GameDto;
import com.itacademy.RandomDiceRollGameApplication.model.repository.GameRepository;
import com.itacademy.RandomDiceRollGameApplication.model.services.PlayerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    private final PlayerServiceImpl playerService;
@Transactional
    @Override
    public GameDto createGame(Long playerId) {
        log.info("Creating new game");

        Player player = playerService.findPlayerById(playerId);
        Game newGame = new Game();
        newGame.setPlayer(player);
        newGame.throwDice();
            Game savedGame = gameRepository.save(newGame);
            log.info("Game created successfully with ID: {}", savedGame.getGameId());
            return gameMapper.toGameDto(savedGame);

    }
@Transactional
    @Override
    public List<GameDto> getGamesByPlayerId(Long playerId) {
        Player player = playerService.findPlayerById(playerId);
        log.info("Fetching games for player with ID: {}", playerId);
        List<Game> games = gameRepository.findByPlayer_PlayerId(player.getPlayerId());
        if(games==null || games.isEmpty()){
            throw new GameNotFound("There are no games for this player");
        }
        return games.stream().map(gameMapper::toGameDto).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void deleteGames(Long playerId) {
        Player player = playerService.findPlayerById(playerId);
     List<Game> games =player.getGames();
             //gameRepository.findByPlayer_PlayerId(playerId);
        if(games==null || games.isEmpty()){
            throw new GameNotFound("Game not found for this player " + playerId);
        }
        games.clear();
        gameRepository.deleteByPlayer_PlayerId(playerId);
    }
}
