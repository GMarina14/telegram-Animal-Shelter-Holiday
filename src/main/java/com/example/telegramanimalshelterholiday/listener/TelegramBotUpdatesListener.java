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

    private final MessageService messageService;
    private final HandlerClient handlerClient;
    private final HandlerVolunteer handlerVolunteer;
    private final HandlerFeedback handlerFeedback;
    private final HandlerShelterInfo handlerShelterInfo;
    private final HandlerBeforeAdoptionInfo handlerBeforeAdoptionInfo;
    private final UserDataCache userDataCache;
    private final HandlerReport handlerReport;


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
                    processMessage(update);
                } else if (update.callbackQuery() != null) {

                    try {
                        processButtonClick(update);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

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


    private void processMessage(Update update) {
        String text = update.message().text();
        Long chatId = update.message().chat().id();
        BotState botState = userDataCache.getUsersCurrentBotState(chatId);


        if (botState == STAGE_WAITING_FOR_PET_PICTURE) {
            handlerReport.saveReportPhoto(update);
            userDataCache.setUsersCurrentBotState(chatId, STAGE_WAITING_FOR_PET_DIET);
            messageService.sendMessage(chatId, SEND_DIET_REPORT);
            return;
        }
        if (botState == STAGE_WAITING_FOR_PET_DIET) {
            handlerReport.getDietInfo(update);
            userDataCache.setUsersCurrentBotState(chatId, WAITING_FOR_BEHAVIOR_CHANGE);
            messageService.sendMessage(chatId, SEND_BEHAVIOR_REPORT);
            return;
        }
        if (botState == WAITING_FOR_BEHAVIOR_CHANGE) {
            handlerReport.getBehavior(update);
            userDataCache.setUsersCurrentBotState(chatId, STAGE_WAITING_FOR_HEALTH_INFO);
            messageService.sendMessage(chatId, SEND_HEALTH_AND_STATE_REPORT);
        }
        if (botState == STAGE_WAITING_FOR_HEALTH_INFO) {
            handlerReport.getStateOfHealth(update);
            userDataCache.setUsersCurrentBotState(chatId, STAGE_WAITING_TO_SAVE_REPORT);
            messageService.sendMessage(chatId, saveOrCancelReport(chatId), SAVE_CANCEL_CORRECT_REPORT);
            return;
        }

        if (update.message().text() == null) {
            return;
        }
        switch (update.message().text()) {
            case "/start":
                handlerClient.saveClient(update);
                messageService.sendMessage(chatId, MESSAGE_TEXT);
                break;
        }

        if (botState == CHOICES_SHELTER) {
           messageService.sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
           return;
       }
    }


//    private void processMessage(Update update) {
//        String text = update.message().text();
//        Long chatId = update.message().chat().id();
//        BotState botState = userDataCache.getUsersCurrentBotState(chatId);
//
//        if (update.message().text().equals("/start")) {
//            userDataCache.assignStartMenu(chatId);
//            handlerClient.saveClient(update);
//            messageService.sendMessage(chatId, MESSAGE_TEXT);
//        }
//        if(text==null){
//            return;
//        }
//
//        else if (botState == null || botState.equals(CHOICES_SHELTER)) {
//            //  userDataCache.assignStartMenu(chatId); //присвоить начальное состояние бота
//            messageService.sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
//        }
//
//        if (botState != null) {
//
//            switch (botState) {
//                case CHOICES_SHELTER:
//                    messageService.sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
//                    break;
//
//                case STAGE_WAITING_FOR_PET_PICTURE:
//                    // methods
//                    handlerReport.saveReportPhoto(update);
//                    userDataCache.setUsersCurrentBotState(chatId, STAGE_WAITING_FOR_PET_DIET);
//                    messageService.sendMessage(chatId, SEND_DIET_REPORT);
//                    break;
//
//                case STAGE_WAITING_FOR_PET_DIET:
//                    // methods
//                    handlerReport.getDietInfo(update);
//                    userDataCache.setUsersCurrentBotState(chatId, WAITING_FOR_BEHAVIOR_CHANGE);
//                    messageService.sendMessage(chatId, SEND_BEHAVIOR_REPORT);
//                    break;
//
//                case WAITING_FOR_BEHAVIOR_CHANGE:
//                    handlerReport.getBehavior(update);
//                    userDataCache.setUsersCurrentBotState(chatId, STAGE_WAITING_FOR_HEALTH_INFO);
//                    messageService.sendMessage(chatId, SEND_HEALTH_AND_STATE_REPORT);
//                    break;
//
//
//                case STAGE_WAITING_FOR_HEALTH_INFO:
//                    handlerReport.getStateOfHealth(update);
//                    userDataCache.setUsersCurrentBotState(chatId, STAGE_WAITING_TO_SAVE_REPORT);
//                    messageService.sendMessage(chatId, saveOrCancelReport(chatId), SAVE_CANCEL_CORRECT_REPORT);
//                    break;
//
//            }
//        }
//    }


    /**
     * Process user's buttons clicks in bot. This method doesn't process anything but button clicks
     *
     * @param update
     * @see com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons
     */
    private void processButtonClick(Update update) throws IOException {
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
                        handlerShelterInfo.getGeneralShelterDescriptionCat(chatId);
                    } else {
                        handlerShelterInfo.getGeneralShelterDescriptionDog(chatId);
                    }
                    messageService.sendMessage(chatId, thirdMenuButtons(chatId), THIRD_MENU);
                    break;

                case ADOPTION_INFO:
                    messageService.sendMessage(chatId, INFO_ABOUT_ADOPTION);
                    if (botState == BotState.SHELTER_CAT) {
                        messageService.sendMessage(chatId, fourthMenuButtons(chatId), FOURTH_MENU);
                    } else {
                        messageService.sendMessage(chatId, fourthMenuButtonsDog(chatId), FOURTH_MENU);
                    }
                    break;

                case ADOPTION_REPORTS:
                    messageService.sendMessage(chatId, fifthMenuButtons(chatId), FIFTH_MENU);
                    break;

                case CALL_VOLUNTEER:
                    handlerVolunteer.callVolunteer(update, chatId);
                    break;

                case SHELTER_INFO:
                    if (botState == BotState.SHELTER_CAT) {
                        handlerShelterInfo.getShelterInformationCat(chatId);
                    } else {
                        handlerShelterInfo.getShelterInformationDog(chatId);
                    }
                    break;

                case SECURITY_MEASURES:
                    handlerShelterInfo.getSecurityMeasuresInfo(chatId);
                    break;

                case CAR_PASS:
                    if (botState == BotState.SHELTER_CAT) {
                        handlerShelterInfo.getCarPassInfoCat(chatId);
                    } else {
                        handlerShelterInfo.getCarPassInfoDog(chatId);
                    }
                    break;

                case REACH_ME_BACK:
                    handlerFeedback.reachBackClient(chatId);
                    botState = BotState.CHOICES_SHELTER;
                    // сброс состояния на главное меню
                    messageService.sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
                    break;

                case FIRST_MEETING:
                    if (botState == BotState.SHELTER_CAT) {
                        handlerBeforeAdoptionInfo.getInfoAboutFirstMeetingCat(chatId);
                    } else {
                        handlerBeforeAdoptionInfo.getInfoAboutFirstMeetingDog(chatId);
                    }
                    break;

                case DOCUMENTS_TO_ADOPT:
                    handlerBeforeAdoptionInfo.getNeededDocsToAdopt(chatId);
                    messageService.sendMessage(chatId, mainOrPreviousMenu(chatId), MAIN_PREVIOUS);
                    break;

                case TRANSPORTATION_INFO:
                    if (botState == BotState.SHELTER_CAT) {
                        handlerBeforeAdoptionInfo.getTransportationInfoCat(chatId);
                    } else {
                        handlerBeforeAdoptionInfo.getTransportationInfoDog(chatId);
                    }
                    break;

                case HOME_ADJUSTMENT:
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

                case SEND_REPORT:
                    botState = BotState.STAGE_WAITING_FOR_PET_PICTURE;
                    handlerReport.createReport(chatId);
                    messageService.sendMessage(chatId, SEND_PHOTO_REPORT);
                    break;

                case GET_REPORT_SAMPLE:
                    if (botState == BotState.SHELTER_CAT) {
                        handlerReport.sendReportSample(chatId, CAT_REPORT);
                    } else {
                        handlerReport.sendReportSample(chatId, DOG_REPORT);
                    }
                    break;

                case MAIN_PAGE:
                    messageService.sendMessage(chatId, firstMenuButtons(chatId), FIRST_MENU);
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

                // REPORTS
                case SAVE_REPORT:
                    handlerReport.saveReport(chatId);
                    break;

                case SAVE_ANYWAY:
                    messageService.sendMessage(chatId, REPORT_SAVED);
                    handlerReport.informVolunteerReportsAreIncomplete(chatId);
                    break;

                case CORRECT_REPORT:
                    messageService.sendMessage(chatId, reportButtons(chatId), REPORT_SECTIONS);
                    break;

                case CANCEL_REPORT:
                    handlerReport.cancelReport(chatId);
                    break;

                case PHOTO:
                    botState = BotState.STAGE_WAITING_FOR_PET_PICTURE;
                    messageService.sendMessage(chatId, SEND_PHOTO_REPORT);
                    // handlerReport.saveReportPhoto(update);
                    break;

                case DIET:
                    botState = STAGE_WAITING_FOR_PET_DIET;
                    messageService.sendMessage(chatId, SEND_DIET_REPORT);
                    break;

                case HEALTH_AND_STATE:
                    botState = STAGE_WAITING_FOR_HEALTH_INFO;
                    messageService.sendMessage(chatId, SEND_HEALTH_AND_STATE_REPORT);

                    break;

                case BEHAVIOR:
                    botState = WAITING_FOR_BEHAVIOR_CHANGE;
                    messageService.sendMessage(chatId, SEND_BEHAVIOR_REPORT);
                    break;

                default:
                    messageService.sendMessage(chatId, MESSAGE_INFO);

            }
            userDataCache.setUsersCurrentBotState(chatId, botState);

        }
    }

}





