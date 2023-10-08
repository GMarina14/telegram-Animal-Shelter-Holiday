package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.model.Shelter;
import com.example.telegramanimalshelterholiday.service.ShelterService;
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
@RequestMapping("/shelter")
public class ShelterController {
    private final ShelterService shelterService;

    @Operation(summary = "Поиск приюта по id ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найден приют с параметрами",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Приют не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Работа с приютом"
    )
    @GetMapping("{id}")
    public ResponseEntity<Shelter> searchShelterById(@PathVariable Long id) {
        Shelter byId = shelterService.getById(id);
        if (isNull(byId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId);
    }

    @Operation(
            summary = "Добавление приюта в базу данных ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Приют сохранен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Работа с приютом",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Параметры нового приюта",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelter.class)
                    )
            )
    )

    @PostMapping
    public ResponseEntity<Shelter> add(@RequestBody Shelter shelter) {
        return ResponseEntity.ok(shelterService.add(shelter));
    }

    @Operation(
            summary = "Изменения параметров приюта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Волонтер изменен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Работа с приютом",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Параметры нового приюта",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelter.class)
                    )
            )
    )

    @PutMapping
    public ResponseEntity<Shelter> update(@RequestBody Shelter shelter) {
        Shelter update = shelterService.update(shelter);
        if (isNull(update)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(shelter);
    }
    @Operation(summary = "Поиск всех животных находящихся в приюте ,по id приюта ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найдены питомцы в данном приюте",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Питомцы не найдены",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            },
            tags = "Работа с приютом"
    )
    @GetMapping("/{id}/animal")
    public ResponseEntity<List<Animal>>getAnimalByShelter(@PathVariable Long id) {
        List<Animal> allAnimal = shelterService.getAllAnimal(id);
        if (isNull(allAnimal)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allAnimal);
    }

}
