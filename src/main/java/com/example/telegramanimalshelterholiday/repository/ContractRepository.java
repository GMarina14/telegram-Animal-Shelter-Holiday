package com.example.telegramanimalshelterholiday.repository;

import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ContractRepository extends JpaRepository <Contract, Long>{

    /**
     * Получаем список всех контрактов
     */
    @Query(value = "SELECT * FROM contract", nativeQuery = true)
    Collection<Contract> getAll();

}
