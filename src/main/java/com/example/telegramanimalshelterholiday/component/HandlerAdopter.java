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
        if (update.message().contact() != null) {
            String firstName = update.message().contact().firstName();
            String lastName = update.message().contact().lastName();
            String phone = update.message().contact().phoneNumber();
            String userName = update.message().chat().username();
            long chatId = update.message().chat().id();
            Adopter adopter = adopterRepository.findByChatId(chatId);
            if (isNull(adopter)) {
                Client client = clientRepository.findByChatId(chatId);
                if (isNull(client)) {
                    throw new ClientNotFoundException("Client with given ChatId not found");
                }
                adopter = new Adopter(firstName,lastName, adopter.getProbExtend(), chatId,userName,phone);


           //     adopter.setUpdateStatus(UpdateStatus.DEFAULT);
                adopterRepository.save(adopter);
                messageService.sendMessage(chatId,SAVE_ADOPTER+ " " + WILL_CONTACT);
                //

//                SendMessage message = new SendMessage(chatId, SAVE_ADOPTER_SUCCESS_TEXT + ' ' + WE_WILL_CALL_YOU_TEXT);
//                sendMessage(message.replyMarkup(createMainMenuKeyboardButtons()));
//            } else {
//                SendMessage message = new SendMessage(chatId, ADOPTER_ALREADY_EXISTS_TEXT + ' ' + WE_WILL_CALL_YOU_TEXT);
//                sendMessage(message.replyMarkup(createMainMenuKeyboardButtons()));
         }
        }
    }

            }
