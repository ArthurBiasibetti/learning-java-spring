package pet.care.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pet.care.api.client.Client;
import pet.care.api.client.ClienteRepository;
import pet.care.api.pet.Pet;
import pet.care.api.pet.PetInsertDTO;
import pet.care.api.pet.PetRepository;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClienteRepository clientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Pet> setPet(@RequestBody @Valid PetInsertDTO data, UriComponentsBuilder uriBuilder) {
        Client owner = clientRepository.getReferenceById(data.owner_id());
        Pet pet = new Pet(data.name(), owner);
        petRepository.save(pet);

        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();

        return ResponseEntity.created(uri).body(pet);


    }

    @GetMapping
    public ResponseEntity<List<Pet>> getPets() {
        return ResponseEntity.ok(petRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable int id) {
        return ResponseEntity.ok(petRepository.findById(id).get());
    }

}
