package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Adopter;
import com.example.telegramanimalshelterholiday.repository.AdopterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdopterControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AdopterRepository adopterRepository;

    @AfterEach
    public void resetDb() {
        adopterRepository.deleteAll();
    }

    @Test
    void shouldAddNewAdopter() {
        //given
        Adopter adopter = new Adopter("Ivan", "Ivanov", 10, 45L, "007");

        //when
        ResponseEntity<Adopter> response = restTemplate.postForEntity("/adopter", adopter, Adopter.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull(); //- фейлится видимо потому, что у нас метод добавления void, ничего не возвращает переделал заработало
        Assertions.assertThat(response.getBody().getId()).isNotNull();
        Assertions.assertThat(response.getBody().getFirstName()).isEqualTo(adopter.getFirstName());
        Assertions.assertThat(response.getBody().getLastName()).isEqualTo(adopter.getLastName());
        Assertions.assertThat(response.getBody().getProbExtend()).isEqualTo(adopter.getProbExtend());
        Assertions.assertThat(response.getBody().getChatId()).isEqualTo(adopter.getChatId());
        Assertions.assertThat(response.getBody().getPhoneNumber()).isEqualTo(adopter.getPhoneNumber());

    }

    @Test
    void shouldGetAllAdopters() {
        //given
        createAdopterAndSaveInBd("Ivan", "Ivanov", 10, 45L, "007");
        createAdopterAndSaveInBd("Ivan1", "Ivanov1", 100, 450L, "008");
        createAdopterAndSaveInBd("Ivan2", "Ivanov2", 200, 250L, "2008");

        List<Adopter> expected = new ArrayList<>(List.of(new Adopter("Ivan", "Ivanov", 10, 45L, "007"),
                new Adopter("Ivan1", "Ivanov1", 100, 450L, "008"),
                new Adopter("Ivan2", "Ivanov2", 200, 250L, "2008")));

        //when
        ResponseEntity<List<Adopter>> response = restTemplate.exchange("/adopter/get-all-adopters", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Adopter>>() {
        });

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Adopter> actual = response.getBody();
        Assertions.assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }

    @Test
    void shouldRemoveAdopter() {
        //given
        Long adopterId = createAdopterAndSaveInBd("Ivan", "Ivanov", 10, 45L, "007").getId();

        //when
        restTemplate.delete("/adopter/{id}", adopterId);

        //then
        Assertions.assertThat(adopterRepository.findById(adopterId).isEmpty());

    }

    private Adopter createAdopterAndSaveInBd(String firstName, String lastName, Integer probExtend, Long chatId, String phoneNumber) {

        Adopter adopter = new Adopter(firstName, lastName, probExtend, chatId, phoneNumber);
        return adopterRepository.save(adopter);
    }

}
