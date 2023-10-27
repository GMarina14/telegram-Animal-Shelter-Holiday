package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.cache.UserDataCache;
import com.example.telegramanimalshelterholiday.component.HandlerClient;
import com.example.telegramanimalshelterholiday.component.HandlerReport;
import com.example.telegramanimalshelterholiday.constants.enums.BotState;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.firstMenuButtons;
import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.saveOrCancelReport;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_TEXT;
import static com.example.telegramanimalshelterholiday.constants.MenuHeadings.*;
import static com.example.telegramanimalshelterholiday.constants.enums.BotState.*;
import static com.example.telegramanimalshelterholiday.constants.enums.BotState.CHOICES_SHELTER;

@Service
@RequiredArgsConstructor
public class TextMessageService {
    private final UserDataCache userDataCache;

    private final HandlerReport handlerReport;
    private final HandlerClient handlerClient;
    private final MessageService messageService;


    /**
     * This method processes all text and photo messages
     * Also through bot state invokes all needed methods concerning report section
     * @param update
     */


    public void processMessage(Update update) {
        Long chatId = update.message().chat().id();
        BotState botState = userDataCache.assignStartMenu(chatId);

        if (botState == STAGE_WAITING_FOR_PET_PICTURE) {
            handlerReport.saveReportPhoto(update);
            userDataCache.setUsersCurrentBotState(chatId, STAGE_WAITING_FOR_PET_DIET);
           messageService.sendMessage(chatId, SEND_DIET_REPORT);
            return;
        }
        if (botState == STAGE_WAITING_FOR_PET_DIET) {
            handlerReport.getDietInfo(update);
            userDataCache.setUsersCurrentBotState(chatId, WAITING_FOR_BEHAVIOR_CHANGE);
            messageService.sendMessage(chatId, SEND_BEHAVIOR_REPORT);
            return;
        }
        if (botState == WAITING_FOR_BEHAVIOR_CHANGE) {
            handlerReport.getBehavior(update);
            userDataCache.setUsersCurrentBotState(chatId, STAGE_WAITING_FOR_HEALTH_INFO);
            messageService.sendMessage(chatId, SEND_HEALTH_AND_STATE_REPORT);
        }
        if (botState == STAGE_WAITING_FOR_HEALTH_INFO) {
            handlerReport.getStateOfHealth(update);
            userDataCache.setUsersCurrentBotState(chatId, STAGE_WAITING_TO_SAVE_REPORT);
            messageService.sendMessage(chatId, saveOrCancelReport(chatId), SAVE_CANCEL_CORRECT_REPORT);
            return;
        }

        if (update.message().text() == null) {
            return;
        }
        switch (update.message().text()) {
            case "/start":
                handlerClient.saveClient(update);
                messageService.sendMessage(chatId, MESSAGE_TEXT);
                break;
        }

        if (botState == CHOICES_SHELTER) {
            messageService.sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
            return;
        }
    }

}
