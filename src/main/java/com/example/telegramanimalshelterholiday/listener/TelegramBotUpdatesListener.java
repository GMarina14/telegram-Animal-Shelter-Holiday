package com.example.telegramanimalshelterholiday.listener;

import com.example.telegramanimalshelterholiday.constants.enums.Icon;
import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_INFO;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_TEXT;
import static java.util.Objects.isNull;


@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;


    @PostConstruct

    public void init() {
        telegramBot.setUpdatesListener(this);
    }


    /**
     * Метод обработки всех !!! входящих сообщений бота,поэтому нет ".text()",
     * так как фотографии тоже принимает данный метод.
     *
     * @param updates
     * @return
     */
    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                logger.info("Processing update: {}", update);
                String text = update.message().text();
                Long chatId = update.message().chat().id();
                if (!isNull(update.message()) && text.contains("/start")) {
                    SendMessage message = new SendMessage(chatId, MESSAGE_TEXT);
                    telegramBot.execute(message);
                } else {

                    telegramBot.execute(sendInlineKeyBoardMessage(chatId));

                }
            });
        } catch (Exception e) {
            logger.error("Error while entering a message", e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public static SendMessage sendInlineKeyBoardMessage(long chatId) {
        // Создаем объект сообщения
        SendMessage message = new SendMessage(chatId, "Выберите приют для посещения");

        //Создаем обьект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем список списков кнопок, который впоследствии объединит ряды кнопок
       // List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        // создаем список с кнопками для первого ряда
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();

        //Создаем обьект InlineKeyboardButton, у которой есть 2 параметка:
        // Текст (Что будет написано на самой кнопке)
        // и CallBackData (Что будет отсылатся серверу при нажатии на кнопку).

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton(Icon.CAT.getParse() + PetsSpecies.CAT).callbackData("Button \"CAT SHELTER\" has been pressed");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton("DOG SHELTER").callbackData("Button \"DOG SHELTER\" has been pressed");

        // добавляем кнопки в первый ряд в том порядке,
       // какой нам необходим. В рассматриваемом случае ряд будет содержать 2 кнопки,
      // размер которых будет одинаково пропорционально растянут по ширине сообщения,
     // под которым клавиатура располагается

        rowInline1.add(inlineKeyboardButton1);
        rowInline1.add(inlineKeyboardButton2);

        markupInline.addRow(inlineKeyboardButton1,inlineKeyboardButton2);


        message.replyMarkup(markupInline);

        return message;
    }


    /**
     * метод формирует ответное сообщение для пользователя
     *
     */
//    private void sendMessage(SendMessage message) {
//        SendResponse response = telegramBot.execute(message);
//        if (!isNull(response ) && !response.isOk()) {
//            logger.warn("Message was not sent: {}, error code: {}", message, response.errorCode());
//        }
//    }



}





