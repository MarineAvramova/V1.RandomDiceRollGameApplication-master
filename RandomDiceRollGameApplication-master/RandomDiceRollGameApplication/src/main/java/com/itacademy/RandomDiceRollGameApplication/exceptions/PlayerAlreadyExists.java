package com.itacademy.RandomDiceRollGameApplication.exceptions;

public class PlayerAlreadyExists extends RuntimeException{
    public PlayerAlreadyExists(String message) {
        super(message);
    }
}
