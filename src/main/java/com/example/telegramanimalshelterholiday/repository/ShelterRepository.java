package com.example.telegramanimalshelterholiday.repository;

import com.example.telegramanimalshelterholiday.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

}
