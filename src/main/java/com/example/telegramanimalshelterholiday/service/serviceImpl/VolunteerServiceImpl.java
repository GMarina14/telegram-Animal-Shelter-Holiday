package com.example.telegramanimalshelterholiday.service.serviceImpl;

import com.example.telegramanimalshelterholiday.exception.NotFoundConfigException;
import com.example.telegramanimalshelterholiday.model.Volunteer;
import com.example.telegramanimalshelterholiday.repository.VolunteerRepository;
import com.example.telegramanimalshelterholiday.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {
    private final VolunteerRepository volunteerRepository;

    private final Logger logger = LoggerFactory.getLogger(VolunteerService.class);

    @Override
    public Volunteer getById(long id) {
        logger.debug("Calling method getById");
        return volunteerRepository.findById(id).orElseThrow(() -> new NotFoundConfigException());
    }

    @Override
    public Volunteer add(Volunteer volunteer) {
        logger.debug("Calling method add");
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer update(Volunteer volunteer) {
        logger.debug("Calling method update");
        return volunteerRepository.save(volunteer);
    }

    @Override
    public void remove(long id) {
        logger.debug("Calling method remove");
        volunteerRepository.deleteById(id);
    }

    /**
     * метод вызова любого(рандомного) волонтера для bot.
     */
}
