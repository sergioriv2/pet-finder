package com.example.demo.services;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PetService {
    private PetRepository petRepository;
    public List<Pet> getPets() {
        return this.petRepository.findAll();
    }

}
