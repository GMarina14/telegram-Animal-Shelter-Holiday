package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.cache.UserDataCache;
import com.example.telegramanimalshelterholiday.component.*;
import com.example.telegramanimalshelterholiday.constants.enums.BotState;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.*;
import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.reportButtons;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.MESSAGE_INFO;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsShelters.GREETING_CAT_SHELTER;
import static com.example.telegramanimalshelterholiday.constants.InfoConstantsShelters.GREETING_DOG_SHELTER;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.*;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.BEHAVIOR;
import static com.example.telegramanimalshelterholiday.constants.MenuHeadings.*;
import static com.example.telegramanimalshelterholiday.constants.MenuHeadings.SEND_BEHAVIOR_REPORT;
import static com.example.telegramanimalshelterholiday.constants.Recommendation.INFO_ABOUT_ADOPTION;
import static com.example.telegramanimalshelterholiday.constants.ReportsConsts.REPORT_SAVED;
import static com.example.telegramanimalshelterholiday.constants.enums.BotState.*;

@Service
@RequiredArgsConstructor
public class ButtonClickService {


    private final MessageService messageService;
    private final HandlerVolunteer handlerVolunteer;
    private final HandlerFeedback handlerFeedback;
    private final HandlerShelterInfo handlerShelterInfo;
    private final HandlerBeforeAdoptionInfo handlerBeforeAdoptionInfo;
    private final UserDataCache userDataCache;
    private final HandlerReport handlerReport;



    /**
     * Process user's buttons clicks in bot. This method doesn't process anything but button clicks
     *
     * @param update
     * @see com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons
     */
    public void processButtonClick(Update update) throws IOException {
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
                    handlerVolunteer.callVolunteer(chatId);
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
                    botState = BotState.CHOICES_SHELTER;
                    break;

                case SAVE_ANYWAY:
                    messageService.sendMessage(chatId, REPORT_SAVED);
                    handlerReport.informVolunteerReportsAreIncomplete(chatId);
                    botState = BotState.CHOICES_SHELTER;
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
