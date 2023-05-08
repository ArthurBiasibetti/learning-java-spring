package pet.care.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pet.care.api.pet.Pet;
import pet.care.api.pet.PetInsertDTO;
import pet.care.api.pet.PetRepository;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository repository;

    @PostMapping
    @Transactional
    public Pet setPet(@RequestBody PetInsertDTO data) {
        return repository.save(new Pet(data));
    }

    @GetMapping
    public List<Pet> getPet() {
        return repository.findAll();
    }
}
