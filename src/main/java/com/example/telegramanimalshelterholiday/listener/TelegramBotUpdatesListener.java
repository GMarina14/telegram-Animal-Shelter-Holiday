package com.example.telegramanimalshelterholiday.listener;

import com.example.telegramanimalshelterholiday.constants.enums.Icon;
import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.service.MessageService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.*;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_INFO;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_TEXT;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.CAT_SHELTER;
import static com.example.telegramanimalshelterholiday.constants.MenuHeadings.*;
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

              /*  } else if(update.message() != null){
                //  sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
*/
                }else{

                    //     telegramBot.execute(sendInlineKeyBoardMessage(chatId));
                    //  telegramBot.execute(secondMenuInlineKeyBoardMessage(chatId));
                    //   telegramBot.execute(thirdMenuInlineKeyBoardMessage(chatId));

                   //    sendMessage(chatId, secondMenuButtons(chatId), SECOND_MENU);
                   //  sendMessage(chatId, thirdMenuButtons(chatId), THIRD_MENU);
                    // sendMessage(chatId, fourthMenuButtons(chatId), FOURTH_MENU);

                   // sendMessage(chatId, dogAgeMenuButtons(chatId), AGE_MENU);

                    sendMessage(chatId, petSpecificMenuButtons(chatId), HEALTH_MENU);

                    processButtonClick(update);

                  //   sendMessage(chatId, fifthMenuButtons(chatId),FIFTH_MENU);


                    // telegramBot.execute(fourthMenuInlineKeyBoardMessage(chatId));
                    // telegramBot.execute(fifthMenuInlineKeyBoardMessage(chatId));


                }
            });
        } catch (Exception e) {
            logger.error("Error while entering a message", e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void sendMessage(long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String heading) {
        SendMessage message = new SendMessage(chatId, heading);
        message.replyMarkup(inlineKeyboardMarkup);
        SendResponse response = telegramBot.execute(message);
        if (!isNull(response) && !response.isOk()) {
            logger.warn("Message was not sent: {}, error code: {}", message, response.errorCode());
        }
    }
    public void sendMessage(long chatId, String string){
        SendMessage message = new SendMessage(chatId,string);
        // message.replyMarkup(inlineKeyboardMarkup);
        SendResponse response = telegramBot.execute(message);
        if (!isNull(response) && !response.isOk()) {
            logger.warn("Message was not sent: {}, error code: {}", message, response.errorCode());
        }
    }




    /**
     * Process user's buttons clicks in bot. This method doesn't process anything but button clicks
     * @param update
     * @see com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons
     */
    private void processButtonClick(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        if (callbackQuery != null) {
            long chatId = callbackQuery.message().chat().id();
          switch (callbackQuery.data()) {
              case CAT_SHELTER:
              //    sendMessage(chatId,"HEY");

                  break;

              default:
             //     sendMessage(chatId,"No");




          }
        }
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





