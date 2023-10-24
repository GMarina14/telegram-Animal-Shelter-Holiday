package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.repository.ClientRepository;
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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClientRepository clientRepository;

    @AfterEach
    public void resetDb() {
        clientRepository.deleteAll();
    }

    @Test
    void shouldAddNewClient() {
        //given
        Client expected = new Client(33L, "Ivanov");

        //when
        ResponseEntity<Client> response = restTemplate.postForEntity("/client", expected, Client.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        Client actual = response.getBody();
        Assertions.assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);

    }

    @Test
    void shouldUpdateClient() {
        //given
        Client client = new Client(33L, "Ivanov");
        createClientAndSaveInBd(client);
        Client expected = new Client(33L, "Ivanov");

        //when
        ResponseEntity<Client> response = restTemplate.exchange("/client", HttpMethod.PUT, new HttpEntity<>(expected), Client.class);
        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        Client actual = response.getBody();
        Assertions.assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);

    }

    @Test
    void shouldGetAllClients() {
        //given
        Client newClient1 = new Client(33L, "Ivanov1");
        Client newClient2 = new Client(35L, "Ivanov2");
        Client newClient3 = new Client(36L, "Ivanov3");

        createClientAndSaveInBd(newClient1);
        createClientAndSaveInBd(newClient2);
        createClientAndSaveInBd(newClient3);

        List<Client> expected = new ArrayList<>(List.of(newClient1, newClient2, newClient3));

        //when
        ResponseEntity<List<Client>> response = restTemplate.exchange("/client/get-all-clients", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Client>>() {
                });

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Client> actual = response.getBody();
        Assertions.assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }

    @Test
    void shouldRemoveClient() {
        //given
        Client newClient1 = new Client(33L, "Ivanov1");
        Client newClient2 = new Client(35L, "Ivanov2");
        Client newClient3 = new Client(36L, "Ivanov3");

        createClientAndSaveInBd(newClient1);
        createClientAndSaveInBd(newClient2);
        createClientAndSaveInBd(newClient3);

        Long clientId = createClientAndSaveInBd(newClient1).getId();

        //when
        restTemplate.delete("/client/{id}", clientId);

        //then
        Assertions.assertThat(clientRepository.findById(clientId).isEmpty());

    }

    @Test
    void shouldGetClientById() {
        Client newClient1 = new Client(33L, "Ivanov1");
        Long id = createClientAndSaveInBd(newClient1).getId();
        ResponseEntity<Client> response = restTemplate.getForEntity("/client/get-by-id/{id}", Client.class, id);
        Client actual = response.getBody();
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getId()).isEqualTo(id);
    }

    @Test
    void shouldGetClientByChatId() {
        Client newClient1 = new Client(33L, "Ivanov1");
        createClientAndSaveInBd(newClient1).getChatId();
        Long chatId = 33L;
        ResponseEntity<Client> response = restTemplate.getForEntity("/client//get-by-chat-id/{chatId}", Client.class, chatId);
        Client actual = response.getBody();
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getChatId()).isEqualTo(chatId);
    }

    private Client createClientAndSaveInBd(Client client) {
        return clientRepository.save(client);
    }
}
