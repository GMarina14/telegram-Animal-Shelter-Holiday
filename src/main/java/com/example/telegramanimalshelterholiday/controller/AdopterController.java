package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Adopter;
import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.service.AdopterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/adopter")
public class AdopterController {
    private final AdopterService adopterService;

    public AdopterController(AdopterService adopterService) {
        this.adopterService = adopterService;
    }

    @Operation(
            summary = "Добавление нового усыновителя ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Усыновитель успешно добавлен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Adopter.class)
                            )
                    )
            },
            tags = "Усыновители"
    )
    @PostMapping()
    public Adopter addNewAdopter(@Parameter(description = "Объект усыновителя") @RequestBody Adopter adopter) {
        return adopterService.addNewAdopter(adopter);

    }

    @Operation(
            summary = "Получаем список всех усыновителей ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "получен список всех усыновителей",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Adopter.class))
                            )
                    )
            },
            tags = "Усыновители"
    )
    @GetMapping("/get-all-adopters")
    public Collection<Adopter> getAllAdopters() {
        return adopterService.getAllAdopters();
    }

   /* @GetMapping("/get-adopter-by-phone")
    public Adopter getAdopterByPhoneNumber(@RequestParam String phoneNumber) {
        return adopterService.getAdopterByPhoneNumber(phoneNumber);
    }*/

    @Operation(
            summary = "Удаление усыновителя по id ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Усыновитель успешно удален",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Adopter.class)
                            )
                    )
            },
            tags = "Усыновители"
    )
    @DeleteMapping("/{id}")
    public void removeAdopterById(@Parameter(description = "id усыновителя") @PathVariable Long id) {
        adopterService.removeAdopterById(id);
    }
}
