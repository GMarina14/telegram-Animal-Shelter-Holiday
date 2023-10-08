package com.example.telegramanimalshelterholiday.service.serviceImpl;

import com.example.telegramanimalshelterholiday.model.Contract;
import com.example.telegramanimalshelterholiday.repository.ContractRepository;
import com.example.telegramanimalshelterholiday.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public void addContract(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public void removeContract(Long id) {
        contractRepository.deleteById(id);
    }

    @Override
    public Collection<Contract> getAllContracts() {
        return contractRepository.getAll();
    }
}
