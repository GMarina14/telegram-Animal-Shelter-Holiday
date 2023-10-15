package com.example.telegramanimalshelterholiday.repository;

import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query(value = "SELECT a FROM Animal a WHERE a.petsSpecies = :petsSpecies")
    List<Animal> findAllAnimalPetsSpeciesFilter(PetsSpecies petsSpecies);
}
