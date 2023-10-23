package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.service.MessageService;
import com.example.telegramanimalshelterholiday.service.ShelterService;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.example.telegramanimalshelterholiday.constants.InfoConstantsShelters.*;

@Component
@RequiredArgsConstructor
public class HandlerShelterInfo {
    private static final Logger logger = LoggerFactory.getLogger(HandlerShelterInfo.class);
    private final MessageService messageService;
    private final ShelterService shelterService;


    /**
     * Provides the shelter description
     *
     * @param update
     * @param chatId
     */
    public void getGeneralShelterDescription(Update update, long chatId) {
        // third param - state or smth to know the shelter type
        // check dog or cat shelter String replyText =
        //messageService.sendMessage(chatId, replyText);

        messageService.sendMessage(chatId, ABOUT_CAT_SHELTER);
        //or
        messageService.sendMessage(chatId, ABOUT_DOG_SHELTER);
    }


    /**
     * Provides all information about the shelter:
     * the address, schedule, phone and etc
     */
    public void getShelterInformation(Update update, long chatId) {
        // third param - state or smth to know the shelter type


        // for cat shelter


        // for dog shelter

    }


    /**
     * Provides the security measures to visit the shelter
     * The rules and measures are the same for both shelters
     *
     * @param chatId
     */
    public void getSecurityMeasuresInfo(long chatId) {
        messageService.sendMessage(chatId, SECURITY_MEASURES_SHELTERS);
    }

    /**
     * Provides information how to get to the shelter on car and how to get a car pass
     *
     * @param update
     * @param chatId
     */
    public void getCarPassInfo(Update update, long chatId) {
        // third param - state or smth to know the shelter type
        // check dog or cat shelter String replyText =
        //messageService.sendMessage(chatId, replyText);

        messageService.sendMessage(chatId, CAR_PASS_INFO_CAT);
        //or
        messageService.sendMessage(chatId, CAR_PASS_INFO_DOG);
    }


}
