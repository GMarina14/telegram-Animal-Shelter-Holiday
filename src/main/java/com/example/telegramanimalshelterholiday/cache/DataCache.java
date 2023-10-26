package com.example.telegramanimalshelterholiday.cache;


import com.example.telegramanimalshelterholiday.constants.enums.BotState;

/**
 * назначит для пользоватедя конкретное состояние бота
 */
public interface DataCache {


    void setUsersCurrentBotState(long chatId, BotState botState);

    BotState getUsersCurrentBotState(long chatId);

    BotState assignStartMenu(long chatId);
}
