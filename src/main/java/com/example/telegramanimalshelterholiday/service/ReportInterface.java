package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Report;
import com.example.telegramanimalshelterholiday.model.Volunteer;

import java.time.LocalDate;
import java.util.Collection;

public interface ReportInterface {

     Report createReport(Long contractId, Report report);
     Collection<Report> getAllReports(Long contractId);

     Report getReport(Long contractId, LocalDate date);

     Volunteer callVolunteer(Long contractId);
}
