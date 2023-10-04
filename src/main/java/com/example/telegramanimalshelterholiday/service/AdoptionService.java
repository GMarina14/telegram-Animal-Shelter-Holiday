package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Animal;

import java.util.List;

public interface AdoptionService {

    ////////////For all///////////////
    String getInfoAboutAdoption();
    String getInfoAboutDocsForAdoption();
    String getInfoAboutAdgustmentUnhealthyWithSightProblems();
    String getInfoAboutAdgustmentUnhealthyMobilityProblems();
    String getInfoAboutReasonsForRejection();

    ////////////Especially for cats///////////////
    String getInfoAboutFirstMeetWithCat();
    List<Animal> getAllCats();
    String getInfoAboutCatTransportation();
    String getInfoAboutAdgustmentKitty();
    String getInfoAboutAdgustmentCat();

    ////////////Especially for dogs///////////////
    String getInfoAboutFirstMeetWithDog();
    List<Animal> getAllDogs();
    String getInfoAboutDogTransportation();
    String getInfoAboutAdgustmentPuppy();
    String getInfoAboutAdgustmentDog();
    String getAdviceFromDoghandler();
    String getGoodDoghandlers();
}
