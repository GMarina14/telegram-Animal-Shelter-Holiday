package com.example.telegramanimalshelterholiday.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public class Report {

    private Long id;
    private LocalDate reportDate;
    private byte[] photo;
    private String diet;
    private String stateOfHealth;
    private String behavior;

    @ManyToOne
    @JoinColumn(name = "adopter_id")
    private Adopter adopter;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    /*на стороне Animal
    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private List<Report> reports;*/


}
