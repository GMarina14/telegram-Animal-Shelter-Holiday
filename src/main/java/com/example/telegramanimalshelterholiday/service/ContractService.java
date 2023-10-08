package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.model.Contract;

import java.util.Collection;

public interface ContractService {

    void addContract(Contract contract);

    void removeContract (Long id);

    Collection<Contract> getAllContracts();
}
