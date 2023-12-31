package com.example.telegramanimalshelterholiday.service.serviceImpl;

import com.example.telegramanimalshelterholiday.exception.NotFoundConfigException;
import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.model.Shelter;
import com.example.telegramanimalshelterholiday.model.Volunteer;
import com.example.telegramanimalshelterholiday.repository.ShelterRepository;
import com.example.telegramanimalshelterholiday.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

    private static final Logger logger = LoggerFactory.getLogger(ShelterServiceImpl.class);

    @Override
    public Shelter getById(long id) {
        logger.debug("Calling method getById");
        return shelterRepository.findById(id).orElseThrow(() -> new NotFoundConfigException());
    }

    @Override
    public Shelter add(Shelter shelter) {
        logger.debug("Calling method add");
        return shelterRepository.save(shelter);
    }

    @Override
    public Shelter update(Shelter shelter) {
        logger.debug("Calling method update");
        return shelterRepository.save(shelter);
    }

    /**
     * Метод возращает всех животных хранящихся в базе данных приюта
     */
    @Override
    public List<Animal> getAllAnimal(long id) {
        logger.debug("Calling method getAllAnimal");
        return shelterRepository.findById(id).get().getAnimalList();
    }

    /**
     * Метод возращает всех волонтеров хранящихся в базе данных приюта
     */
    @Override
    public List<Volunteer> getAllVolunteer(long id) {
        logger.debug("Calling method getAllVolunteer");
        return shelterRepository.findById(id).get().getVolunteerList();
    }

    /**
     * Метод возращает всех клиентов хранящихся в базе данных приюта
     */
    @Override
    public List<Client> getAllClient(long id) {
        logger.debug("Calling method getAllClient");
        return shelterRepository.findById(id).get().getClientsList();
    }
}
