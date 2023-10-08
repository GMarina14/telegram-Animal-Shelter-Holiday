package com.example.telegramanimalshelterholiday.repository;

import com.example.telegramanimalshelterholiday.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
/**
 * Запрос JPQL в базу данных,который ищет любого (рандомного) волонтера.
 */
}
