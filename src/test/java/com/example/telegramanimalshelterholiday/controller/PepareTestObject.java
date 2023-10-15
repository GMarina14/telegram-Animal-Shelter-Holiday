package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.constants.enums.Health;
import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.constants.enums.Sex;
import com.example.telegramanimalshelterholiday.model.*;

import java.util.ArrayList;
import java.util.List;

public class PepareTestObject {

    public static Shelter shelterObjectOne() {
        Shelter shelter = new Shelter();
        shelter.setId(1L);
        shelter.setName("catShelter");
        shelter.setPetsSpecies(PetsSpecies.CAT);
        shelter.setCountry("Russia");
        shelter.setCity("Moscow");
        shelter.setAddress("Lenina 23");
        shelter.setPhoneNumber("567893900-0");
        shelter.setYandexMapsUrl("https://");
        return shelter;
    }

    public static Volunteer volunteerObjectOne() {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(1L);
        volunteer.setName("Sergei");
        volunteer.setChatId(2344L);
        return volunteer;
    }

    public static Volunteer volunteerObjectTwo() {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(2L);
        volunteer.setName("Ivan");
        volunteer.setChatId(289L);
        return volunteer;
    }

    public static Animal animalObjectOne() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setName("Musya");
        animal.setAge(7);
        animal.setSex(Sex.FEMALE);
        animal.setHealth(Health.HEALTHY);
        animal.setPetsSpecies(PetsSpecies.CAT);
        return animal;
    }

    public static Animal animalObjectTwo() {
        Animal animal = new Animal();
        animal.setId(2L);
        animal.setName("Dusya");
        animal.setAge(3);
        animal.setSex(Sex.FEMALE);
        animal.setHealth(Health.HEALTHY);
        animal.setPetsSpecies(PetsSpecies.CAT);
        return animal;
    }

    public static Adopter adopterObjectOne() {
        Adopter adopter = new Adopter();
        adopter.setFirstName("Ivan");
        adopter.setLastName("Ivan");
        adopter.setChatId(37L);
        adopter.setProbExtend(30);
        adopter.setPhoneNumber("7896543698");
        return adopter;
    }

    public static Adopter adopterObjectTwo() {
        Adopter adopter = new Adopter();
        adopter.getId();
        adopter.setFirstName("Ivan");
        adopter.setLastName("Ivan");
        adopter.setChatId(37L);
        adopter.setProbExtend(30);
        adopter.setPhoneNumber("7896543698");
        return adopter;
    }

    public static Client clientObjectOne() {
        Client client = new Client();
        client.setChatId(23L);
        client.setUserName("Ivan");
        return client;
    }

    public static Client clientObjectTwo() {
        Client client = new Client();
        client.setChatId(56L);
        client.setUserName("Sergei");
        return client;
    }

    public static List<Adopter> adopterList() {
        List<Adopter> adopterList = new ArrayList<>();
        adopterList.add(adopterObjectOne());
        adopterList.add(adopterObjectTwo());
        return adopterList;
    }

    public static List<Volunteer> volunteerList() {
        List<Volunteer> volunteerArrayList = new ArrayList<>();
        volunteerArrayList.add(volunteerObjectOne());
        volunteerArrayList.add(volunteerObjectTwo());
        return volunteerArrayList;
    }

    public static List<Animal> animalList() {
        ArrayList<Animal> animalArrayList = new ArrayList<>();
        animalArrayList.add(animalObjectOne());
        animalArrayList.add(animalObjectTwo());
        return animalArrayList;
    }

    public static List<Client> clientList() {
        ArrayList<Client> clientArrayList = new ArrayList<>();
        clientArrayList.add(clientObjectOne());
        clientArrayList.add(clientObjectTwo());
        return clientArrayList;
    }
}
