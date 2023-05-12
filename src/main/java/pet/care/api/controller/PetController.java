package pet.care.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pet.care.api.pet.Pet;
import pet.care.api.pet.PetInsertDTO;
import pet.care.api.pet.PetRepository;
import pet.care.api.pet.PetService;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService service;

    @PostMapping
    @Transactional
    public Pet registerPet(@RequestBody @Valid PetInsertDTO data) throws Exception {
        return service.save(data);
    }

    @GetMapping
    public Page<Pet> getPet(Pageable page) { return service.listPest(page); }
}
