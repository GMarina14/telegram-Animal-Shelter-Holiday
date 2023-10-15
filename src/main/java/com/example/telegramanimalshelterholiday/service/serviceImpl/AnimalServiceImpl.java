package com.example.telegramanimalshelterholiday.service.serviceImpl;

import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.exception.NotFoundConfigException;
import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.repository.AnimalRepository;
import com.example.telegramanimalshelterholiday.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private static final Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);

    @Override
    public Animal getById(long id) {
        logger.debug("Calling method getById");
        return animalRepository.findById(id).orElseThrow(() -> new NotFoundConfigException());
    }

    @Override
    public Animal add(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal update(Animal animal) {
        logger.debug("Calling method update");
        return animalRepository.save(animal);
    }

    @Override
    public void remove(long id) {
        logger.debug("Calling method remove");
        animalRepository.deleteById(id);
    }

    @Override
    public List<Animal> getAnimalByPetsSpecies(PetsSpecies petsSpecies) {
        logger.debug("Calling method getAnimalByPetsSpecies");
        return animalRepository.findAllAnimalPetsSpeciesFilter(petsSpecies);
    }

}

