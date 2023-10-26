package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.cache.UserDataCache;
import com.example.telegramanimalshelterholiday.constants.enums.Icon;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.*;

@Component
@RequiredArgsConstructor
public class InlineKeyBoardButtons {

    private static final Logger logger = LoggerFactory.getLogger(InlineKeyBoardButtons.class);

    /**
     * Creating buttons so user can choose the needed shelter (first menu)
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup firstMenuButtons(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton(Icon.CAT.getParse() + " " + CAT_SHELTER).callbackData(CAT_SHELTER);
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton(Icon.DOG.getParse() + " " + DOG_SHELTER).callbackData(DOG_SHELTER);

        // Добавляем кнопки в клавиатуру
        markupInline.addRow(inlineKeyboardButton1, inlineKeyboardButton2);

        return markupInline;
    }

    /**
     * Creating buttons so user can choose the needed section of the shelter (second menu)
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup secondMenuButtons(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки первого ряда
        InlineKeyboardButton firstShelterInfoButton = new InlineKeyboardButton(Icon.INFO.getParse() + " " + ALL_ABOUT_SHELTER).callbackData(ALL_ABOUT_SHELTER);
        InlineKeyboardButton howToAdoptButton = new InlineKeyboardButton(Icon.HOUSE.getParse() + " " + ADOPTION_INFO).callbackData(ADOPTION_INFO);

        // Добавляем кнопки в клавиатуру
        markupInline.addRow(firstShelterInfoButton, howToAdoptButton);

        // создаем второй ряд меню
        InlineKeyboardButton reportButton = new InlineKeyboardButton(Icon.REPORT.getParse() + " " + ADOPTION_REPORTS).callbackData(ADOPTION_REPORTS);
        InlineKeyboardButton volunteerButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + " " + CALL_VOLUNTEER).callbackData(CALL_VOLUNTEER);


        // добавляем кнопки

        markupInline.addRow(reportButton, volunteerButton);

        return markupInline;
    }

    /**
     * Creating buttons so user can choose the needed info to visit the shelter (third menu)
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup thirdMenuButtons(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки первого ряда
        InlineKeyboardButton mainShelterInfoButton = new InlineKeyboardButton(Icon.INFO.getParse() + " " + SHELTER_INFO).callbackData(SHELTER_INFO);
        InlineKeyboardButton securityMeasuresButton = new InlineKeyboardButton(Icon.WARNING.getParse() + " " + SECURITY_MEASURES).callbackData(SECURITY_MEASURES);

        // Добавляем кнопки в клавиатуру
        markupInline.addRow(mainShelterInfoButton, securityMeasuresButton);

        // создаем второй ряд меню
        InlineKeyboardButton carPassButton = new InlineKeyboardButton(Icon.CAR.getParse() + " " + CAR_PASS).callbackData(CAR_PASS);
        InlineKeyboardButton getContactsButton = new InlineKeyboardButton(Icon.PHONE.getParse() + " " + REACH_ME_BACK).callbackData(REACH_ME_BACK);

        // добавляем кнопки
        markupInline.addRow(carPassButton, getContactsButton);

        // создаем третий ряд меню
        InlineKeyboardButton volunteerButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + " " + CALL_VOLUNTEER).callbackData(CALL_VOLUNTEER);
        //  InlineKeyboardButton contactButton = new InlineKeyboardButton(Icon.CONTACT.getParse() + " " + BUTTON_SHARE_CONTACT).callbackData(BUTTON_SHARE_CONTACT);

        // добавляем кнопки
        markupInline.addRow(volunteerButton);

        return markupInline;
    }

    /**
     * Creating buttons so user can choose the needed info to get home with a pet from the shelter (fourth menu)
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup fourthMenuButtons(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки первого ряда
        InlineKeyboardButton meetAnimalButton = new InlineKeyboardButton(Icon.SPARKLES.getParse() + " " + FIRST_MEETING).callbackData(FIRST_MEETING);
        InlineKeyboardButton docsToAdopt = new InlineKeyboardButton(Icon.REPORT.getParse() + " " + DOCUMENTS_TO_ADOPT).callbackData(DOCUMENTS_TO_ADOPT);

        // добавляем кнопки
        markupInline.addRow(meetAnimalButton, docsToAdopt);


        // создаем второй ряд меню
        InlineKeyboardButton rejectionInfoButton = new InlineKeyboardButton(Icon.REJECTION.getParse() + " " + REJECTION_REASONS).callbackData(REJECTION_REASONS);
        InlineKeyboardButton homeAdjustmentButton = new InlineKeyboardButton(Icon.HOUSE.getParse() + " " + HOME_ADJUSTMENT).callbackData(HOME_ADJUSTMENT);

        // добавляем кнопки
        markupInline.addRow(rejectionInfoButton, homeAdjustmentButton);

        // создаем третий ряд меню
        InlineKeyboardButton transportationInfoButton = new InlineKeyboardButton(Icon.TRANSPORT.getParse() + " " + TRANSPORTATION_INFO).callbackData(TRANSPORTATION_INFO);
        InlineKeyboardButton getContactsButton = new InlineKeyboardButton(Icon.PHONE.getParse() + " " + REACH_ME_BACK).callbackData(REACH_ME_BACK);

        // добавляем кнопки
        markupInline.addRow(transportationInfoButton, getContactsButton);


        return markupInline;

    }


    //меню для собак
    public static InlineKeyboardMarkup fourthMenuButtonsDog(long chatId) {
        //Создаем объект разметки клавиатуры:

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки первого ряда
        InlineKeyboardButton meetAnimalButton = new InlineKeyboardButton(Icon.SPARKLES.getParse() + " " + FIRST_MEETING).callbackData(FIRST_MEETING);
        InlineKeyboardButton docsToAdopt = new InlineKeyboardButton(Icon.REPORT.getParse() + " " + DOCUMENTS_TO_ADOPT).callbackData(DOCUMENTS_TO_ADOPT);

        // добавляем кнопки
        markupInline.addRow(meetAnimalButton, docsToAdopt);


        // создаем второй ряд меню
        InlineKeyboardButton rejectionInfoButton = new InlineKeyboardButton(Icon.REJECTION.getParse() + " " + REJECTION_REASONS).callbackData(REJECTION_REASONS);
        InlineKeyboardButton homeAdjustmentButton = new InlineKeyboardButton(Icon.HOUSE.getParse() + " " + HOME_ADJUSTMENT).callbackData(HOME_ADJUSTMENT);

        // добавляем кнопки
        markupInline.addRow(rejectionInfoButton, homeAdjustmentButton);

        // создаем третий ряд меню
        InlineKeyboardButton transportationInfoButton = new InlineKeyboardButton(Icon.TRANSPORT.getParse() + " " + TRANSPORTATION_INFO).callbackData(TRANSPORTATION_INFO);
        InlineKeyboardButton getContactsButton = new InlineKeyboardButton(Icon.PHONE.getParse() + " " + REACH_ME_BACK).callbackData(REACH_ME_BACK);

        // добавляем кнопки
        markupInline.addRow(transportationInfoButton, getContactsButton);

        // создаем четвертый ряд меню, для ПРИЮТА СОБАК!!!
        InlineKeyboardButton volunteerButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + " " + CALL_VOLUNTEER).callbackData(CALL_VOLUNTEER);
        InlineKeyboardButton handlersRecommendationButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + Icon.DOG.getParse() + " " + DOG_HANDLER_RECOMMENDATIONS).callbackData(DOG_HANDLER_RECOMMENDATIONS);

        // добавляем кнопки
        markupInline.addRow(volunteerButton, handlersRecommendationButton);

        return markupInline;

    }


    /**
     * Creating buttons so user can choose the needed action to send the report (fifth menu)
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup fifthMenuButtons(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки первого ряда
        InlineKeyboardButton sendReportButton = new InlineKeyboardButton(Icon.REPORT.getParse() + " " + SEND_REPORT).callbackData(SEND_REPORT);
        InlineKeyboardButton sampleButton = new InlineKeyboardButton(Icon.SAMPLE.getParse() + " " + GET_REPORT_SAMPLE).callbackData(GET_REPORT_SAMPLE);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(sendReportButton, sampleButton);

        // создаем второй ряд меню
        InlineKeyboardButton volunteerButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + " " + CALL_VOLUNTEER).callbackData(CALL_VOLUNTEER);

        // добавляем кнопки
        markupInline.addRow(volunteerButton);

        return markupInline;
    }

    /**
     * Creating buttons so user can choose the age specification of a pet (additional menu)
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup catAgeMenuButtons(long chatId) {
        //Создаем объект разметки клавиатуры:

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки
        InlineKeyboardButton isKittyButton = new InlineKeyboardButton(Icon.KITTY_CAT.getParse() + " " + KITTY).callbackData(KITTY);
        InlineKeyboardButton isAdultCatButton = new InlineKeyboardButton(Icon.CAT.getParse() + " " + CAT).callbackData(PUPPY);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(isKittyButton, isAdultCatButton);

        return markupInline;
    }

    public static InlineKeyboardMarkup dogAgeMenuButtons(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки
        InlineKeyboardButton adultDogButton = new InlineKeyboardButton(Icon.DOG.getParse() + " " + DOG).callbackData(DOG);
        InlineKeyboardButton isPuppyButton = new InlineKeyboardButton(Icon.PUPPY_DOG.getParse() + " " + PUPPY).callbackData(PUPPY);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(adultDogButton, isPuppyButton);

        return markupInline;
    }

    /**
     * Creating buttons so user can choose the health specification of a pet (additional menu)
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup petSpecificMenuButtons(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки
        InlineKeyboardButton hasSightProblems = new InlineKeyboardButton(" " + SIGHT_PROBLEMS).callbackData(SIGHT_PROBLEMS);
        InlineKeyboardButton hasMobilityProblems = new InlineKeyboardButton(" " + MOBILITY_PROBLEMS).callbackData(MOBILITY_PROBLEMS);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(hasSightProblems, hasMobilityProblems);

        // создаем второй ряд меню
        InlineKeyboardButton isHealthy = new InlineKeyboardButton(" " + HEALTHY).callbackData(HEALTHY);

        // добавляем кнопки
        markupInline.addRow(isHealthy);

        return markupInline;
    }

    /**
     * Creating buttons so user can return on the main page or to the previous level of the menu
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup mainOrPreviousMenu(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки
        InlineKeyboardButton mainButton = new InlineKeyboardButton(Icon.MAIN.getParse() + " " + MAIN_PAGE).callbackData(MAIN_PAGE);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(mainButton);

        return markupInline;

    }

    /**
     * Creating buttons for dog adoption
     * Options to chose dog handlers
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup dogHandlers(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки
        InlineKeyboardButton firstMeetWithDogHandlers = new InlineKeyboardButton(Icon.FIRST.getParse() + " " + FIRST_HANDLER_DATE).callbackData(FIRST_HANDLER_DATE);
        InlineKeyboardButton listOfHandlers = new InlineKeyboardButton(Icon.REPORT.getParse() + " " + LIST_OF_HANDLERS).callbackData(LIST_OF_HANDLERS);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(firstMeetWithDogHandlers, listOfHandlers);

        return markupInline;
    }

    /**
     * Creating buttons for dog or cat report Samples
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup dogOrCatReportSample(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки
        InlineKeyboardButton dogsReports = new InlineKeyboardButton(Icon.CAT.getParse() + " " + CAT_REPORT).callbackData(CAT_REPORT);
        InlineKeyboardButton catsReports = new InlineKeyboardButton(Icon.DOG.getParse() + " " + DOG_REPORT).callbackData(DOG_REPORT);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(dogsReports, catsReports);

        return markupInline;

    }

    /**
     * Creating buttons for report sections
     *
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup reportButtons(long chatId) {
        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки
        InlineKeyboardButton photoButton = new InlineKeyboardButton(" " + PHOTO).callbackData(PHOTO);
        InlineKeyboardButton dietButton = new InlineKeyboardButton(" " + DIET).callbackData(DIET);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(photoButton, dietButton);

        // Кнопки
        InlineKeyboardButton behaviorButton = new InlineKeyboardButton(" " + BEHAVIOR).callbackData(BEHAVIOR);
        InlineKeyboardButton healthAndStateButton = new InlineKeyboardButton(" " + HEALTH_AND_STATE).callbackData(HEALTH_AND_STATE);


        // добавляем кнопки в том порядке,какой нам необходим
        markupInline.addRow(behaviorButton, healthAndStateButton);

        return markupInline;

    }

    /**
     * Creating buttons to save anyway or correct report
     * for incomplete reports
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup saveOrCorrectReport(long chatId) {

        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки
        InlineKeyboardButton saveAnywayButton = new InlineKeyboardButton(" " + SAVE_ANYWAY).callbackData(SAVE_ANYWAY);
        InlineKeyboardButton correctButton = new InlineKeyboardButton(" " + CORRECT_REPORT).callbackData(CORRECT_REPORT);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(saveAnywayButton, correctButton);

        return markupInline;

    }

    /**
     * Creating buttons to save anyway, call volunteer, cancel report
     * for incomplete reports
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup saveCAllVolunteerOrCancelReport(long chatId) {

        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки
        InlineKeyboardButton saveAnywayButton = new InlineKeyboardButton(" " + SAVE_ANYWAY).callbackData(SAVE_ANYWAY);
        InlineKeyboardButton correctButton = new InlineKeyboardButton(" " + CORRECT_REPORT).callbackData(CORRECT_REPORT);
        InlineKeyboardButton cancelButton = new InlineKeyboardButton(" " + CANCEL_REPORT).callbackData(CANCEL_REPORT);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(saveAnywayButton, correctButton, cancelButton);

        InlineKeyboardButton volunteerButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + " " + CALL_VOLUNTEER).callbackData(CALL_VOLUNTEER);
        InlineKeyboardButton mainButton = new InlineKeyboardButton(Icon.MAIN.getParse() + " " + MAIN_PAGE).callbackData(MAIN_PAGE);

        markupInline.addRow(volunteerButton, mainButton);

        return markupInline;

    }
    /**
     * Creating buttons for reports
     * @param chatId
     * @return {@code InlineKeyboardMarkup}
     */
    public static InlineKeyboardMarkup saveOrCancelReport(long chatId) {

        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем объект InlineKeyboardButton (текст на кнопке, CallBackData - что будет отсылатся серверу при нажатии на кнопку)
        // Кнопки
        InlineKeyboardButton saveButton = new InlineKeyboardButton(" " + SAVE_REPORT).callbackData(SAVE_REPORT);
        InlineKeyboardButton correctButton = new InlineKeyboardButton(" " + CORRECT_REPORT).callbackData(CORRECT_REPORT);
        InlineKeyboardButton cancelButton = new InlineKeyboardButton(" " + CANCEL_REPORT).callbackData(CANCEL_REPORT);

        // добавляем кнопки в первый ряд в том порядке,какой нам необходим
        markupInline.addRow(saveButton, correctButton, cancelButton);

        return markupInline;
    }
}
