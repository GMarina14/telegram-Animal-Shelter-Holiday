package com.example.telegramanimalshelterholiday.component;

import com.example.telegramanimalshelterholiday.exception.ReportNotFoundException;
import com.example.telegramanimalshelterholiday.model.Contract;
import com.example.telegramanimalshelterholiday.model.Report;
import com.example.telegramanimalshelterholiday.model.Volunteer;
import com.example.telegramanimalshelterholiday.repository.ContractRepository;
import com.example.telegramanimalshelterholiday.repository.ReportRepository;
import com.example.telegramanimalshelterholiday.service.ContractService;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.reportButtons;
import static com.example.telegramanimalshelterholiday.component.InlineKeyBoardButtons.saveOrCorrectReport;
import static com.example.telegramanimalshelterholiday.constants.MenuButtonsConst.*;
import static com.example.telegramanimalshelterholiday.constants.MenuHeadings.REPORT_SECTIONS;
import static com.example.telegramanimalshelterholiday.constants.MenuHeadings.SAVE_ANYWAY_CORRECT;
import static com.example.telegramanimalshelterholiday.constants.ReportsConsts.*;

@Component
@RequiredArgsConstructor
public class HandlerReport {
    private static final Logger logger = LoggerFactory.getLogger(com.example.telegramanimalshelterholiday.component.HandlerReport.class);
    private final VolunteerService volunteerService;
    private final MessageService messageService;
    private final TelegramBot telegramBot;
    private final ContractRepository contractRepository;
    private final ReportRepository reportRepository;


    /**
     * Method creates report with empty fields if adopter presses send report
     *
     * @param chatId
     */
    public void createReport(long chatId) {
        Report report = new Report();

        LocalDate date = LocalDate.now();
        Contract contractId = contractRepository.findContractByChatId(chatId);

        report.setReportDate(date);
        report.setContract(contractId);
        report.setChatId(chatId);

        reportRepository.save(report);


        messageService.sendMessage(chatId, REPORT_CREATING_RULES);
    }

    /**
     * Method checks if everything ok and saves report
     */
    public void saveReport(long chatId) {

        checkComplete(chatId);

        Report report = reportRepository.findByChatIdAndAndReportDate(chatId, LocalDate.now());

        reportRepository.save(report);

        messageService.sendMessage(chatId, REPORT_SAVED);
    }

    /**
     * Method gets diet information and saves it as a part of a daily report
     *
     * @param update
     */
    public void getDietInfo(Update update) {
        long chatId = update.message().chat().id();
        reportExists(chatId);

        Report report = reportRepository.findByChatIdAndAndReportDate(chatId, LocalDate.now());

        String diet = update.message().text();
        report.setDiet(diet);
        reportRepository.save(report);

        messageService.sendMessage(chatId, REPORT_DIET_IS_SAVED);
    }

    /**
     * Method gets information about health state of a pet as part of a daily report
     *
     * @param update
     */
    public void getStateOfHealth(Update update) {
        long chatId = update.message().chat().id();
        reportExists(chatId);

        Report report = reportRepository.findByChatIdAndAndReportDate(chatId, LocalDate.now());

        String stateOfHealth = update.message().text();

        report.setStateOfHealth(stateOfHealth);
        reportRepository.save(report);

        messageService.sendMessage(chatId, REPORT_HEALTH_AND_STATE_IS_SAVED);

    }

    /**
     * Method gets information about behavior and some changes in it of a pet as part of a daily report
     *
     * @param update
     */
    public void getBehavior(Update update) {
        long chatId = update.message().chat().id();
        reportExists(chatId);

        Report report = reportRepository.findByChatIdAndAndReportDate(chatId, LocalDate.now());

        String behavior = update.message().text();
        report.setBehavior(behavior);

        reportRepository.save(report);

        messageService.sendMessage(chatId, REPORT_BEHAVIOR_IS_SAVED);

    }

