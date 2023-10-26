package com.example.telegramanimalshelterholiday.repository;

import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {


    @Query(value = "SELECT s FROM Shelter s WHERE s.petsSpecies = :petsSpecies")
    Shelter findByPetsSpecies(PetsSpecies petsSpecies);

}
