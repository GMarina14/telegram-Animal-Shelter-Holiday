package com.example.telegramanimalshelterholiday.model;

import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Shelter {

    /* модель умышленно лишена возможности изменения id, рода содержащихся животных и названия приюта после создания экземпляра
    id назначается автоматически при содании через скрипты таблицы liquibase/flyway  через код (shelter_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shelterId;

    private String name;
    private PetsSpecies petsSpecies;
    private String city;
    private String address;
    private String phoneNumber;

    // расписание приюта
    private String workHours;

    // ссылка на адрес на картах
    private byte[] yandexMapsUrl;

    // контактные данные охраны для оформления пропуска на машину
    private String carPassInfo;

    // общие рекомендации по технике безопасности на территории приюта
    private String securityMeasures;

    public Shelter() {

    }
    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Animal> animalList;

    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Volunteer>volunteerList;


    public Shelter(String name, PetsSpecies petsSpecies) {
        //this.id = id; // если назначение айди сделаем автоматическим
        this.name = name;
        this.petsSpecies = petsSpecies;
    }

    public Long getId() {
        return shelterId;
    }

    public String getName() {
        return name;
    }

    public PetsSpecies getPetsSpecies() {
        return petsSpecies;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public byte[] getYandexMapsUrl() {
        return yandexMapsUrl;
    }

    public void setYandexMapsUrl(byte[] yandexMapsUrl) {
        this.yandexMapsUrl = yandexMapsUrl;
    }

    public String getCarPassInfo() {
        return carPassInfo;
    }

    public void setCarPassInfo(String carPassInfo) {
        this.carPassInfo = carPassInfo;
    }

    public String getSecurityMeasures() {
        return securityMeasures;
    }

    public void setSecurityMeasures(String securityMeasures) {
        this.securityMeasures = securityMeasures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shelter shelter)) return false;
        return shelterId.equals(shelter.shelterId) && name.equals(shelter.name) && petsSpecies == shelter.petsSpecies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelterId, name, petsSpecies);
    }
}
