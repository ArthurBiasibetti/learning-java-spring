package pet.care.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pet.care.api.client.Client;
import pet.care.api.client.ClientInsertDTO;
import pet.care.api.client.ClientService;
import pet.care.api.client.ClienteRepository;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    @Transactional
    public void setClient(@RequestBody ClientInsertDTO data) {
        service.save(data);
    }

    @GetMapping
    public Page<Client> getClients(@PageableDefault(size = 3) Pageable page) {
        return service.listUsers(page);
    }
}
