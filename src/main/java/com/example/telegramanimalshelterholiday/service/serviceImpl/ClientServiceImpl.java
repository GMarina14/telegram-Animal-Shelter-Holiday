package com.example.telegramanimalshelterholiday.service.serviceImpl;

import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.repository.ClientRepository;
import com.example.telegramanimalshelterholiday.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void addClient(Client client) {
        clientRepository.save(client);
    }


    @Override
    public void removeClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Collection<Client> getAllClients() {
        return clientRepository.getAll();
    }

    @Override
    public Collection<Client> getClientByChatID(Long chatId) {
        return clientRepository.findByChatId(chatId);
    }

    @Override
    public Collection<Client> getClientsWithPhoneNuber() {
        return clientRepository.getClientsWithPhoneNumber();
    }
}
