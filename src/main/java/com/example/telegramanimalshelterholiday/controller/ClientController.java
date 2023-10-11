package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.model.Report;
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
    @PostMapping("/add")
    public void addNewClient(@Parameter(description = "объект клиента") @RequestBody Client client) {
        clientService.addClient(client);
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


    /*@GetMapping("/get-client-by-chat-id")
    public Collection<Client> getClientsByChatID(Long chatId) {
        return clientService.getClientByChatID(chatId);
    }
*/
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
    @DeleteMapping("/remove-client-by-id")
    public void removeClientById(@Parameter(description = "id клиента") Long id) {
        clientService.removeClient(id);
    }
}
