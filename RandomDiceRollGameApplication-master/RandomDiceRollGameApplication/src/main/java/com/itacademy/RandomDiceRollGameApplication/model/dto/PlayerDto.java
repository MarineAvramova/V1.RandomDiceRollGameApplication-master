package com.itacademy.RandomDiceRollGameApplication.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private Long playerId;
    private String name;
    private LocalDateTime registerDate;
    private double successScore;
    private Long userId;

}
