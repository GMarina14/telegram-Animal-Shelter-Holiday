package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Adopter;
import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.model.Shelter;
import com.example.telegramanimalshelterholiday.model.Volunteer;
import com.example.telegramanimalshelterholiday.service.VolunteerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;

    @Operation(summary = "Поиск волонтера по id ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найден волонтер с параметрами",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Волонтер не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )
                    )
            },
            tags = "Работа с волонтером"
    )

    @GetMapping("{id}")
    public ResponseEntity<Volunteer> searchVolunteerById(@PathVariable Long id) {
        Volunteer byId = volunteerService.getById(id);
        if (isNull(byId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId);
    }

    @Operation(
            summary = "Добавление волонтера в базу данных ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Волонтер сохранен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )
                    )
            },
            tags = "Работа с волонтером",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Параметры нового волонтера",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            )
    )

    @PostMapping
    public ResponseEntity<Volunteer> add(@RequestBody Volunteer volunteer) {
        return ResponseEntity.ok(volunteerService.add(volunteer));
    }

    @Operation(
            summary = "Изменения параметров волонтера",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Волонтер изменен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )
                    )
            },
            tags = "Работа с волонтером",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Параметры нового волонтера",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            )
    )


    @PutMapping
    public ResponseEntity<Volunteer> update(@RequestBody Volunteer volunteer) {
        Volunteer updated = volunteerService.update(volunteer);
        if (isNull(updated)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(volunteer);
    }

    @Operation(summary = "Удаление волонтера по id ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Волонтер удален из  базы",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )
                    )
            },
            tags = "Работа с волонтером"
    )
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        volunteerService.remove(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Поиск всех усыновителей обратившихся к волонтеру ,поиск по id волонтера ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найдены усыновители данного волонтера",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Усыновители не найдены",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Работа с волонтером"
    )
    @GetMapping("/{id}/adopter")
    public ResponseEntity<List<Adopter>> getClientByShelter(@PathVariable Long id) {
        List<Adopter> allAdopter = volunteerService.getAllAdopter(id);
        if (isNull(allAdopter)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allAdopter);
    }


}

