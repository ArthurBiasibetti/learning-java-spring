package pet.care.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pet.care.api.client.Client;
import pet.care.api.client.ClienteRepository;
import pet.care.api.pet.Pet;
import pet.care.api.pet.PetInsertDTO;
import pet.care.api.pet.PetRepository;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClienteRepository clientRepository;

    @PostMapping
    @Transactional
    public Pet setPet(@RequestBody @Valid PetInsertDTO data) {
        Client owner = clientRepository.getReferenceById(data.owner_id());
        Pet pet = new Pet(data.name(), owner);
        petRepository.save(pet);

        return pet;


    }

    @GetMapping
    public List<Pet> getPet() {
        return petRepository.findAll();
    }
}
