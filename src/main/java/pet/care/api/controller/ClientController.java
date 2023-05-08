package pet.care.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pet.care.api.client.Client;
import pet.care.api.client.ClienteInsertDTO;
import pet.care.api.client.ClienteRepository;

import java.util.List;


@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public void setClient(@RequestBody ClienteInsertDTO data) {
        repository.save(new Client(data));
    }

    @GetMapping
    public Page<Client> getClients(@PageableDefault(size = 3) Pageable page) {
        return repository.findAll(page);
    }
}