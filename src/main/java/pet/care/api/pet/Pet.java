package pet.care.api.pet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pets")
@Entity(name = "Pet")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int owner_id;

    private String name;

    public Pet(PetInsertDTO data) {
        this.owner_id = data.owner_id();
        this.name = data.name();
    }
}
