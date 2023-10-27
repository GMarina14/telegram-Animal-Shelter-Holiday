package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Report;
import com.example.telegramanimalshelterholiday.service.serviceImpl.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("cat/shelter/report")
public class CatReportController {

    private final ReportService reportService;

    public CatReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(
            summary = "Создание и направление ежедневного отчета ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ежедневный отчет сохранен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report.class)
                            )
                    )
            },
            tags = "Ежедневные отчеты",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Характеристики нового ежедневного отчета",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Report.class)
                    )
            )
    )
    @PostMapping(value = "/{contractId}/sendReport")
    public Report createNewReport(@PathVariable long contractId, @RequestBody Report report) {

        return reportService.createReport(contractId, report);
    }

    @Operation(
            summary = "Получение списка всех направленных отчетов в рамках контракта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ежедневные отчеты найдены",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ежедневные отчеты не найдены",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report.class)
                            )
                    )
            },
            tags = "Ежедневные отчеты"
    )
    @GetMapping(value = "/{contractId}/all")
    public ResponseEntity<Collection<Report>> getAllReports(@PathVariable long contractId) {
        Collection<Report> reports = reportService.getAllReports(contractId);
        if (reports.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reports);
    }

    @Operation(
            summary = "Получение отчета, направленного в рамках контракта, по дате",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ежедневный отчет найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ежедневный отчет не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report.class)
                            )
                    )
            },
            tags = "Ежедневные отчеты"
    )
    @GetMapping(value = "/{contractId}/{date}")
    public ResponseEntity<Report> getReportByDate(@PathVariable long contractId, @PathVariable LocalDate date) {
       Report report = reportService.getReport(contractId, date);
       if(report==null)
           return ResponseEntity.notFound().build();
        return ResponseEntity.ok(report);
    }
}
