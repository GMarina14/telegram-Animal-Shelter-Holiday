package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Adopter;
import com.example.telegramanimalshelterholiday.model.Volunteer;

import java.util.List;

public interface VolunteerService {
    Volunteer getById(long id);

    Volunteer add(Volunteer volunteer);

    Volunteer update(Volunteer volunteer);


    void remove(long id);

    List<Adopter> getAllAdopter(long id);

    Volunteer getRandomVolunteer();
}
