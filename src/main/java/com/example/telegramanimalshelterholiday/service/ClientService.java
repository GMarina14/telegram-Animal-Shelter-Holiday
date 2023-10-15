package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Client;

import java.util.Collection;
import java.util.Optional;

public interface ClientService {

    Client addClient (Client client);

    void removeClient (Long id);

    Collection<Client> getAllClients();

    Client getClientByChatId(Long chatId);

    Optional<Client> getClientById(Long id);

    Client update(Client client);

}
