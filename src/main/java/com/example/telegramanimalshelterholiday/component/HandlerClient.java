package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.repository.ClientRepository;
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
    private final ClientRepository clientRepository;

    /**
     * the method saves the bot user to the database
     * @param update
     */
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
