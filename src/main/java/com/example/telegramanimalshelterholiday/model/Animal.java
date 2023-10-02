package com.example.telegramanimalshelterholiday.model;

import com.example.telegramanimalshelterholiday.constants.enums.Health;
import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.constants.enums.Sex;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

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

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    private List<Report> reportList;

    @ManyToOne
    @JoinColumn(name = "adopter_id")
    private Adopter adopter;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;


}
