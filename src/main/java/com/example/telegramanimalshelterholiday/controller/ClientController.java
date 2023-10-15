package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @Operation(
            summary = "Добавление нового пользователя ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "пользователь успешно добавлен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            },
            tags = "Пользователь"
    )
    @PostMapping()
    public Client addNewClient(@Parameter(description = "объект клиента") @RequestBody Client client) {
        return clientService.addClient(client);

    }

    @Operation(
            summary = "Внесение изменений в пользователя ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "пользователь успешно изменен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            },
            tags = "Пользователь"
    )
    @PutMapping()
    public Client update(@Parameter(description = "обновленный клиент") @RequestBody Client client) {
        return clientService.update(client);

    }

    @Operation(
            summary = "Получение списка пользователей ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "получен список пользователей",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema (schema = @Schema(implementation = Client.class))
                            )
                    )
            },
            tags = "Пользователь"
    )
    @GetMapping("/get-all-clients")
    public Collection<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @Operation(
            summary = "Получение пользователя по chatId",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "пользователь найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema (schema = @Schema(implementation = Client.class))
                            )
                    )
            },
            tags = "Пользователь"
    )
    @GetMapping("/get-by-chat-id/{chatId}")
    public Client getClientsByChatId(@PathVariable Long chatId) {
        return clientService.getClientByChatId(chatId);
    }


    @Operation(
            summary = "Получение пользователя по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "пользователь найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema (schema = @Schema(implementation = Client.class))
                            )
                    )
            },
            tags = "Пользователь"
    )
    @GetMapping("/get-by-id/{id}")
    public Optional<Client> getClientsById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @Operation(
            summary = "Удаление пользователя ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "пользователь успешно удален",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            },
            tags = "Пользователь"
    )
    @DeleteMapping("/remove/{id}")
    public void removeClientById(@Parameter(description = "id клиента") @PathVariable Long id) {
        clientService.removeClient(id);
    }
}
