package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Report;
import com.example.telegramanimalshelterholiday.model.Volunteer;
import org.slf4j.Logger;
import com.example.telegramanimalshelterholiday.repository.ReportRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;


@Service
public class ReportService implements ReportInterface {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(ReportService.class);

    /**
     * Метод создания и отправки ежедневного отчета через id контракта для обеспечения однозначной связи с животным и усыновителем
     *
     * @param contractId
     * @param report
     * @return
     */
    public Report createReport(Long contractId, Report report) {
        logger.info("Calling method of report creation");
        // Нужна проверка наличия контракта с таким номером через репозиторий контракта с выбросом исключения

        return null;
               // reportRepository.save(report);
    }


    /**
     * Метод предоставления всего списка отправленных отчетов по отдельному контракту.
     * Позваоляет проследить за изменениями, происходящими с животным
     *
     * @param contractId
     * @return
     */

    public Collection<Report> getAllReports(Long contractId) {
        logger.info("Calling method to get all reports, sent in the terms of contract");

        return null;
        //reportRepository.findAllByContractId(contractId).orElseThrow(() -> new NotFoundException("contract id not found"));
    }


    /**
     * Получение и просмотр отдельного отчета, направленного в рамках контракта
     *
     * @param contractId
     * @param date
     * @return
     */
    public Report getReport(Long contractId, LocalDate date) {
        logger.info("Method to get an exact report, sent " + date.toString());

        return null;
               // reportRepository.findByContractIdAndAndReportDate(contractId, date).orElseThrow(() -> new NotFoundException("report with these contract id or date can not be found"));
    }


    /**
     * В случае возникновения каких-то вопросов, метод позволяет вызвать волонтера, закрепленного по конкретному контракту
     *
     * @param contractId
     * @return
     */
    public Volunteer callVolunteer(Long contractId) {
        logger.info("Method to call volunteer if something went wrong");
        // нужен репозиторий контракта, чтобы возвращать сущность волонтера контракта

        return null;


    }
}
