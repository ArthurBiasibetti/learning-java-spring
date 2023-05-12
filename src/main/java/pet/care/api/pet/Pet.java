package pet.care.api.pet;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pet.care.api.client.Client;

@Table(name = "pets")
@Entity(name = "Pet")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name="SEQ_PET_ID", sequenceName = "SEQ_PET_ID", allocationSize = 1)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties("pets")
    private Client owner;

    private String name;

    public Pet(String name, Client client) {
        this.owner = client;
        this.name = name;
    }
}
