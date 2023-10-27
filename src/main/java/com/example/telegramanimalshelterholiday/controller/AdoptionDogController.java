package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Report;
import com.example.telegramanimalshelterholiday.model.Shelter;
import com.example.telegramanimalshelterholiday.service.AdoptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dog/shelter/adoption")
public class AdoptionDogController {

    private final AdoptionService adoptionService;

    public AdoptionDogController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }
    @Operation(
            summary = "Получение информации о том, как усыновить животное",
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
                            description = "Информация об усыновлении",
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
        return adoptionService.getInfoAboutFirstMeetWithDog();
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
            summary = "Получение списка рекомендаций по транспортировке собаки",
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
        return adoptionService.getInfoAboutDogTransportation();
    }

    @Operation(
            summary = "Получение списка рекомендаций по обустройству дома для щенка",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список рекомендаций по обустройству дома для щенка",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список рекомендаций по обустройству дома для щенка не найден",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Список рекомендаций по обустройству дома для щенка"

    )
    @GetMapping("/recommendation/home-adgustment-puppy")
    public String getInfoAboutAdgustmentPuppy() {
        return adoptionService.getInfoAboutAdgustmentPuppy();
    }

    @Operation(
            summary = "Получение списка рекомендаций по обустройству дома для взрослой собаки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список рекомендаций по обустройству дома для взрослой собаки",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список рекомендаций по обустройству дома для взрослой собаки не найден",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Список рекомендаций по обустройству дома для взрослой собаки"

    )
    @GetMapping("/recommendation/home-adgustment-dog")
    public String getInfoAboutAdgustmentDog() {
        return adoptionService.getInfoAboutAdgustmentDog();
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
    @GetMapping("/recommendation/home-adgustment-unhealthy-sight")
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
    @GetMapping("/recommendation/home-adgustment-unhealthy-mobility")
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

   @Operation(
           summary = "Выдача рекомендаций кинолога по обращению с собакой",
           responses = {
                   @ApiResponse(
                           responseCode = "200",
                           description = "Выдача рекомендаций кинолога по обращению с собакой",
                           content = @Content(
                                   schema = @Schema(implementation = Shelter.class)
                           )
                   ),
                   @ApiResponse(
                           responseCode = "404",
                           description = "Выдача рекомендаций кинолога по обращению с собакой не произведена",
                           content = @Content(
                                   schema = @Schema(implementation = Shelter.class)
                           )
                   )
           },
           tags = "Рекомендации кинолога по обращению с собакой"
   )
    @GetMapping("/recommendation/first-meeting-doghandler")
    public String getAdviceFromDoghandler() {
        return adoptionService.getAdviceFromDoghandler();
    }

    @Operation(
            summary = "Выдача списка проверенных кинологов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Выдача списка проверенных кинологов",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Выдача списка проверенных кинологов не произведена",
                            content = @Content(
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Выдача списка проверенных кинологов"
    )
    @GetMapping("/recommendation/doghandlers")
    public String getGoodDoghandlers() {
        return adoptionService.getGoodDoghandlers();
    }
}
