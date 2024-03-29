package udemy.studying.petclinic.services.springdatajpa;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import udemy.studying.petclinic.model.Owner;
import udemy.studying.petclinic.repositories.ContactInfoRepository;
import udemy.studying.petclinic.repositories.OwnerRepository;
import udemy.studying.petclinic.repositories.PetRepository;
import udemy.studying.petclinic.repositories.PetTypeRepository;
import udemy.studying.petclinic.services.OwnerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jt on 8/5/18.
 */
@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;
    private final ContactInfoRepository contactInfoRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                             PetTypeRepository petTypeRepository, ContactInfoRepository contactInfoRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
        this.contactInfoRepository = contactInfoRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }


    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        contactInfoRepository.save(object.getContactInfo());
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }
}
