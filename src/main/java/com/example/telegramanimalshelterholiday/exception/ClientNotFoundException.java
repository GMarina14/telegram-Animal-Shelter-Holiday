package com.example.telegramanimalshelterholiday.exception;

import lombok.AllArgsConstructor;


public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String message) {
        super(message);
    }
}
