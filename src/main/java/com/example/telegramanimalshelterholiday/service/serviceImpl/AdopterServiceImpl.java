package com.example.telegramanimalshelterholiday.service.serviceImpl;

import com.example.telegramanimalshelterholiday.model.Adopter;
import com.example.telegramanimalshelterholiday.repository.AdopterRepository;
import com.example.telegramanimalshelterholiday.service.AdopterService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AdopterServiceImpl implements AdopterService {
    private final AdopterRepository adopterRepository;

    public AdopterServiceImpl(AdopterRepository adopterRepository) {
        this.adopterRepository = adopterRepository;
    }

    @Override
    public Adopter addNewAdopter(Adopter adopter) {
        return adopterRepository.save(adopter);

    }

    @Override
    public Collection<Adopter> getAllAdopters() {
        return adopterRepository.getAll();
    }

    @Override
    public Adopter update(Adopter adopter) {
        return adopterRepository.save(adopter);

    }

    @Override
    public Optional<Adopter> getById(Long id) {
        return adopterRepository.findById(id);
    }

    @Override
    public void removeAdopterById(Long id) {
        adopterRepository.deleteById(id);

    }
}
