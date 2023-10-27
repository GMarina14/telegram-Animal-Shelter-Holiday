package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.model.Shelter;
import com.example.telegramanimalshelterholiday.service.AdoptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat/shelter/adoption")
public class AdoptionCatController {

    private final AdoptionService adoptionService;


    public AdoptionCatController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }


    @Operation(
            summary = "Получение информации о том как взять животное",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация об усыновлении",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Информация об усыновлении не найдена",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Информация об усыновлении"
    )
    @GetMapping("/info")
    public String getInfoAboutAdoption() {
        return adoptionService.getInfoAboutAdoption();
    }


    @Operation(
            summary = "Получение информации о первом знакомстве с животным",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о встрече с животным",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Информация о встрече с животным не найдена",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Информация о встрече с животным"
    )
    @GetMapping("/first-meet")
    public String getInfoAboutFirstMeet() {
        return adoptionService.getInfoAboutFirstMeetWithCat();
    }

    @Operation(
            summary = "Получение списка документов о том, как усыновить животное",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Документы для усыновления",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Документы для усыновления не найдены",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Документы для усыновления"
    )
    @GetMapping("/docs-for-adoption")
    public String getInfoAboutDocsForAdoption() {
        return adoptionService.getInfoAboutDocsForAdoption();
    }


    @Operation(
            summary = "Получение списка рекомендаций по транспортировке кота",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список рекомендаций по транспортировке",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список рекомендаций по транспортировке не найден",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Список рекомендаций по транспортировке"

    )
    @GetMapping("/recommendation/transportation")
    public String getInfoAboutTransportation() {
        return adoptionService.getInfoAboutCatTransportation();
    }

    @Operation(
            summary = "Получение списка рекомендаций по обустройству дома для котенка",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список рекомендаций по обустройству дома для котенка",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список рекомендаций по обустройству дома для котенка не найден",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Список рекомендаций по обустройству дома для котенка"

    )
    @GetMapping("/recommendation/home-adgustment-kitty")
    public String getInfoAboutAdgustmentKitty() {
        return adoptionService.getInfoAboutAdgustmentKitty();
    }

    @Operation(
            summary = "Получение списка рекомендаций по обустройству дома для взрослого кота",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список рекомендаций по обустройству дома для взрослого кота",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список рекомендаций по обустройству дома для взрослого кота не найден",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Список рекомендаций по обустройству дома для взрослого кота"

    )
    @GetMapping("/reccomendation/home-adgustment-cat")
    public String getInfoAboutAdgustmentCat() {
        return adoptionService.getInfoAboutAdgustmentCat();
    }
    @Operation(
            summary = "Получение списка рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение)",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение) не найден",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение)"

    )
    @GetMapping("/reccomendation/home-adgustment-unhealthy-sight")
    public String getInfoAboutAdgustmentUnhealthyWithSightProblems() {
        return adoptionService.getInfoAboutAdgustmentUnhealthyWithSightProblems();
    }

    @Operation(
            summary = "Получение списка рекомендаций по обустройству дома для животного с ограниченными возможностями (мобильность)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список рекомендаций по обустройству дома для животного с ограниченными возможностями (мобильность)",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список рекомендаций по обустройству дома для животного с ограниченными возможностями (мобильность) не найден",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Список рекомендаций по обустройству дома для животного с ограниченными возможностями (мобильность)"

    )
    @GetMapping("/reccomendation/home-adgustment-unhealthy-mobility")
    public String getInfoAboutAdgustmentUnhealthyMobilityProblems() {
        return adoptionService.getInfoAboutAdgustmentUnhealthyMobilityProblems();
    }

    @Operation(
            summary = "Получение списка причин, по которым могут отказать в усыновлении животного",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список причин, по которым могут отказать в усыновлении животного",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список причин, по которым могут отказать в усыновлении животного не найден",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Список причин, по которым могут отказать в усыновлении животного"

    )
    @GetMapping("/reasons-for-rejection")
    public String getInfoAboutReasonsForRejection() {
        return adoptionService.getInfoAboutReasonsForRejection();
    }

}
