package com.example.telegramanimalshelterholiday.listener;

import com.example.telegramanimalshelterholiday.cache.DataCache;
import com.example.telegramanimalshelterholiday.cache.UserDataCache;
import com.example.telegramanimalshelterholiday.component.*;
import com.example.telegramanimalshelterholiday.constants.enums.BotState;
import com.example.telegramanimalshelterholiday.service.MessageService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.*;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_INFO;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_TEXT;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsShelters.GREETING_CAT_SHELTER;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsShelters.GREETING_DOG_SHELTER;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.*;
import static com.example.telegramanimalshelterholiday.constants.MenuHeadings.*;
import static com.example.telegramanimalshelterholiday.constants.Recommendation.INFO_ABOUT_ADOPTION;


@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;

    private final MessageService messageService;
    private final HandlerClient handlerClient;
    private final HandlerVolunteer handlerVolunteer;
    private final HandlerFeedback handlerFeedback;
    private final HandlerShelterInfo handlerShelterInfo;
    private final HandlerBeforeAdoptionInfo handlerBeforeAdoptionInfo;
    private final HandlerState handlerState;
    private final UserDataCache userDataCache;


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

                if (update.message() != null) {
                    firstMessage(update);

                } else if (update.callbackQuery() != null) {

                    processButtonClick(update);

                }
            });
        } catch (Exception e) {
            logger.error("Error while entering a message", e);
        }

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

        handlerClient.saveClient(update);

        if (text.contains("/start"))
            messageService.sendMessage(chatId, MESSAGE_TEXT);
        else if (text == null) {
            //messageService.sendMessage(chatId, MESSAGE_TEXT);
            return;
        }
        userDataCache.assignStartMenu(chatId); //присвоить начальное состояние бота
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
            BotState botState = userDataCache.assignStartMenu(chatId);
            switch (callbackQuery.data()) {
                case CAT_SHELTER:
                    botState = BotState.SHELTER_CAT;
                    messageService.sendMessage(chatId, GREETING_CAT_SHELTER);
                    messageService.sendMessage(chatId, secondMenuButtons(chatId), SECOND_MENU);
                    break;

                case DOG_SHELTER:
                    botState = BotState.SHELTER_DOG;
                    messageService.sendMessage(chatId, GREETING_DOG_SHELTER);
                    messageService.sendMessage(chatId, secondMenuButtons(chatId), SECOND_MENU);
                    break;

                case ALL_ABOUT_SHELTER:
                    if (botState == BotState.SHELTER_CAT) {
                        handlerShelterInfo.getGeneralShelterDescriptionCat(update, chatId);
                    } else {
                        handlerShelterInfo.getGeneralShelterDescriptionDog(update, chatId);
                    }
                    messageService.sendMessage(chatId, thirdMenuButtons(chatId), THIRD_MENU);
                    break;

                case ADOPTION_INFO:
                    messageService.sendMessage(chatId, INFO_ABOUT_ADOPTION);
                    if (botState == BotState.SHELTER_CAT) {
                        // Нужна проверка приюта, чтобы выдавать без кинологов кнопки
                        messageService.sendMessage(chatId, fourthMenuButtons(chatId), FOURTH_MENU);
                    } else {
                        messageService.sendMessage(chatId, fourthMenuButtonsDog(chatId), FOURTH_MENU);
                    }
                    break;

                case ADOPTION_REPORTS:
                    // YET IS EMPTY
                    messageService.sendMessage(chatId, fifthMenuButtons(chatId), FIFTH_MENU);
                    break;

                case CALL_VOLUNTEER:
                    handlerVolunteer.callVolunteer(update, chatId);
                    break;

                case SHELTER_INFO:
                    handlerShelterInfo.getShelterInformation(update, chatId);
                    // check dog or cat shelter String replyText =
                    //messageService.sendMessage(chatId, replyText);
                    break;

                case SECURITY_MEASURES:
                    handlerShelterInfo.getSecurityMeasuresInfo(chatId);
                    break;

                case CAR_PASS:
                    handlerShelterInfo.getCarPassInfo(update, chatId);
                    // check dog or cat shelter String replyText =
                    //messageService.sendMessage(chatId, replyText);
                    break;

                case REACH_ME_BACK:
                    handlerFeedback.reachBackClient(chatId);
                    botState = BotState.CHOICES_SHELTER;
                    // сброс состояния?? на главное меню
                    messageService.sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
                    break;

                case FIRST_MEETING:
                    handlerBeforeAdoptionInfo.getInfoAboutFirstMeeting(update, chatId);
                    break;

                case DOCUMENTS_TO_ADOPT:
                    handlerBeforeAdoptionInfo.getNeededDocsToAdopt(chatId);
                    messageService.sendMessage(chatId, mainOrPreviousMenu(chatId), MAIN_PREVIOUS);
                    break;

                case TRANSPORTATION_INFO:
                    handlerBeforeAdoptionInfo.getTransportationInfo(update, chatId);
                    break;

                case HOME_ADJUSTMENT:
                    // отправляем меню
                    // check dog or cat shelter
                    if (botState == BotState.SHELTER_CAT) {
                        messageService.sendMessage(chatId, catAgeMenuButtons(chatId), AGE_MENU);
                    } else {
                        messageService.sendMessage(chatId, dogAgeMenuButtons(chatId), AGE_MENU);
                    }
                    break;

                case REJECTION_REASONS:
                    handlerBeforeAdoptionInfo.getReasonsOfRejection(chatId);
                    break;


                case DOG_HANDLER_RECOMMENDATIONS:

                    messageService.sendMessage(chatId, dogHandlers(chatId), DOG_HANDLERS);
                    break;

                case FIRST_HANDLER_DATE:
                    handlerBeforeAdoptionInfo.getFirstHandlerDateRecommendations(chatId);
                    break;
                case LIST_OF_HANDLERS:
                    handlerBeforeAdoptionInfo.getHandlersRecommendations(chatId);

                    break;

                case MAIN_PAGE:
                    messageService.sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
                    break;

                case PREVIOUS_PAGE:
                    // по ответу на каком уровне сейчас и какой прошлый
                    break;


                //  home adjustment buttons
                case CAT:
                    handlerBeforeAdoptionInfo.getHomeAdjustmentInfo(CAT, chatId);
                    break;

                case DOG:
                    handlerBeforeAdoptionInfo.getHomeAdjustmentInfo(DOG, chatId);
                    break;

                case KITTY:
                    botState = BotState.KITTY_STATE;
                    handlerBeforeAdoptionInfo.getHomeAdjustmentInfo(KITTY, chatId);
                    break;

                case PUPPY:
                    botState = BotState.PUPPY_STATE;
                    handlerBeforeAdoptionInfo.getHomeAdjustmentInfo(PUPPY, chatId);
                    break;

                case SIGHT_PROBLEMS:
                    botState = BotState.SIGHT_PROBLEMS_STATE;
                    handlerBeforeAdoptionInfo.getHomeAdjustmentInfo(SIGHT_PROBLEMS, chatId);
                    break;

                case MOBILITY_PROBLEMS:
                    botState = BotState.MOBILITY_PROBLEMS_STATE;
                    handlerBeforeAdoptionInfo.getHomeAdjustmentInfo(MOBILITY_PROBLEMS, chatId);
                    break;

                case HEALTHY:
                    handlerBeforeAdoptionInfo.getHomeAdjustmentInfo(HEALTHY, chatId);
                    break;

                default:
                    messageService.sendMessage(chatId, MESSAGE_INFO);


            }
            userDataCache.setUsersCurrentBotState(chatId, botState);

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





