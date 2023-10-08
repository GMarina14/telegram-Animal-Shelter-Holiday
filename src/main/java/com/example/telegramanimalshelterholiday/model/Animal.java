package com.example.telegramanimalshelterholiday.model;

import com.example.telegramanimalshelterholiday.constants.enums.Health;
import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.constants.enums.Sex;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Animal {
    /**
     * Модель создания животного.
     * Связь с приютом и связь через контракт с усыновителем.
     */
    @Id
    @SequenceGenerator(name="animalSequence",sequenceName = "animal_sequence",allocationSize = 1,initialValue =1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "animalSequence")
    @Column(name="id")
    private Long id;

    @Column(name = "nick_name")
    private String name;
    @Column(name="age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name="pets_species")
    private PetsSpecies petsSpecies;

    @Enumerated(EnumType.STRING)
    @Column(name="sex")
    private Sex sex;

    @Enumerated(EnumType.STRING)
    @Column(name="health")
    private Health health;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contract",
            joinColumns = @JoinColumn(name = "animal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = " adopter_id", referencedColumnName = "id"))
    private List<Adopter> adopterList;


    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;


}
