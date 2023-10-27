package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.exception.NotFoundConfigException;
import com.example.telegramanimalshelterholiday.model.Volunteer;
import com.example.telegramanimalshelterholiday.service.MessageService;
import com.example.telegramanimalshelterholiday.service.VolunteerService;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.example.telegramanimalshelterholiday.constants.InfoConstantsMessageBot.FEED_BACK_WILL_BE_PROVIDED;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.CONTACT_USER;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.NO_VOLUNTEERS;

@Component
@RequiredArgsConstructor
public class HandlerVolunteer {
    private static final Logger logger = LoggerFactory.getLogger(HandlerVolunteer.class);
    private final VolunteerService volunteerService;
    private final MessageService messageService;

    /**
     * This method calls volunteer
     * @param chatIdC
     */
    public void callVolunteer(long chatIdC) {

        logger.info("UserId = {}", chatIdC);
        Volunteer randomVolunteer;

        try {
            randomVolunteer = volunteerService.getRandomVolunteer();
        } catch (NotFoundConfigException e) {
            logger.error("Volunteer not found");
            System.out.println(e.getMessage());
            System.out.println("Exception was processed. Program continues");
            messageService.sendMessage(chatIdC, NO_VOLUNTEERS);
            return;
        }
        messageService.sendMessage(randomVolunteer.getChatId(), String.format(CONTACT_USER, chatIdC));
        messageService.sendMessage(chatIdC, FEED_BACK_WILL_BE_PROVIDED);
    }
}
