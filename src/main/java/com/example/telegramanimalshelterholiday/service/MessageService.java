package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.listener.TelegramBotUpdatesListener;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;



@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;

    public MessageService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Send any message w/ inline markup keyboard
     * @param chatId         chatId value from update
     * @param inlineKeyboardMarkup message from BotMessages
     * @param heading keyboardMarkup selected keyboard
     * @return
     */
    public void sendMessage(long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String heading) {
        SendMessage message = new SendMessage(chatId, heading);
        message.replyMarkup(inlineKeyboardMarkup);
        SendResponse response = telegramBot.execute(message);
        if (!isNull(response) && !response.isOk()) {
            logger.warn("Message was not sent: {}, error code: {}", message, response.errorCode());
        }
    }

    /**
     * Send any message
     *
     * @param chatId        chatId value from update
     * @param stringText To Send message from BotMessages
     */
    public void sendMessage(long chatId, String stringText) {
        SendMessage message = new SendMessage(chatId, stringText);
        // message.replyMarkup(inlineKeyboardMarkup);
        SendResponse response = telegramBot.execute(message);
        if (!isNull(response) && !response.isOk()) {
            logger.warn("Message was not sent: {}, error code: {}", message, response.errorCode());
        }
    }
}
