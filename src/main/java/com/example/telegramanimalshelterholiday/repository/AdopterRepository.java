package com.example.telegramanimalshelterholiday.repository;

import com.example.telegramanimalshelterholiday.model.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, Long> {

    /**
     * Получаем список всех усыновителей
     */
    @Query(value = "SELECT * FROM adopter", nativeQuery = true)
    Collection<Adopter> getAll();

    /**
     * Получения Адоптера по chatId
     */
    Adopter findByChatId(long chatId);


    @Query(value = "SELECT * FROM adopter WHERE phone_number = :phoneNumber", nativeQuery = true)
    Adopter getAdopterByPhoneNumber(@Param("phoneNumber") String phoneNumber);









}
