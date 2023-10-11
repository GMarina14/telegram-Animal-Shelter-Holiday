package com.example.telegramanimalshelterholiday;

import com.pengrad.telegrambot.impl.TelegramBotClient;
import com.pengrad.telegrambot.model.WebhookInfo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class TelegramAnimalShelterHolidayApplication {
	public static void main(String[] args) {
		SpringApplication.run(TelegramAnimalShelterHolidayApplication.class, args);
		System.out.println("Hello world!");
	}


}
