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
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);

    }

    @Override
    public void removeContract(Long id) {
        contractRepository.deleteById(id);
    }

    @Override
    public Collection<Contract> getAllContracts() {
        return contractRepository.getAll();
    }

    @Override
    public Contract getById(Long id) {
        return contractRepository.findByid(id);
    }

    @Override
    public Contract update(Contract contract) {
        return contractRepository.save(contract);
    }
}
