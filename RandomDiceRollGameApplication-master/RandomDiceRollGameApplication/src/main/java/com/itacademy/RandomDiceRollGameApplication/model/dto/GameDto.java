package com.itacademy.RandomDiceRollGameApplication.model.dto;

import com.itacademy.RandomDiceRollGameApplication.model.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GameDto {
    private Long gameId;
    private int dice1;
    private int dice2;
    private boolean win;
    private Player player;
}
