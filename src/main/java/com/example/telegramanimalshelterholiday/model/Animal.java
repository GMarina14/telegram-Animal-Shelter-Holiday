package com.example.telegramanimalshelterholiday.model;

import com.example.telegramanimalshelterholiday.constants.enums.Health;
import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.constants.enums.Sex;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private PetsSpecies petsSpecies;
    private Sex sex;
    private Health health;

}
