package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.repository.ClientRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HandlerClient {
    private static final Logger logger = LoggerFactory.getLogger(HandlerClient.class);
    private final TelegramBot telegramBot;
    private final ClientRepository clientRepository;

    @NotNull
    public void saveClient(Update update) {
        String username = update.message().from().username();
        Long chatId = update.message().chat().id();
        Client client = clientRepository.findByChatId(chatId);
        if (client == null) {
            client = new Client(chatId, username);
        } else {
            return;
        }
        clientRepository.save(client);
    }

}