    /**
     * Method gets a photo of a pet and saves it as a part of a daily report
     *
     * @param update
     */
    public void saveReportPhoto(Update update) {
        long chatId = update.message().chat().id();
        reportExists(chatId);
        Report report = reportRepository.findByChatIdAndAndReportDate(chatId, LocalDate.now());
        if (update.message().photo() != null) {
            byte[] photo = getPhoto(update);
            report.setPhoto(photo);
            reportRepository.save(report);

            messageService.sendMessage(chatId, REPORT_PHOTO_IS_SAVED);
        }


    }


    /**
     * this method receives a photo of a pet as a part of the daily report
     *
     * @param update
     * @link http://core.telegram.org/bots/api#getfile
     */
    public byte[] getPhoto(Update update) {


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
                        byte[] pic = telegramBot.getFileContent(getFileResponse.file()); //Files.readAllBytes(Path.of(getFilePath))
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
     * Method deletes report if adopter presses cancel
     */
    public void cancelReport(long chatId) {
        Report report = reportRepository.findByChatIdAndAndReportDate(chatId, LocalDate.now());

        reportRepository.delete(report);

        messageService.sendMessage(chatId, REPORT_CANCEL);
    }

    /**
     * This method checks if report was created previously
     *
     * @param chatId
     */
    private void reportExists(long chatId) {
        Report report = reportRepository.findByChatIdAndAndReportDate(chatId, LocalDate.now());
        if (report == null) {
            logger.error("Report wasn't created yet");
            messageService.sendMessage(chatId, "Отчет не был создан, пожалуйста, вернитесь в необходимое меню и создайте отчет");
            throw new ReportNotFoundException();
        }
    }

    /**
     * This method sends a sample of a daily report
     *
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
                messageService.sendMessage(chatId, CAT_BEHAVIOR_EXAMPLE);
                break;

            case DOG_REPORT:
                sendPhoto(chatId, urlDog);
                messageService.sendMessage(chatId, DOG_DIET_EXAMPLE);
                messageService.sendMessage(chatId, DOG_STATE_OF_HEALTH_EXAMPLE);
                messageService.sendMessage(chatId, DOG_BEHAVIOR_EXAMPLE);
                break;
        }
    }

    /**
     * This method send an example of a photo as a part of a daily report
     *
     * @param chatId
     * @throws IOException
     */
    private void sendPhoto(long chatId, URL url) throws IOException {
        String caption = "Example photo";

        Image image = ImageIO.read(url);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, "jpg", bos);
        byte[] photo = bos.toByteArray();

        SendPhoto sendPhoto = new SendPhoto(chatId, photo);
        telegramBot.execute(sendPhoto);
    }

    /**
     * this method checks if the report is complete and sends notifications otherwise
     *
     * @param chatId
     */
    public void checkComplete(long chatId) {
        Report report = reportRepository.findByChatIdAndAndReportDate(chatId, LocalDate.now());
        if (report == null) {
            messageService.sendMessage(chatId, REPORT_IS_EMPTY);

        } else if (report.getPhoto() == null || report.getDiet() == null || report.getBehavior() == null) {
            messageService.sendMessage(chatId, INCOMPLETE_REPORT_NOTIFICATION);
            messageService.sendMessage(chatId, saveOrCorrectReport(chatId), SAVE_ANYWAY_CORRECT);

            //messageService.sendMessage(chatId, reportButtons(chatId), REPORT_SECTIONS);
        }
    }

    /**
     * This method informs volunteer if incomplete report was saved
     *
     * @param chatId
     */

    public void informVolunteerReportsAreIncomplete(long chatId) {
        Volunteer randomVolunteer = volunteerService.getRandomVolunteer();
        messageService.sendMessage(randomVolunteer.getChatId(), String.format(INFORM_VOLUNTEER_REPORT_INCOMPLETE, chatId));
    }

