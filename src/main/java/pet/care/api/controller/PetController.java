package pet.care.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pet.care.api.pet.Pet;
import pet.care.api.pet.PetInsertDTO;
import pet.care.api.pet.PetService;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Pet> setPet(@RequestBody @Valid PetInsertDTO data, UriComponentsBuilder uriBuilder) throws Exception {
        Pet pet = service.save(data);
        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(pet);
    }

    @GetMapping
    public ResponseEntity<Page<Pet>> getPets(Pageable page) {
        return ResponseEntity.ok(service.listPest(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable int id) {
        return ResponseEntity.ok(service.findPet(id));
    }

}
