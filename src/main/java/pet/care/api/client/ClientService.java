package pet.care.api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ClientService {

    @Autowired
    private ClienteRepository repository;

    public Client save(ClientInsertDTO data) {
        Client client = repository.save(new Client(data));
        return client;
    }

    public Page<Client> listUsers(Pageable page) {
        return repository.findAll(page);
    }
}
