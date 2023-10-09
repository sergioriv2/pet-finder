package com.example.demo.controllers;

import com.example.demo.models.Pet;
import com.example.demo.services.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("pets")
public class PetController {
    private PetService petService;

    @GetMapping()
    public List<Pet> getPets() {
        return this.petService.getPets();
    }
}
