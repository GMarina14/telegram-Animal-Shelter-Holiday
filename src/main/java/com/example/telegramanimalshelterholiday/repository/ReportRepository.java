package com.example.telegramanimalshelterholiday.repository;

import com.example.telegramanimalshelterholiday.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {


    /**
     * Getting all reports, sent in the terms of contract
     */
    public Optional<Collection<Report>> findAllByContractId(Long contractId);


    /**
     * Получение и просмотр отдельного отчета, направленного в рамках контракта
     */
    public Optional<Report> findByContractIdAndAndReportDate(Long contractId, LocalDate date);

    Optional<Collection<Report>> findReportByChatId(Long chatId);

    Report findByChatIdAndAndReportDate(Long chatId, LocalDate date);

    public Optional<Collection<Report>> findAllByReportDate(LocalDate date);


    Report findReportByContractId(Long contractId);



}
