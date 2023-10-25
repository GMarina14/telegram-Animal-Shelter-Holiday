package com.example.telegramanimalshelterholiday.cache;

import com.example.telegramanimalshelterholiday.constants.enums.BotState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class UserDataCache implements DataCache {
    private Map<Long, BotState> userBotStates = new HashMap<>();


    /**
     * Назначить состояние бота пользователю
     *
     * @param chatId
     * @param botState
     */
    @Override
    public void setUsersCurrentBotState(long chatId, BotState botState) {
        userBotStates.put(chatId, botState);
    }

    /**
     * получить состояние бота по chatId
     *
     * @param chatId
     * @return
     */
    @Override
    public BotState getUsersCurrentBotState(long chatId) {
        BotState botState = userBotStates.get(chatId);
//        if (isNull(botState)) {
//            botState = BotState.CHOICES_SHELTER;
//            //первое сотояние бота
//        }
        return botState;
    }
    public BotState assignStartMenu(long chatId) {
        BotState botState = userBotStates.get(chatId);
        if (isNull(botState)) {
            botState = BotState.CHOICES_SHELTER;
            //первое сотояние бота вернуться может метод войд
        }
        return botState;
    }





}
