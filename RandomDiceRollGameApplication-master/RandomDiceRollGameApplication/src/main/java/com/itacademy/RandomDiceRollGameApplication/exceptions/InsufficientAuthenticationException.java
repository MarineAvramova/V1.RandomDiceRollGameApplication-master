package com.itacademy.RandomDiceRollGameApplication.exceptions;

public class InsufficientAuthenticationException extends RuntimeException{
    public InsufficientAuthenticationException(String message){
        super(message);
    }
}
