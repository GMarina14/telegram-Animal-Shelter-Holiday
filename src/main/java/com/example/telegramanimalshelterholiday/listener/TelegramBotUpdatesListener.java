package com.example.telegramanimalshelterholiday.listener;

import com.example.telegramanimalshelterholiday.component.HandlerClient;
import com.example.telegramanimalshelterholiday.component.HandlerVolunteer;
import com.example.telegramanimalshelterholiday.constants.enums.Icon;
import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.repository.ClientRepository;
import com.example.telegramanimalshelterholiday.service.ClientService;
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
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.*;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_INFO;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_TEXT;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsShelterCat.GREETING_CAT_SHELTER;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsShelterDog.GREETING_DOG_SHELTER;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.*;
import static com.example.telegramanimalshelterholiday.constants.MenuHeadings.*;
import static java.util.Objects.isNull;


@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;

    private final MessageService messageService;
    private final HandlerClient handlerClient;

    private final HandlerVolunteer handlerVolunteer;


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
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.message() != null) {
                firstMessage(update);

            } else if (update.callbackQuery() != null) {

                processButtonClick(update);

            }
        });


     /*   try {
            updates.forEach(update -> {
                logger.info("Processing update: {}", update);
                String text = update.message().text();
                Long chatId = update.message().chat().id();


                if (!isNull(update.message()) && text.contains("/start")) {
                    SendMessage message = new SendMessage(chatId, MESSAGE_TEXT);
                    telegramBot.execute(message);

                    sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
              *//*  } else if(update.message() != null){

                //  sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
*//*
                } else {


                    if (update.callbackQuery() != null) {
                        processButtonClick(update);


                        //  if (update.callbackQuery() != null) {

                        //     telegramBot.execute(sendInlineKeyBoardMessage(chatId));
                        //  telegramBot.execute(secondMenuInlineKeyBoardMessage(chatId));
                        //   telegramBot.execute(thirdMenuInlineKeyBoardMessage(chatId));

                        //    sendMessage(chatId, secondMenuButtons(chatId), SECOND_MENU);
                        //  sendMessage(chatId, thirdMenuButtons(chatId), THIRD_MENU);
                        // sendMessage(chatId, fourthMenuButtons(chatId), FOURTH_MENU);
                        //  sendMessage(chatId, petSpecificMenuButtons(chatId), HEALTH_MENU);
                        // sendMessage(chatId, dogAgeMenuButtons(chatId), AGE_MENU);
                  *//*  if(update.callbackQuery()!=null){
                        update.callbackQuery().data();
                    }
*//*
                        //   sendMessage(chatId, fifthMenuButtons(chatId),FIFTH_MENU);


                        // telegramBot.execute(fourthMenuInlineKeyBoardMessage(chatId));
                        // telegramBot.execute(fifthMenuInlineKeyBoardMessage(chatId));


                        //    }
                    }
                }

            });
        } catch (Exception e) {
            logger.error("Error while entering a message", e);
        }*/
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


    private void firstMessage(Update update) {
        String text = update.message().text();
        Long chatId = update.message().chat().id();
    /*  Collection<Client> client =  clientRepository.findByChatId(chatId);
      if (client.isEmpty())
          clientRepository*/

        handlerClient.saveClient(update);//может поставить его после /start?

        if (text.contains("/start"))

            messageService.sendMessage(chatId, MESSAGE_TEXT);
        else if (text == null) {
            return;
        }
        messageService.sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
    }


    /**
     * Process user's buttons clicks in bot. This method doesn't process anything but button clicks
     *
     * @param update
     * @see com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons
     */
    private void processButtonClick(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        if (callbackQuery != null) {
            long chatId = callbackQuery.message().chat().id();
            switch (callbackQuery.data()) {
                case CAT_SHELTER:
                    messageService.sendMessage(chatId, GREETING_CAT_SHELTER);
                    messageService.sendMessage(chatId, secondMenuButtons(chatId), SECOND_MENU);
                    break;

                case DOG_SHELTER:
                    messageService.sendMessage(chatId, GREETING_DOG_SHELTER);
                    messageService.sendMessage(chatId, secondMenuButtons(chatId), SECOND_MENU);
                    break;

                case ALL_ABOUT_SHELTER:

                    // check dog or cat shelter String replyText =
                    //messageService.sendMessage(chatId, replyText);
                    messageService.sendMessage(chatId, thirdMenuButtons(chatId), THIRD_MENU);
                    break;

                case ADOPTION_INFO:

                    break;

                case ADOPTION_REPORTS:

                    break;

                case CALL_VOLUNTEER:
                    handlerVolunteer.callVolunteer(update, chatId);
                    break;


                default:
                    messageService.sendMessage(chatId, "No");


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





