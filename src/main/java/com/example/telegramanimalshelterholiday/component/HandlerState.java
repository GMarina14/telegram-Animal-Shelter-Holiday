package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.cache.UserDataCache;
import com.example.telegramanimalshelterholiday.constants.enums.BotState;
import com.example.telegramanimalshelterholiday.service.MessageService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.CAT_SHELTER;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.DOG_SHELTER;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class HandlerState {


    private final UserDataCache userDataCache;
    private final TelegramBot telegramBot;
    private static final Logger logger = LoggerFactory.getLogger(HandlerState.class);

    public void statusHandler(Update update) {
        long chatId = update.message().chat().id();

        //состояние выбора приюта
        BotState botState = userDataCache.getUsersCurrentBotState(chatId);

        if (botState == BotState.SHELTER_CAT) {
            userDataCache.setUsersCurrentBotState(chatId, BotState.STAGE_INFO_CAT);

            return;
        }
        if (botState == BotState.STAGE_INFO_CAT) {
            userDataCache.setUsersCurrentBotState(chatId, BotState.STAGE_ADOPTER_IN_ADOPTION_INFO_MENU_CAT);
            return;
        }
        if (botState == BotState.STAGE_ADOPTER_IN_ADOPTION_INFO_MENU_CAT) {
            userDataCache.setUsersCurrentBotState(chatId, BotState.STAGE_WAITING_FOR_PET_PICTURE);
            return;
        }

        if (botState == BotState.SHELTER_DOG) {
            userDataCache.setUsersCurrentBotState(chatId, BotState.STAGE_INFO_DOG);

            return;
        }
        if (botState == BotState.STAGE_INFO_DOG) {
            userDataCache.setUsersCurrentBotState(chatId, BotState.STAGE_ADOPTER_IN_ADOPTION_INFO_MENU_DOG);
            return;
        }
        if (botState == BotState.STAGE_ADOPTER_IN_ADOPTION_INFO_MENU_DOG) {
            userDataCache.setUsersCurrentBotState(chatId, BotState.STAGE_WAITING_FOR_PET_PICTURE);
            return;
        }

        if (botState == BotState.STAGE_WAITING_FOR_PET_PICTURE) {
            //  saveReportPhoto(update);
            userDataCache.setUsersCurrentBotState(chatId, BotState.STAGE_WAITING_FOR_PET_DIET);
            return;
        }

        if (botState == BotState.STAGE_WAITING_FOR_PET_DIET) {
            //  saveReportDiet(update);
            userDataCache.setUsersCurrentBotState(chatId, BotState.STAGE_WAITING_FOR_WELL_BEING);

            return;
        }
        if (botState == BotState.STAGE_WAITING_FOR_WELL_BEING) {
            //  saveReportWellBeing(update);
            userDataCache.setUsersCurrentBotState(chatId, BotState.WAITING_FOR_BEHAVIOR_CHANGE);

            return;
        }
        if (botState == BotState.WAITING_FOR_BEHAVIOR_CHANGE) {
            //  saveReportBehaviorChange(update);
            userDataCache.setUsersCurrentBotState(chatId, BotState.DEFAULT);
            return;
        }
        if (update.message().text() == null) {
            return;
        }

        switch (update.message().text()) {


        }
    }


    /***
     //     * определяет в каком состоянии сообщение
     //     * @param message
     //     * @return
     //     */
    private void handlerInputMessage(Message message) {
        String inputMessage = message.text();
        long userId = message.from().id();
        BotState botState;
        SendMessage sendMessage;
        switch (inputMessage) {
            case "/start":
                botState = BotState.CHOICES_SHELTER;
                break;
            case CAT_SHELTER:
                botState = BotState.SHELTER_CAT;
                break;
            case DOG_SHELTER:
                botState = BotState.SHELTER_DOG;
                break;



            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }
        //назначаю состояние бота
        userDataCache.setUsersCurrentBotState(userId, botState);

        //обработать входящие сообщение используя бот-состояния и ответ message нашего пользователя
       // sendMessage = botStateContext.processInputMessage(botState, message);


    }


}
