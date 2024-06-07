package com.itacademy.RandomDiceRollGameApplication.exceptions;

public class GameNotFound extends RuntimeException{
    public GameNotFound(String message) {
        super(message);
    }
}
