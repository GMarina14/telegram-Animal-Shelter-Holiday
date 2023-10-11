package com.example.telegramanimalshelterholiday.model;

import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Shelter {
    /**
     * Модель приютю
     * Имеет связи с моделями: животные, волонтер, клиент и усыновитель.
     */

    @Id
    @SequenceGenerator(name = "shelterSequence", sequenceName = "shelter_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shelterSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "shelter_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "pets_species")
    private PetsSpecies petsSpecies;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;


    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "path_description")
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

   /* @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Adopter> adopterList;*/
}
