package com.example.telegramanimalshelterholiday.repository;

import com.example.telegramanimalshelterholiday.model.Client;
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
     * Получаем список всех клиентов по id
     */
    @Query(value = "SELECT * FROM client WHERE chat_id = :id", nativeQuery = true)
    Collection<Client> findByChatId(@Param("id") Long id);


}
