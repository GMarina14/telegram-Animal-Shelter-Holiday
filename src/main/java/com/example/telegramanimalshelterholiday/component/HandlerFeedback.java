package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.exception.NotFoundConfigException;
import com.example.telegramanimalshelterholiday.model.Volunteer;
import com.example.telegramanimalshelterholiday.service.MessageService;
import com.example.telegramanimalshelterholiday.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.FEED_BACK_WILL_BE_PROVIDED;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.CONTACT_USER;

@Component
@RequiredArgsConstructor
public class HandlerFeedback {
    private static final Logger logger = LoggerFactory.getLogger(HandlerFeedback.class);
    private final VolunteerService volunteerService;
    private final MessageService messageService;

    /**
     * If the client doesn't find needed info via telegram bot
     * these form gathers info about the client and provides it to a random volunteer,
     * so the last one can reach back and help
     *
     * @param chatId
     */
    public void reachBackClient(long chatId) {
        logger.info("UserId = {}", chatId);
        Volunteer randomVolunteer;
        try {
            randomVolunteer = volunteerService.getRandomVolunteer();
        } catch (NotFoundConfigException e) {
            logger.error("Volunteer not found");
            System.out.println(e.getMessage());
            System.out.println("Exception was processed. Program continues");
            return;
        } finally {
            messageService.sendMessage(chatId, FEED_BACK_WILL_BE_PROVIDED);
        }
        messageService.sendMessage(randomVolunteer.getChatId(), String.format(CONTACT_USER, chatId));
    }


}
