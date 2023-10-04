package com.example.telegramanimalshelterholiday.model;

import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Shelter {

    @Id
    @SequenceGenerator(name="shelterSequence",sequenceName = "shelter_sequence",allocationSize = 1,initialValue =1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "shelterSequence")
    private Long shelterId;

    @Column(name="shelter_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private PetsSpecies petsSpecies;
    private String city;
    private String address;
    private String phoneNumber;
    @Column(name="path_description")
    private String yandexMapsUrl;

    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Animal> animalList;

    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Volunteer> volunteerList;

    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Client> clientsList;

    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Adopter> adopterList;
}
