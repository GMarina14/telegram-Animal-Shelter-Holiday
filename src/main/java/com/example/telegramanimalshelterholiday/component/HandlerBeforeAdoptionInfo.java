package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.service.MessageService;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.petSpecificMenuButtons;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.*;
import static com.example.telegramanimalshelterholiday.constants.MenuHeadings.HEALTH_MENU;
import static com.example.telegramanimalshelterholiday.constants.Recommendation.*;

@Component
@RequiredArgsConstructor
public class HandlerBeforeAdoptionInfo {
    private static final Logger logger = LoggerFactory.getLogger(HandlerBeforeAdoptionInfo.class);
    private final MessageService messageService;

    /**
     * Provides info how to behave with an animal for the first time meeting each other
     * @param chatId
     */
    public void getInfoAboutFirstMeetingCat(long chatId) {
        messageService.sendMessage(chatId, RULES_HOW_TO_MEET_CAT);
    }

    public void getInfoAboutFirstMeetingDog(long chatId) {
        messageService.sendMessage(chatId, RULES_HOW_TO_MEET_DOG);
    }


    /**
     * Provides the list of needed documents to adopt an animal
     * The rules are the same for both shelters
     *
     * @param chatId
     */
    public void getNeededDocsToAdopt(long chatId) {
        messageService.sendMessage(chatId, DOCUMENTS_NEEDED_TO_ADOPT_ANIMAL);
    }

    /**
     * Provides recommendations of safe transportation
     *
     * @param chatId
     */
    public void getTransportationInfoCat(long chatId) {
        messageService.sendMessage(chatId, RECOMMENDATIONS_FOR_CAT_TRANSPORTATION);
    }

    public void getTransportationInfoDog( long chatId) {
        messageService.sendMessage(chatId, RECOMMENDATIONS_FOR_DOG_TRANSPORTATION);

    }

    /**
     * Provides information how to adjust the house before getting home with the adopted pet
     * Include age and health specification
     *
     * @param animalAgeAndHealthSpecific
     * @param chatId
     * @see com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons
     */
    public void getHomeAdjustmentInfo(String animalAgeAndHealthSpecific, long chatId) {


        switch (animalAgeAndHealthSpecific) {
            case CAT:
                messageService.sendMessage(chatId, RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_CAT);
                messageService.sendMessage(chatId, petSpecificMenuButtons(chatId), HEALTH_MENU);
                break;

            case DOG:
                messageService.sendMessage(chatId, RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_DOG);
                messageService.sendMessage(chatId, petSpecificMenuButtons(chatId), HEALTH_MENU);
                break;

            case KITTY:
                messageService.sendMessage(chatId, RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_KITTY);
                messageService.sendMessage(chatId, petSpecificMenuButtons(chatId), HEALTH_MENU);
                break;

            case PUPPY:
                messageService.sendMessage(chatId, RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_PUPPY);
                messageService.sendMessage(chatId, petSpecificMenuButtons(chatId), HEALTH_MENU);
                break;

            case SIGHT_PROBLEMS:
                messageService.sendMessage(chatId, RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_ANIMAL_WITH_SIGHTPROBLEMS);
                break;

            case MOBILITY_PROBLEMS:
                messageService.sendMessage(chatId, RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_ANIMAL_WITH_MOBILITYPROBLEMS);
                break;

            case HEALTHY:
                messageService.sendMessage(chatId, RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_HEALTHY_ANIMAL);
                break;
        }

    }

    /**
     * Provides the list of reasons why client can get rejection to adopt
     * The rules are the same for both shelters
     *
     * @param chatId
     */
    public void getReasonsOfRejection(long chatId) {
        messageService.sendMessage(chatId, REASONS_OF_REJECTION_TO_ADOPT);

    }

    /**
     * Provides recommendations for the first date with the dog handler
     *
     * @param chatId
     */
    public void getHandlersRecommendations(long chatId) {
        messageService.sendMessage(chatId, RECOMMENDATIONS_OF_DOGSHANDLERS);
    }

    /**
     * Provides the list of recommended dog handlers
     *
     * @param chatId
     */
    public void getFirstHandlerDateRecommendations(long chatId) {
        messageService.sendMessage(chatId, RECOMMENDATIONS_FOR_THE_FIRST_MEETING_WITH_DOGHANDLER);
    }

}
