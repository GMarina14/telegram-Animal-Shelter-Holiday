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
     * Send any message
     *
     * @param chatId        chatId value from update
     * @param messageToSend message from BotMessages
     * @return SendMessage
     */
   /* public SendMessage sendMessage(long chatId, String messageToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageToSend);
        return message;
    }

    *//**
     * Send any message w/ reply markup keyboard
     *
     * @param chatId         chatId value from update
     * @param messageToSend  message from BotMessages
     * @param keyboardMarkup keyboardMarkup selected keyboard
     * @return SendMessage
     *//*
    public SendMessage sendReplyMessage(long chatId, String messageToSend, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageToSend);
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    *//**
     * Generates a reply keyboard from String varargs
     *
     * @param buttons String values with text for menu buttons, @NotNull
     * @return ReplyKeyboardMarkup - menu keyboard
     *//*
    public ReplyKeyboardMarkup generateMenuKeyBoard(String... buttons) {

        ReplyKeyboardMarkup menuKeyBoard = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (String button : buttons) {
            row.add(button);
            if (row.size() == 1) {
                keyboardRows.add(row);
            } else if (row.size() > 1) {
                row = new KeyboardRow();
            }
        }

        menuKeyBoard.setKeyboard(keyboardRows);
        menuKeyBoard.setResizeKeyboard(true);
        menuKeyBoard.setSelective(true);
        menuKeyBoard.setInputFieldPlaceholder("ВЫБЕРИ ПУНКТ ИЗ МЕНЮ))");
        return menuKeyBoard;
    }*/

}
