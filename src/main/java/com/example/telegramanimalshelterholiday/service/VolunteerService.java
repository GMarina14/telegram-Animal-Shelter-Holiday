package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Volunteer;

public interface VolunteerService {
    Volunteer getById(long id);

    Volunteer add(Volunteer volunteer);

    Volunteer update(Volunteer volunteer);


    void remove(long id);
}
