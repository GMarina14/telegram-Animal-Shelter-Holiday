package com.example.telegramanimalshelterholiday.repository;

import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Получаем список всех клиентов
     */
    @Query(value = "SELECT * FROM client", nativeQuery = true)
    Collection<Client> getAll();

    /**
     * Получаем клиента по chatId
     */

//    @Query(value = "SELECT * FROM client WHERE chat_id = :chatId", nativeQuery = true)
//    Client findByChatId(@Param("chatId") Long chatId);
    Client findByChatId(long chatId);


}
