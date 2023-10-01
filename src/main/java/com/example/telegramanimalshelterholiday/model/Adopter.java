package com.example.telegramanimalshelterholiday.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Adopter extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Contract contract;
    private Integer probExtend;



    @JsonIgnore
    @OneToMany(mappedBy = "adopter")
    private List<Animal> animals;

    /*на строне Animal нам нужно добавить следующее
    @ManyToOne
    @JoinColumn(name = "adopter_id")
    private Adopter adopter;*/


    @JsonIgnore
    @OneToMany(mappedBy = "adopter")
    private List<Report> reports;

}
