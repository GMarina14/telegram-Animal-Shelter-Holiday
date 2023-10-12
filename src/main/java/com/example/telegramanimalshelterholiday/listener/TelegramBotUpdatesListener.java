package com.example.telegramanimalshelterholiday.listener;

import com.example.telegramanimalshelterholiday.constants.enums.Icon;
import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.constants.enums.MenuButtons;
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
import static com.example.telegramanimalshelterholiday.constants.enums.MenuButtons.*;
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

                    //     telegramBot.execute(sendInlineKeyBoardMessage(chatId));
                  //  telegramBot.execute(secondMenuInlineKeyBoardMessage(chatId));
                 //   telegramBot.execute(thirdMenuInlineKeyBoardMessage(chatId));
                    telegramBot.execute(fourthMenuInlineKeyBoardMessage(chatId));
                   // telegramBot.execute(fifthMenuInlineKeyBoardMessage(chatId));


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

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton(Icon.CAT.getParse() + " " + CAT_SHELTER).callbackData("Button \" CAT SHELTER\" has been pressed");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton(Icon.DOG.getParse() + " " + DOG_SHELTER).callbackData("Button \"DOG SHELTER\" has been pressed");

        // добавляем кнопки в первый ряд в том порядке,
        // какой нам необходим. В рассматриваемом случае ряд будет содержать 2 кнопки,
        // размер которых будет одинаково пропорционально растянут по ширине сообщения,
        // под которым клавиатура располагается

        rowInline1.add(inlineKeyboardButton1);
        rowInline1.add(inlineKeyboardButton2);

        markupInline.addRow(inlineKeyboardButton1, inlineKeyboardButton2);


        message.replyMarkup(markupInline);

        return message;
    }

    public static SendMessage secondMenuInlineKeyBoardMessage(long chatId) {
        // Создаем объект сообщения
        SendMessage message = new SendMessage(chatId, "Выберите интересующий Вас раздел");

        //Создаем обьект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем список списков кнопок, который впоследствии объединит ряды кнопок
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        // создаем список с кнопками для первого ряда
        List<InlineKeyboardButton> firstRowInline = new ArrayList<>();

        //Создаем объект InlineKeyboardButton, у которой есть 2 параметра:
        // Текст (Что будет написано на самой кнопке)
        // и CallBackData (Что будет отсылатся серверу при нажатии на кнопку).

        // передавая название энама сможем переходить по меню в свиче
        InlineKeyboardButton firstShelterInfoButton = new InlineKeyboardButton(Icon.INFO.getParse() + " " + ALL_ABOUT_SHELTER).callbackData(ALL_ABOUT_SHELTER.name());
        InlineKeyboardButton howToAdoptButton = new InlineKeyboardButton(Icon.HOUSE.getParse() + " " + ADOPTION_INFO).callbackData(ADOPTION_INFO.name());


        // добавляем кнопки в первый ряд в том порядке,
        // какой нам необходим. В рассматриваемом случае ряд будет содержать 2 кнопки,
        // размер которых будет одинаково пропорционально растянут по ширине сообщения,
        // под которым клавиатура располагается


        firstRowInline.add(firstShelterInfoButton);
        firstRowInline.add(howToAdoptButton);
        rowsInline.add(firstRowInline);

        markupInline.addRow(firstShelterInfoButton, howToAdoptButton);

        // создаем список с кнопками для второго ряда
        List<InlineKeyboardButton> secondRowInline = new ArrayList<>();


        // создаем второй ряд меню
        InlineKeyboardButton reportButton = new InlineKeyboardButton(Icon.REPORT.getParse() + " " + ADOPTION_REPORTS).callbackData(ADOPTION_REPORTS.name());
        InlineKeyboardButton volunteerButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + " " + CALL_VOLUNTEER).callbackData(CALL_VOLUNTEER.name());

        // добавляем кнопки
        secondRowInline.add(reportButton);
        secondRowInline.add(volunteerButton);
        rowsInline.add(secondRowInline);

        markupInline.addRow(reportButton, volunteerButton);

        message.replyMarkup(markupInline);

        return message;
    }

    public static SendMessage thirdMenuInlineKeyBoardMessage(long chatId) {
        // Создаем объект сообщения
        SendMessage message = new SendMessage(chatId, "Выберите интересующую Вас информацию о приюте");

        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем список списков кнопок, который впоследствии объединит ряды кнопок
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        // создаем список с кнопками для первого ряда
        List<InlineKeyboardButton> firstRowInline = new ArrayList<>();

        //Создаем объект InlineKeyboardButton, у которой есть 2 параметра:
        // Текст (Что будет написано на самой кнопке)
        // и CallBackData (Что будет отсылатся серверу при нажатии на кнопку).

        // передавая название энама сможем переходить по меню в свиче
        InlineKeyboardButton mainShelterInfoButton = new InlineKeyboardButton(Icon.INFO.getParse() + " " + SHELTER_INFO).callbackData(SHELTER_INFO.name());
        InlineKeyboardButton securityMeasuresButton = new InlineKeyboardButton(Icon.WARNING.getParse() + " " + SECURITY_MEASURES).callbackData(SECURITY_MEASURES.name());


        // добавляем кнопки в первый ряд в том порядке,
        // какой нам необходим. В рассматриваемом случае ряд будет содержать 2 кнопки,
        // размер которых будет одинаково пропорционально растянут по ширине сообщения,
        // под которым клавиатура располагается
        firstRowInline.add(mainShelterInfoButton);
        firstRowInline.add(securityMeasuresButton);
        rowsInline.add(firstRowInline);

        markupInline.addRow(mainShelterInfoButton, securityMeasuresButton);

        // создаем список с кнопками для второго ряда
        List<InlineKeyboardButton> secondRowInline = new ArrayList<>();

        InlineKeyboardButton carPassButton = new InlineKeyboardButton(Icon.CAR.getParse() + " " + CAR_PASS).callbackData(CAR_PASS.name());
        InlineKeyboardButton getContactsButton = new InlineKeyboardButton(Icon.PHONE.getParse() + " " + REACH_ME_BACK).callbackData(REACH_ME_BACK.name());
        // добавляем кнопки
        secondRowInline.add(carPassButton);
        secondRowInline.add(getContactsButton);
        rowsInline.add(secondRowInline);

        markupInline.addRow(carPassButton, getContactsButton);

        // создаем третий ряд меню
        List<InlineKeyboardButton> thirdRowInline = new ArrayList<>();
        InlineKeyboardButton volunteerButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + " " + CALL_VOLUNTEER).callbackData(CALL_VOLUNTEER.name());

        // добавляем кнопки

        thirdRowInline.add(volunteerButton);
        rowsInline.add(thirdRowInline);

        markupInline.addRow(volunteerButton);

        message.replyMarkup(markupInline);

        return message;
    }
    public static SendMessage fourthMenuInlineKeyBoardMessage(long chatId) {
        // Создаем объект сообщения
        SendMessage message = new SendMessage(chatId, "Раздел консультации для усыновления из приюта");

        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем список списков кнопок, который впоследствии объединит ряды кнопок
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        // создаем список с кнопками для первого ряда
        List<InlineKeyboardButton> firstRowInline = new ArrayList<>();

        //Создаем объект InlineKeyboardButton, у которой есть 2 параметра:
        // Текст (Что будет написано на самой кнопке)
        // и CallBackData (Что будет отсылатся серверу при нажатии на кнопку).

        // передавая название энама сможем переходить по меню в свиче
        InlineKeyboardButton meetAnimalButton = new InlineKeyboardButton(Icon.SPARKLES.getParse()+ " " + FIRST_MEETING).callbackData(FIRST_MEETING.name());
        InlineKeyboardButton transportationInfoButton = new InlineKeyboardButton(Icon.TRANSPORT.getParse() + " " + TRANSPORTATION_INFO).callbackData(TRANSPORTATION_INFO.name());

        // добавляем кнопки в первый ряд в том порядке,
        // какой нам необходим. В рассматриваемом случае ряд будет содержать 2 кнопки,
        // размер которых будет одинаково пропорционально растянут по ширине сообщения,
        // под которым клавиатура располагается
        firstRowInline.add(meetAnimalButton);
        firstRowInline.add(transportationInfoButton);
        rowsInline.add(firstRowInline);

        markupInline.addRow(meetAnimalButton, transportationInfoButton);

        // создаем список с кнопками для второго ряда
        List<InlineKeyboardButton> secondRowInline = new ArrayList<>();

        InlineKeyboardButton rejectionInfoButton = new InlineKeyboardButton(Icon.REJECTION.getParse() + " " + REJECTION_REASONS).callbackData(REJECTION_REASONS.name());
        InlineKeyboardButton homeAdjustmentButton = new InlineKeyboardButton(Icon.HOUSE.getParse() + " " + HOME_ADJUSTMENT).callbackData(HOME_ADJUSTMENT.name());
        // добавляем кнопки
        secondRowInline.add(rejectionInfoButton);
        secondRowInline.add(homeAdjustmentButton);
        rowsInline.add(secondRowInline);

        markupInline.addRow(rejectionInfoButton, homeAdjustmentButton);

        // создаем третий ряд меню
        List<InlineKeyboardButton> thirdRowInline = new ArrayList<>();
        InlineKeyboardButton volunteerButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + " " + CALL_VOLUNTEER).callbackData(CALL_VOLUNTEER.name());
        InlineKeyboardButton getContactsButton = new InlineKeyboardButton(Icon.PHONE.getParse() + " " + REACH_ME_BACK).callbackData(REACH_ME_BACK.name());


        // добавляем кнопки
        thirdRowInline.add(volunteerButton);
        thirdRowInline.add(getContactsButton);
        rowsInline.add(thirdRowInline);

        markupInline.addRow(volunteerButton, getContactsButton);

        // создаем четвертый ряд меню, если выбрали ПРИЮТ СОБАК!!!
        List<InlineKeyboardButton> fourthRowInline = new ArrayList<>();
        InlineKeyboardButton handlersRecommendationButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + Icon.DOG.getParse()+" "+DOG_HANDLER_RECOMMENDATIONS).callbackData(DOG_HANDLER_RECOMMENDATIONS.name());
        // добавляем кнопки
        fourthRowInline.add(handlersRecommendationButton);
        rowsInline.add(fourthRowInline);

        markupInline.addRow(handlersRecommendationButton);

        message.replyMarkup(markupInline);

        return message;
    }







    public static SendMessage fifthMenuInlineKeyBoardMessage(long chatId) {
        // Создаем объект сообщения
        SendMessage message = new SendMessage(chatId, "Раздел ежедневных отчетов о состоянии питомцев");

        //Создаем объект разметки клавиатуры:
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // Создаем список списков кнопок, который впоследствии объединит ряды кнопок
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        // создаем список с кнопками для первого ряда
        List<InlineKeyboardButton> firstRowInline = new ArrayList<>();

        //Создаем объект InlineKeyboardButton, у которой есть 2 параметра:
        // Текст (Что будет написано на самой кнопке)
        // и CallBackData (Что будет отсылатся серверу при нажатии на кнопку).

        // передавая название энама сможем переходить по меню в свиче
        InlineKeyboardButton sendReportButton = new InlineKeyboardButton(Icon.REPORT.getParse() + " " + SEND_REPORT).callbackData(SEND_REPORT.name());
        InlineKeyboardButton sampleButton = new InlineKeyboardButton(Icon.SAMPLE.getParse() + " " + GET_REPORT_SAMPLE).callbackData(GET_REPORT_SAMPLE.name());

        // добавляем кнопки в первый ряд в том порядке,
        // какой нам необходим. В рассматриваемом случае ряд будет содержать 2 кнопки,
        // размер которых будет одинаково пропорционально растянут по ширине сообщения,
        // под которым клавиатура располагается
        firstRowInline.add(sendReportButton);
        firstRowInline.add(sampleButton);
        rowsInline.add(firstRowInline);
        markupInline.addRow(sendReportButton, sampleButton);


        List<InlineKeyboardButton> secondRowInline = new ArrayList<>();
        InlineKeyboardButton volunteerButton = new InlineKeyboardButton(Icon.VOLUNTEER.getParse() + " " + CALL_VOLUNTEER).callbackData(CALL_VOLUNTEER.name());

        secondRowInline.add(volunteerButton);
        rowsInline.add(secondRowInline);
        markupInline.addRow(volunteerButton);

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





