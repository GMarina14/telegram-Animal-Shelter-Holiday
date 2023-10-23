package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.exception.ClientNotFoundException;
import com.example.telegramanimalshelterholiday.model.Adopter;
import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.repository.AdopterRepository;
import com.example.telegramanimalshelterholiday.repository.ClientRepository;
import com.example.telegramanimalshelterholiday.service.MessageService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.SAVE_ADOPTER;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.WILL_CONTACT;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class HandlerAdopter {
    private static final Logger logger = LoggerFactory.getLogger(HandlerAdopter.class);
    private final MessageService messageService;
    private final AdopterRepository adopterRepository;
    private final ClientRepository clientRepository;

    public void saveAdopter(Update update) {
        String userName = update.message().from().username();
        Long chatId = update.message().chat().id();
        String firstName = update.message().from().firstName();
        String lastName = update.message().from().lastName();
        String phone = update.message().contact().phoneNumber();
        Adopter adopter = adopterRepository.findByChatId(chatId);
        if (isNull(adopter)) {
            Client client = clientRepository.findByChatId(chatId);
            if (isNull(client)) {
                throw new ClientNotFoundException("Client with given ChatId not found");
            }
            adopter = new Adopter(firstName, lastName, chatId, userName, phone);

            //     adopter.setUpdateStatus(UpdateStatus.DEFAULT);
            //   adopterRepository.save(adopter);
            //    messageService.sendMessage(chatId, SAVE_ADOPTER + " " + WILL_CONTACT);

            //   } else {
            //     messageService.sendMessage(chatId, ADOPTER_ALREADY_EXISTS + " " + WILL_CONTACT);
            //    }
        }
    }
}


