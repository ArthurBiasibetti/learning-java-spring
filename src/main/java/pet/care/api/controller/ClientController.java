package pet.care.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pet.care.api.client.Client;
import pet.care.api.client.ClientInsertDTO;
import pet.care.api.client.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Client> setClient(@RequestBody ClientInsertDTO data, UriComponentsBuilder uriBuilder) {
        Client client = service.save(data);
        var uri = uriBuilder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(client);
    }

    @GetMapping
    public ResponseEntity<Page<Client>> getClients(@PageableDefault(size = 3) Pageable page) {
        var pages = service.listClients(page);
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable int id) {
        return ResponseEntity.ok(service.findClient(id));
    }
}
