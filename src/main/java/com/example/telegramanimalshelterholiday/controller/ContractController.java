package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Contract;
import com.example.telegramanimalshelterholiday.service.ContractService;
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
@RequestMapping("/contract")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @Operation(
            summary = "Добавление нового контракта ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "контракт добавлен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Contract.class)
                            )
                    )
            },
            tags = "Контракт"
    )
    @PostMapping()
    public Contract addNewContract(@Parameter(description = "Объект контракта") @RequestBody Contract contract) {
        return contractService.addContract(contract);

    }

    @Operation(
            summary = "Изменение контракта ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "контракт изменен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Contract.class)
                            )
                    )
            },
            tags = "Контракт"
    )
    @PutMapping()
    public Contract updateContract(@Parameter(description = "Объект контракта") @RequestBody Contract contract) {
        return contractService.update(contract);

    }


    @Operation(
            summary = "Получение списка контрактов ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "получен список контрактов",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Contract.class))
                            )
                    )
            },
            tags = "Контракт"
    )
    @GetMapping("/get-all-contracts")
    public Collection<Contract> getAllContracts() {
        return contractService.getAllContracts();
    }


    @Operation(
            summary = "Контракт найден ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "получен контракт",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Contract.class))
                            )
                    )
            },
            tags = "Контракт"
    )
    @GetMapping("/get-contract-by-id/{id}")
    public Contract getContractById(@PathVariable Long id) {
        return contractService.getById(id);
    }

    @Operation(
            summary = "Удаление контракта по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "контракт удален",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Contract.class)
                            )
                    )
            },
            tags = "Контракт"
    )
    @DeleteMapping("/delete-contract/{id}")
    public void removeContractById(@Parameter(description = "id контракта") @PathVariable Long id) {
        contractService.removeContract(id);
    }

}