    /**
     * This method checks everyday all reports, if they are sent in time and if not, informs a volunteer
     */
    @Scheduled(cron = "0 00 00 * * ?")
    private void controlIfReportsAreSent() {
        logger.info("Method to check if all reports are sent in time is called");
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate dayBeforeYesterday = today.minusDays(2);

        // Getting all contracts of adoption
        Collection<Contract> contracts = contractRepository.findAll();

        // getting all reports through contracts and checking if reports are sent yesterday and the day before
        for (Contract contract : contracts) {
            Long chatId = contract.getChatId();
            LocalDate contactDate = contract.getContractDate();
            LocalDate probExpired = contactDate.plusDays(contract.getProbation());
            // if contracts were signed today, yesterday or a day  before yesterday, there is no point to check if reports are sent
            if (contract.getContractDate().equals(today) || contract.getContractDate().equals(yesterday) || contract.getContractDate().equals(dayBeforeYesterday)) {
                return;
            } else if (today.isAfter(probExpired)) {
                return;
            } else if (!(contract.getReportList().contains(reportRepository.findByChatIdAndAndReportDate(chatId, yesterday)))) {
                logger.info("Checking if the list of reports of an exact contract doesnt contain yesterday and day before reports");
                messageService.sendMessage(chatId, INCOMPLETE_REPORT_NOTIFICATION);
                if (!(contract.getReportList().contains(reportRepository.findByChatIdAndAndReportDate(chatId, dayBeforeYesterday)))) {
                    logger.info("Contacting with volunteer because reports weren't sent");
                    Volunteer randomVolunteer = volunteerService.getRandomVolunteer();
                    messageService.sendMessage(randomVolunteer.getChatId(), String.format(REPORTS_ARE_NOT_SENT, chatId));
                }
            }
        }

    }

    /**
     * this method helps to control the probation period
     */
    @Scheduled(cron = "0 00 00 * * ?")
    private void controlPassingProbation() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        // Getting all contracts of adoption
        Collection<Contract> contracts = contractRepository.findAll();

        for (Contract contract : contracts) {
            Long chatId = contract.getChatId();
            LocalDate contactDate = contract.getContractDate();
            LocalDate probExpired = contactDate.plusDays(contract.getProbation());
            Volunteer randomVolunteer = volunteerService.getRandomVolunteer();

            logger.info("Checking if someones probation is over");
            if (yesterday.equals(probExpired)) {
                messageService.sendMessage(chatId, PROBATION_IS_OVER);
                messageService.sendMessage(randomVolunteer.getChatId(), String.format(INFORM_VOLUNTEER_PROBATION_IS_OVER, chatId));
            } else if (today.equals(probExpired)) {
                messageService.sendMessage(randomVolunteer.getChatId(), String.format(ASK_VOLUNTEER_ABOUT_PROBATION, chatId, contract.getId()));
            }
        }
    }

    /**
     * This method checks everyday if probation was extended or failed and informs the adopters about it
     * if probation was failed, bot sends instructions how to return a pet to the shelter
     */
    @Scheduled(cron = "0 00 00 * * ?")
    private void informIfExtendedOrFailedProbation() {
        LocalDate today = LocalDate.now();

        // Getting all contracts of adoption
        Collection<Contract> contracts = contractRepository.findAll();

        for (Contract contract : contracts) {

            Long chatId = contract.getChatId();
            Integer contractProbation = contract.getProbation();

            if (contractProbation > 30) {
                Integer probationExtendShort = 44;
                Integer probationExtendLong = 60;
                LocalDate exactDateAfterShortExt = contract.getContractDate().plusDays(probationExtendShort);
                LocalDate exactDateAfterLongExt = contract.getContractDate().plusDays(probationExtendLong);

                if (today.equals(exactDateAfterShortExt)) {
                    messageService.sendMessage(chatId, PROBATION_EXTENDED_FOR_14);
                } else if (today.equals(exactDateAfterLongExt)) {
                    messageService.sendMessage(chatId, PROBATION_EXTENDED_FOR_30);
                }
            } else if (contractProbation == 0) {
                messageService.sendMessage(chatId, PROBATION_FAILED);
                Volunteer randomVolunteer = volunteerService.getRandomVolunteer();
                messageService.sendMessage(randomVolunteer.getChatId(), String.format(INFORM_VOLUNTEER_PROBATION_FAILED, chatId, contract.getId()));
            }
        }
    }
}
