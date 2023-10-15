package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Adopter;
import com.example.telegramanimalshelterholiday.model.Volunteer;
import com.example.telegramanimalshelterholiday.repository.VolunteerRepository;
import jakarta.validation.constraints.NotNull;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.example.telegramanimalshelterholiday.controller.PepareTestObject.adopterList;
import static com.example.telegramanimalshelterholiday.controller.PepareTestObject.volunteerObjectOne;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VolunteerControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @AfterEach
    public void resetDB() {
        volunteerRepository.deleteAll();
    }

    @Test
    void shouldGetVolunteerById() {
        Long volunteerId = volunteerSaveBd("Sergei", 2L).getId();
        ResponseEntity<Volunteer> response = testRestTemplate.getForEntity("/volunteer/{id}", Volunteer.class, volunteerId);
        Volunteer volunteerResult = response.getBody();
        Assertions.assertThat(volunteerResult).isNotNull();
        Assertions.assertThat(volunteerResult.getId()).isEqualTo(volunteerId);
    }

    @Test
    void shouldCreateVolunteer() {
        Volunteer volunteer = volunteerObjectOne();
        ResponseEntity<Volunteer> response = testRestTemplate.postForEntity("/volunteer", volunteer, Volunteer.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isNotNull();
        Assertions.assertThat(response.getBody().getName()).isEqualTo(volunteer.getName());
        Assertions.assertThat(response.getBody().getChatId()).isEqualTo(volunteer.getChatId());
    }

    @Test
    void shouldUpdateVolunteer() {
        Volunteer volunteer = volunteerObjectOne();
        volunteerRepository.save(volunteer);
        ResponseEntity<Volunteer> response = testRestTemplate.exchange("/volunteer", HttpMethod.PUT, new HttpEntity<>(volunteer), Volunteer.class);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(volunteer.getId());
        Assertions.assertThat(response.getBody().getName()).isEqualTo(volunteer.getName());
        Assertions.assertThat(response.getBody().getChatId()).isEqualTo(volunteer.getChatId());
    }

    @Test
    void shouldDeleteVolunteerById() {
        Volunteer volunteer = volunteerObjectOne();
        volunteerRepository.save(volunteer);
        testRestTemplate.delete("/volunteer/1/delete");
        Assertions.assertThat(volunteerRepository.findById(1L)).isEmpty();
    }

    @Test
    void shouldGetListAdopterById() {
        Volunteer volunteer = volunteerObjectOne();
        volunteerRepository.save(volunteer);
        List<Adopter> adopterList = adopterList();
        ResponseEntity<Volunteer> response = testRestTemplate.getForEntity("/volunteer/1/adopter", Volunteer.class, adopterList);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getAdopterList()).isEqualTo(volunteer.getAdopterList());

    }


    @NotNull
    private Volunteer volunteerSaveBd(String name, long chatId) {
        Volunteer volunteer = new Volunteer();
        volunteer.setName(name);
        volunteer.setChatId(chatId);
        return volunteerRepository.save(volunteer);
    }


}
