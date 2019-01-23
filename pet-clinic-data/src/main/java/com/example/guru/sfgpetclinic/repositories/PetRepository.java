package com.example.guru.sfgpetclinic.repositories;

import com.example.guru.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
