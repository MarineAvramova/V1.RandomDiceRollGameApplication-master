package com.itacademy.RandomDiceRollGameApplication.exceptions;

public class PlayerNotFound extends RuntimeException{
    public PlayerNotFound(String message) {
        super(message);
    }
}
