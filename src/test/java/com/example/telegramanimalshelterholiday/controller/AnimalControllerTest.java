package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.constants.enums.Health;
import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.constants.enums.Sex;
import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.repository.AnimalRepository;
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

import static com.example.telegramanimalshelterholiday.controller.PepareTestObject.animalObjectOne;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimalControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AnimalRepository animalRepository;

    @AfterEach
    public void resetDB() {
        animalRepository.deleteAll();
    }

    @Test
    void shouldGetVolunteerById() {
        Long animalId = animalSaveBd("Musiy", 3, PetsSpecies.CAT, Sex.FEMALE, Health.HEALTHY).getId();
        ResponseEntity<Animal> response = testRestTemplate.getForEntity("/animal/{id}", Animal.class, animalId);
        Animal animalResult = response.getBody();
        Assertions.assertThat(animalResult).isNotNull();
        Assertions.assertThat(animalResult.getId()).isEqualTo(animalId);
    }

    @Test
    void shouldCreateAnimal() {
        Animal animal = animalObjectOne();
        ResponseEntity<Animal> response = testRestTemplate.postForEntity("/animal", animal, Animal.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isNotNull();
        Assertions.assertThat(response.getBody().getName()).isEqualTo(animal.getName());
        Assertions.assertThat(response.getBody().getAge()).isEqualTo(animal.getAge());
        Assertions.assertThat(response.getBody().getPetsSpecies()).isEqualTo(animal.getPetsSpecies());
        Assertions.assertThat(response.getBody().getSex()).isEqualTo(animal.getSex());
        Assertions.assertThat(response.getBody().getHealth()).isEqualTo(animal.getHealth());
    }

    @Test
    void shouldUpdateVolunteer() {
        Animal animal = animalObjectOne();
        animalRepository.save(animal);
        ResponseEntity<Animal> response = testRestTemplate.exchange("/animal", HttpMethod.PUT, new HttpEntity<>(animal), Animal.class);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(animal.getId());
        Assertions.assertThat(response.getBody().getName()).isEqualTo(animal.getName());
        Assertions.assertThat(response.getBody().getAge()).isEqualTo(animal.getAge());
        Assertions.assertThat(response.getBody().getPetsSpecies()).isEqualTo(animal.getPetsSpecies());
        Assertions.assertThat(response.getBody().getSex()).isEqualTo(animal.getSex());
        Assertions.assertThat(response.getBody().getHealth()).isEqualTo(animal.getHealth());
    }
    @Test
    void shouldDeleteAnimalById() {
        Animal animal = animalObjectOne();
        animalRepository.save(animal);
        testRestTemplate.delete("/animal/1/delete");
        Assertions.assertThat(animalRepository.findById(1L)).isEmpty();
    }


    @NotNull
    private Animal animalSaveBd(String name, int age, PetsSpecies petsSpecies, Sex sex, Health health) {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setAge(age);
        animal.setPetsSpecies(petsSpecies);
        animal.setSex(sex);
        animal.setHealth(health);
        return animalRepository.save(animal);
    }

}
