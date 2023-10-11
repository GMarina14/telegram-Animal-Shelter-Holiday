package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Client;

import java.util.Collection;
import java.util.Optional;

public interface ClientService {

    void addClient (Client client);

    void removeClient (Long id);

    Collection<Client> getAllClients();

    Collection<Client> getClientByChatID(Long chatId);

}
