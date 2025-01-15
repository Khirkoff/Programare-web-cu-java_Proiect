package com.example.pet_adoption_system.Service;

import com.example.pet_adoption_system.Model.Pet;
import com.example.pet_adoption_system.Repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

}