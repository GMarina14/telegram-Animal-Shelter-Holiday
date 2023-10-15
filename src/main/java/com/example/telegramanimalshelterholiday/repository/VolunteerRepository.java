package com.example.telegramanimalshelterholiday.repository;

import com.example.telegramanimalshelterholiday.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    /**
     * Запрос JPQL в базу данных,который ищет любого (рандомного) волонтера.
     */

    @Query(nativeQuery = true, value = "SELECT* FROM volunteer ORDER BY random() LIMIT 1")
    public Optional<Volunteer> getRandomVolunteer();
}

