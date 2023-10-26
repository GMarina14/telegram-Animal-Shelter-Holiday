package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.cache.UserDataCache;
import com.example.telegramanimalshelterholiday.constants.enums.BotState;
import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.model.Shelter;
import com.example.telegramanimalshelterholiday.repository.ShelterRepository;
import com.example.telegramanimalshelterholiday.service.MessageService;
import com.example.telegramanimalshelterholiday.service.ShelterService;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.example.telegramanimalshelterholiday.constants.InfoConstantsShelters.*;
import static com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies.CAT;
import static com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies.DOG;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class HandlerShelterInfo {
    private static final Logger logger = LoggerFactory.getLogger(HandlerShelterInfo.class);
    private final MessageService messageService;
    private final ShelterRepository shelterRepository;

    /**
     * Provides the shelter description
     *
     * @param chatId
     */
    public void getGeneralShelterDescriptionCat(long chatId) {
        messageService.sendMessage(chatId, ABOUT_CAT_SHELTER);
    }

    public void getGeneralShelterDescriptionDog(long chatId) {
        messageService.sendMessage(chatId, ABOUT_DOG_SHELTER);
    }

    /**
     * Provides all information about the shelter:
     * the address, schedule, phone and etc
     */
    public void getShelterInformationCat(long chatId) {
        StringBuilder messageText = new StringBuilder();
        PetsSpecies petsSpecies = CAT;
        Shelter byPetsSpecies = shelterRepository.findByPetsSpecies(CAT);

        messageText.append("Город: ").append(byPetsSpecies.getCity()).append("\n");
        messageText.append("Адрес: ").append(byPetsSpecies.getAddress()).append("\n");
        messageText.append("Часы работы: ").append(byPetsSpecies.getYandexMapsUrl()).append("\n");

        messageService.sendMessage(chatId, messageText.toString() + WORK_HOURS_CAT_SHELTER);
    }

    public void getShelterInformationDog(long chatId) {
        StringBuilder messageText = new StringBuilder();
        PetsSpecies petsSpecies = DOG;
        Shelter byPetsSpecies = shelterRepository.findByPetsSpecies(DOG);
        messageText.append("Город: ").append(byPetsSpecies.getCity()).append("\n");
        messageText.append("Адрес: ").append(byPetsSpecies.getAddress()).append("\n");
        messageText.append("Часы работы: ").append(byPetsSpecies.getYandexMapsUrl()).append("\n");
        messageService.sendMessage(chatId, messageText.toString() + WORK_HOURS_DOG_SHELTER);
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
     * @param chatId
     */
    public void getCarPassInfoCat(long chatId) {
        messageService.sendMessage(chatId, CAR_PASS_INFO_CAT);

    }

    public void getCarPassInfoDog(long chatId) {
        messageService.sendMessage(chatId, CAR_PASS_INFO_DOG);
    }

}

