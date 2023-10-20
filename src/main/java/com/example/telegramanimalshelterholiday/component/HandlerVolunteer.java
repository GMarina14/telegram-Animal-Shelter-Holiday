package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.model.Volunteer;
import com.example.telegramanimalshelterholiday.service.VolunteerService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.*;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class HandlerVolunteer {
    private static final Logger logger = LoggerFactory.getLogger(HandlerVolunteer.class);
    private final TelegramBot telegramBot;
    private final VolunteerService volunteerService;

    public void callVolunteer(Update update) {
        String userId = ""; // client's chat_id or username
        userId = userId + update.message().from().id();
        long chatId = 0; // volunteer's chat_id
        logger.info("UserId = {}", userId);
        Volunteer randomVolunteer = volunteerService.getRandomVolunteer();
        if (isNull(randomVolunteer)) {
            // Client chat_id. Send message to the client.
            chatId = Long.parseLong(userId);
            SendMessage message = new SendMessage(chatId, NO_VOLUNTEERS);
            sendMessage(message);
        } else {
            // Volunteer chat_id. Send message to volunteer.
            chatId = randomVolunteer.getChatId();
            if (update.message().from().username() != null) {
                userId =update.message().from().username();
                SendMessage message = new SendMessage(chatId, String.format(CONTACT_USERNAME, userId));
                sendMessage(message);
            } else {
                SendMessage message = new SendMessage(chatId, String.format(CONTACT_ID, userId));
                sendMessage(message);
            }
        }
    }

    private void sendMessage(SendMessage message) {
        SendResponse response = telegramBot.execute(message);
        if (!isNull(response) && !response.isOk()) {
            logger.warn("Message was not sent: {}, error code: {}", message, response.errorCode());
        }
    }

}
