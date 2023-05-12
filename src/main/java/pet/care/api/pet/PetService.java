package pet.care.api.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pet.care.api.client.Client;
import pet.care.api.client.ClienteRepository;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Pet save(PetInsertDTO data) throws Exception {
        if(clienteRepository.existsById(data.owner_id())){
            Client owner = clienteRepository.getReferenceById(data.owner_id());
            Pet pet = repository.save(new Pet(data.name(), owner));
            return pet;
        }
        throw new Exception("User does not exist");
    }

    public Page<Pet> listPest(Pageable page) {
        return repository.findAll(page);
    }
}
