package com.example.telegramanimalshelterholiday.service.serviceImpl;

import com.example.telegramanimalshelterholiday.constants.Recommendation;
import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.service.AdoptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionServiceImpl implements AdoptionService {
    Recommendation recommendation = new Recommendation();


    public String getInfoAboutAdoption() {
        return recommendation.INFO_ABOUT_ADOPTION;
    }

    @Override
    public String getInfoAboutDocsForAdoption() {
        return recommendation.DOCUMENTS_NEEDED_TO_ADOPT_ANIMAL;
    }

    @Override
    public String getInfoAboutAdgustmentUnhealthyWithSightProblems() {
        return recommendation.RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_ANIMAL_WITH_SIGHTPROBLEMS;
    }

    @Override
    public String getInfoAboutAdgustmentUnhealthyMobilityProblems() {
        return recommendation.RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_ANIMAL_WITH_MOBILITYPROBLEMS;
    }

    @Override
    public String getInfoAboutReasonsForRejection() {
        return recommendation.REASONS_OF_REJECTION_TO_ADOPT;
    }


    @Override
    public String getInfoAboutFirstMeetWithCat() {
        return recommendation.RULES_HOW_TO_MEET_CAT;
    }

    @Override
    public List<Animal> getAllCats() {
        return null;
    }

    @Override
    public String getInfoAboutCatTransportation() {
        return recommendation.RECOMMENDATIONS_FOR_CAT_TRANSPORTATION;
    }

    @Override
    public String getInfoAboutAdgustmentKitty() {
        return recommendation.RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_KITTY;
    }

    @Override
    public String getInfoAboutAdgustmentCat() {
        return recommendation.RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_CAT;
    }


    @Override
    public String getInfoAboutFirstMeetWithDog() {
        return recommendation.RULES_HOW_TO_MEET_DOG;
    }

    @Override
    public List<Animal> getAllDogs() {
        return null;
    }

    @Override
    public String getInfoAboutDogTransportation() {
        return recommendation.RECOMMENDATIONS_FOR_DOG_TRANSPORTATION;
    }

    @Override
    public String getInfoAboutAdgustmentPuppy() {
        return recommendation.RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_PUPPY;
    }

    @Override
    public String getInfoAboutAdgustmentDog() {
        return recommendation.RECOMMENDATIONS_FOR_HOME_ADJUSTMENT_FOR_DOG;
    }

    @Override
    public String getAdviceFromDoghandler() {
        return recommendation.RECOMMENDATIONS_FOR_THE_FIRST_MEETING_WITH_DOGHANDLER;
    }

    @Override
    public String getGoodDoghandlers() {
        return recommendation.RECOMMENDATIONS_OF_DOGSHANDLERS;
    }
}
