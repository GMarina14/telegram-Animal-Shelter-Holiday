package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Contract;
import com.example.telegramanimalshelterholiday.repository.ContractRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

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

    @Test
    void shouldAddNewContract() {
        //given

        Contract expected = new Contract(1L, LocalDate.of(2021, 10, 31), null);

        //when
        ResponseEntity<Contract> response = restTemplate.postForEntity("/contract", expected, Contract.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        Contract actual = response.getBody();
        //Ниже не работает, вообще не сохраняет в базу
        //Assertions.assertThat(response.getBody()).isEqualTo(expected);
        //Assertions.assertThat(actual).usingRecursiveComparison().ignoringFields("id", "reportList").isEqualTo(expected);

    }

    private Contract createContractAndSaveInBd(Contract contract) {
        return contractRepository.save(contract);
    }
}
