package com.itacademy.RandomDiceRollGameApplication.mapper;

import com.itacademy.RandomDiceRollGameApplication.model.domain.Player;
import com.itacademy.RandomDiceRollGameApplication.model.domain.User;
import com.itacademy.RandomDiceRollGameApplication.model.dto.PlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerMapper MAPPER = Mappers.getMapper(PlayerMapper.class);

    @Mapping(source = "user.id", target = "userId") // Ensure this mapping exists
    PlayerDto mapToPlayerDto(Player player);

    @Mapping(source = "userId", target = "user", qualifiedByName = "userFromId")
    Player mapToPlayer(PlayerDto playerDto);

    @Named("userFromId")
    default User userFromId(Long userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        return user;
    }
}
