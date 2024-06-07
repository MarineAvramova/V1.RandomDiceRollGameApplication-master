package com.itacademy.RandomDiceRollGameApplication.model.services;

import com.itacademy.RandomDiceRollGameApplication.model.dto.PlayerDto;

public interface RankingService {
    double getAveragePlayer ();
    PlayerDto getWinnerPlayer ();
    PlayerDto getLoserPlayer ();
}
