package udemy.studying.petclinic.model;

import lombok.*;
import udemy.studying.petclinic.model.person.Person;
import udemy.studying.petclinic.model.pet.Pet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
@Setter
@Getter
@NoArgsConstructor
public class Owner extends Person {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, Set<Pet> pets) {
        super(id, firstName, lastName);

        if(pets != null) {
            this.pets = pets;
        }
    }

    public Pet getPet(Long petId) {
        return pets
                .stream()
                .filter(pet -> pet.getId() == petId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

}
