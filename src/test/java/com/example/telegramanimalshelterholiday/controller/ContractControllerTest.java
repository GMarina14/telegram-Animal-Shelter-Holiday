package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.model.Contract;
import com.example.telegramanimalshelterholiday.repository.ContractRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContractRepository contractRepository;

    @AfterEach
    public void resetDb() {
        contractRepository.deleteAll();
    }


    private Contract createContractAndSaveInBd(Contract contract) {
        return contractRepository.save(contract);
    }

    @Test
    void shouldAddNewContract() {
        //given

        Contract expected = new Contract(1L, LocalDate.of(2021, 10, 31),678L,5,null);

        //when
        ResponseEntity<Contract> response = restTemplate.postForEntity("/contract", expected, Contract.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        Contract actual = response.getBody();
        Assertions.assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);



    }


    @Test
    void shouldUpdateContract() {
        //given

        Contract test = new Contract(1L, LocalDate.of(2021, 10, 31),4567L,15, null);
        createContractAndSaveInBd(test);
        Contract expected = new Contract(1L, LocalDate.of(2022, 11, 25),6789L,40, null);

        //when
        ResponseEntity<Contract> response = restTemplate.exchange("/contract", HttpMethod.PUT, new HttpEntity<>(expected), Contract.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        Contract actual = response.getBody();
        Assertions.assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);



    }

    @Test
    void shouldGetAllContracts() {
        //given
        Contract newContract1 = new Contract(1L, LocalDate.of(2021, 10, 31),1234L,15, null);
        Contract newContract2 = new Contract(1L, LocalDate.of(2022, 1, 20),345L,30, null);
        Contract newContract3 = new Contract(1L, LocalDate.of(2023, 2, 12),678L,23, null);

        createContractAndSaveInBd(newContract1);
        createContractAndSaveInBd(newContract2);
        createContractAndSaveInBd(newContract3);

        List<Contract> expected = new ArrayList<>(List.of(newContract1, newContract2, newContract3));

        //when
        ResponseEntity<List<Contract>> response = restTemplate.exchange("/contract/get-all-contracts", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Contract>>() {
                });

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Contract> actual = response.getBody();
        Assertions.assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }

    @Test
    void shouldGetContractById() {
       // given
        Contract newContract1 = new Contract(1L, LocalDate.of(2021, 10, 31),678L,78, null);
        Long id = createContractAndSaveInBd(newContract1).getId();

        //when
        ResponseEntity<Contract> response = restTemplate.getForEntity("/contract/get-contract-by-id/{id}", Contract.class, id);

        //then
        Contract actual = response.getBody();
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getId()).isEqualTo(id);
    }


    @Test
    void shouldRemoveClient() {
       // given
        Contract newContract1 = new Contract(1L, LocalDate.of(2021, 10, 31),789L,8, null);
        Contract newContract2 = new Contract(1L, LocalDate.of(2022, 1, 20),678L,8, null);
        Contract newContract3 = new Contract(1L, LocalDate.of(2023, 2, 12),908L,25, null);

        createContractAndSaveInBd(newContract1);
        createContractAndSaveInBd(newContract2);
        createContractAndSaveInBd(newContract3);

        Long contractId = createContractAndSaveInBd(newContract2).getId();

        //when
        restTemplate.delete("/contract/delete-contract/{id}", contractId);

        //then
        Assertions.assertThat(contractRepository.findById(contractId).isEmpty());
    }

}
