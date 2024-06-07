package com.itacademy.RandomDiceRollGameApplication.mapper;

import com.itacademy.RandomDiceRollGameApplication.model.domain.Game;
import com.itacademy.RandomDiceRollGameApplication.model.dto.GameDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    GameDto toGameDto(Game game);

    Game toGame(GameDto gameDto);
}
