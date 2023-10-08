package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Operation(summary = "Поиск питомца по id ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найден питомец с параметрами",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Animal.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Питомец не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Animal.class)
                            )
                    )
            },
            tags = "Работа с питомцем"
    )
    @GetMapping("{id}")
    public ResponseEntity<Animal> searchAnimalById(@PathVariable Long id) {
        Animal byId = animalService.getById(id);
        if (isNull(byId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId);
    }

    @Operation(
            summary = "Добавления нового питомца в базу данных ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Животное сохранено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Animal.class)
                            )
                    )
            },
            tags = "Работа с питомцем",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Параметры нового питомца",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )
            )
    )
    @PostMapping
    public ResponseEntity<Animal> add(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.add(animal));
    }

    @Operation(
            summary = "Редактирование питомца",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Обновленный питомец с параметрами",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Animal.class)
                            )
                    )
            },
            tags = "Работа с питомцем",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новые параметры питомца",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )
            )
    )
    @PutMapping
    public ResponseEntity<Animal> update(@RequestBody Animal animal) {
        Animal update = animalService.update(animal);
        if (isNull(update)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(update);
    }

    @Operation(summary = "Удаление питомца по id ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Питомец удален из  базы",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Animal.class)
                            )
                    )
            },
            tags = "Работа с питомцем"
    )

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        animalService.remove(id);
        return ResponseEntity.ok().build();
    }


}
