package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.repository.PetRepository;
import com.pe.edu.upc.petcare.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;
    /*
    @Override
    public List<Pet> findPetAll() {
        return petRepository.findAll();
    }
    @Override
    public Pet createPet(Pet pet) {
        /*Pet petDB= petRepository.findByNumberId((pet.getNumberId()));
        if (petDB != null){
            return petDB;
        }
        pet.setState("CREATED");
       // Pet petDB = petRepository.save(pet);
        return petRepository.save(pet);
    }
*/
    @Override
    public Pet save(Pet pet) throws Exception {
        Pet petDB = petRepository.findById((pet.getId())).orElse(null);
        if (petDB != null) {
            return petDB;
        }
        return petRepository.save(pet);
    }

    @Override
    public Pet update(Pet pet) throws Exception {
        Pet petDB= petRepository.findById(pet.getId()).orElse(null);
        if (petDB == null){
            return null;
        }
        petDB.setName(pet.getName());
        petDB.setAge(pet.getAge());
        petDB.setBreed(pet.getBreed());
        petDB.setPhoto(pet.getPhoto());
        petDB.setSex(pet.getSex());

        return petRepository.save(petDB);
    }



    @Override
    public Pet findById(Long id) throws Exception {
        return petRepository.findById(id).orElse(null);
    }



    @Override
    public List<Pet> findAll() throws Exception {
        return petRepository.findAll();
    }

    @Override
    public Pet deleteById(Long id) throws Exception {
        Pet petDB= petRepository.findById(id).orElse(null);

        if (petDB != null){
            petRepository.deleteById(petDB.getId());
            return petDB;
        }

        return null;
    }
}