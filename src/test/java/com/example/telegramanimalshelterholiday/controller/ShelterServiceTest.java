package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.model.Shelter;
import com.example.telegramanimalshelterholiday.model.Volunteer;
import com.example.telegramanimalshelterholiday.repository.ShelterRepository;
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

import static com.example.telegramanimalshelterholiday.controller.PepareTestObject.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShelterServiceTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ShelterRepository shelterRepository;

    @AfterEach
    public void resetDB() {
        shelterRepository.deleteAll();
    }

    @Test
    void shouldGetShelterById() {
        Long shelterId = shelterSaveBd("ShelterCat", PetsSpecies.CAT, "Russia", "Moscow", "Lenina", "78994569098", "https://").getId();
        ResponseEntity<Shelter> response = testRestTemplate.getForEntity("/shelter/{id}", Shelter.class, shelterId);
        Shelter shelterResult = response.getBody();
        Assertions.assertThat(shelterResult).isNotNull();
        Assertions.assertThat(shelterResult.getId()).isEqualTo(shelterId);
    }

    @Test
    void shouldCreateShelter() {
        Shelter shelter = shelterObjectOne();
        ResponseEntity<Shelter> response = testRestTemplate.postForEntity("/shelter", shelter, Shelter.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isNotNull();
        Assertions.assertThat(response.getBody().getName()).isEqualTo(shelter.getName());
        Assertions.assertThat(response.getBody().getPetsSpecies()).isEqualTo(shelter.getPetsSpecies());
        Assertions.assertThat(response.getBody().getCountry()).isEqualTo(shelter.getCountry());
        Assertions.assertThat(response.getBody().getCity()).isEqualTo(shelter.getCity());
        Assertions.assertThat(response.getBody().getAddress()).isEqualTo(shelter.getAddress());
        Assertions.assertThat(response.getBody().getPhoneNumber()).isEqualTo(shelter.getPhoneNumber());
        Assertions.assertThat(response.getBody().getYandexMapsUrl()).isEqualTo(shelter.getYandexMapsUrl());
    }

    @Test
    void shouldUpdateShelter() {
        Shelter shelter = shelterObjectOne();
        shelterRepository.save(shelter);
        ResponseEntity<Shelter> response = testRestTemplate.exchange("/shelter", HttpMethod.PUT, new HttpEntity<>(shelter), Shelter.class);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(shelter.getId());
        Assertions.assertThat(response.getBody().getName()).isEqualTo(shelter.getName());
        Assertions.assertThat(response.getBody().getPetsSpecies()).isEqualTo(shelter.getPetsSpecies());
        Assertions.assertThat(response.getBody().getCountry()).isEqualTo(shelter.getCountry());
        Assertions.assertThat(response.getBody().getCity()).isEqualTo(shelter.getCity());
        Assertions.assertThat(response.getBody().getAddress()).isEqualTo(shelter.getAddress());
        Assertions.assertThat(response.getBody().getPhoneNumber()).isEqualTo(shelter.getPhoneNumber());
        Assertions.assertThat(response.getBody().getYandexMapsUrl()).isEqualTo(shelter.getYandexMapsUrl());
    }

    @Test
    void shouldGetListAnimalById() {
        Shelter shelter = shelterObjectOne();
        shelterRepository.save(shelter);
        List<Animal> animalList = animalList();
        ResponseEntity<Shelter> response = testRestTemplate.getForEntity("/shelter/1/animal", Shelter.class, animalList);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getAnimalList()).isEqualTo(shelter.getAnimalList());

    }

    @Test
    void shouldGetListVolunteerById() {
        Shelter shelter = shelterObjectOne();
        shelterRepository.save(shelter);
        List<Volunteer> volunteerList = volunteerList();
        ResponseEntity<Shelter> response = testRestTemplate.getForEntity("/shelter/1/volunteer", Shelter.class, volunteerList);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getAnimalList()).isEqualTo(shelter.getAnimalList());

    }

    @Test
    void shouldGetListClientById() {
        Shelter shelter = shelterObjectOne();
        shelterRepository.save(shelter);
        List<Client> clientList = clientList();
        ResponseEntity<Shelter> response = testRestTemplate.getForEntity("/shelter/1/client", Shelter.class, clientList);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getClientsList()).isEqualTo(shelter.getClientsList());

    }


    @NotNull
    private Shelter shelterSaveBd(String name, PetsSpecies petSpecies, String country, String city, String address,
                                  String phoneNumber, String url) {
        Shelter shelter = new Shelter();
        shelter.setName(name);
        shelter.setPetsSpecies(petSpecies);
        shelter.setCountry(country);
        shelter.setCity(city);
        shelter.setAddress(address);
        shelter.setPhoneNumber(phoneNumber);
        shelter.setYandexMapsUrl(url);
        return shelterRepository.save(shelter);
    }

}
