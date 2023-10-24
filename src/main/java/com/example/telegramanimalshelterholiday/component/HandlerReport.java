package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.model.Report;
import com.example.telegramanimalshelterholiday.service.MessageService;
import com.example.telegramanimalshelterholiday.service.VolunteerService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.CAT_REPORT;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.DOG_REPORT;
import static com.example.telegramanimalshelterholiday.constants.ReportsConsts.*;

@Component
@RequiredArgsConstructor
public class HandlerReport {
    private static final Logger logger = LoggerFactory.getLogger(com.example.telegramanimalshelterholiday.component.HandlerReport.class);
    private final VolunteerService volunteerService;
    private final MessageService messageService;
    private final TelegramBot telegramBot;


    public void saveReport(Update update) {
        LocalDate date = LocalDate.now();


        // если состояние бота такое, то обработка ответа пользователя передается как номер контракта

    }


    public void updateReport(Update update) {


    }


    private void getDietInfo(Update update) {
        long chatId = update.message().chat().id();


    }

    private void getStateOfHealth(Update update) {


    }


    /**
     * this method receives a photo of a pet as a part of the daily report
     *
     * @param update
     * @link http://core.telegram.org/bots/api#getfile
     */
    private byte[] getPhoto(Update update) {
        if (update.message().photo() != null) {
            logger.info("Method to save photo to DB was invoked");


            // photos are represented as an arrays of PhotoSize in telegram
            PhotoSize[] photo = update.message().photo();
            //    update.Message.Photo.Last().FileId;
            for (PhotoSize photoS : photo) {
                GetFile getField = new GetFile(photoS.fileId()); // Identifier for this file, which can be used to download or reuse the file

                // <file_path> is taken from the response
                GetFileResponse getFileResponse = telegramBot.execute(getField);

                //if success telegram returns File object
                if (getFileResponse.isOk()) {
                    File file = getFileResponse.file();
                    String getFilePath = getFileResponse.file().filePath(); // File path Use https://api.telegram.org/file/bot<token>/<file_path> to get the file

                    try {
                        byte[] pic = telegramBot.getFileContent(file);
                        return pic;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return null;
    }

    /**
     * This method sends a sample of a daily report
     * @param chatId
     * @param catOrDog
     * @throws IOException
     */
    public void sendReportSample(long chatId, String catOrDog) throws IOException {
        URL urlCat = new URL("https://images.unsplash.com/photo-1608848461950-0fe51dfc41cb?auto=format&fit=crop&q=80&w=1000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D");
        URL urlDog = new URL("https://images.unsplash.com/photo-1611003229186-80e40cd54966?auto=format&fit=crop&q=80&w=1000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8YmFieSUyMGRvZ3xlbnwwfHwwfHx8MA%3D%3D");

        messageService.sendMessage(chatId, REPORT_HEADER_EXAMPLE);

        switch (catOrDog) {
            case CAT_REPORT:
                sendPhoto(chatId, urlCat);
                messageService.sendMessage(chatId, CAT_DIET_EXAMPLE);
                messageService.sendMessage(chatId, CAT_STATE_OF_HEALTH_EXAMPLE);
                messageService.sendMessage(chatId,CAT_BEHAVIOR_EXAMPLE);
                break;

            case DOG_REPORT:
                sendPhoto(chatId, urlDog);
                messageService.sendMessage(chatId, DOG_DIET_EXAMPLE);
                messageService.sendMessage(chatId, DOG_STATE_OF_HEALTH_EXAMPLE);
                messageService.sendMessage(chatId,DOG_BEHAVIOR_EXAMPLE);
                break;
        }
    }

    /**
     * This method send an example of a photo as a part of a daily report
     *
     * @param chatId
     * @throws IOException
     */
    public void sendPhoto(long chatId, URL url) throws IOException {
        String caption = "Example photo";

        Image image = ImageIO.read(url);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, "jpg", bos);
        byte[] photo = bos.toByteArray();

        SendPhoto sendPhoto = new SendPhoto(chatId, photo);
        telegramBot.execute(sendPhoto);
    }

}