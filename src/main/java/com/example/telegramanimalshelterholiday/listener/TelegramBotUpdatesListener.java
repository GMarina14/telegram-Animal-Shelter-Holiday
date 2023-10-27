package com.example.telegramanimalshelterholiday.listener;

import com.example.telegramanimalshelterholiday.cache.DataCache;
import com.example.telegramanimalshelterholiday.cache.UserDataCache;
import com.example.telegramanimalshelterholiday.component.*;
import com.example.telegramanimalshelterholiday.constants.enums.BotState;
import com.example.telegramanimalshelterholiday.service.ButtonClickService;
import com.example.telegramanimalshelterholiday.service.MessageService;
import com.example.telegramanimalshelterholiday.service.TextMessageService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.*;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_INFO;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_TEXT;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsShelters.GREETING_CAT_SHELTER;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsShelters.GREETING_DOG_SHELTER;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.*;
import static com.example.telegramanimalshelterholiday.constants.MenuHeadings.*;
import static com.example.telegramanimalshelterholiday.constants.Recommendation.INFO_ABOUT_ADOPTION;
import static com.example.telegramanimalshelterholiday.constants.ReportsConsts.REPORT_SAVED;
import static com.example.telegramanimalshelterholiday.constants.enums.BotState.*;


@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;


    private final TextMessageService textMessageService;
    private final ButtonClickService buttonClickService;


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }


    /**
     * Метод обработки всех входящих сообщений бота,поэтому нет ".text()",
     * так как фотографии тоже принимает данный метод.
     * @param updates
     * @return
     */
    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                logger.info("Processing update: {}", update);

                if (update.message() != null) {
                   textMessageService.processMessage(update);
                } else if (update.callbackQuery() != null) {

                    try {
                      buttonClickService.processButtonClick(update);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        } catch (Exception e) {
            logger.error("Error while entering a message", e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
