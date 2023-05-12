package pet.care.api.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pet.care.api.pet.Pet;

import java.util.List;

@Table(name = "users")
@Entity(name = "Client")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name="SEQ_CLIENT_ID", sequenceName = "SEQ_CLIENT_ID", allocationSize = 1)
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties("owner")
    private List<Pet> pets;

    public Client(ClientInsertDTO data) {
        this.name = data.name();
    }
}
