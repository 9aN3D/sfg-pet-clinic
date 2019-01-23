package com.example.guru.sfgpetclinic.services.map;

import com.example.guru.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Sam";

    @BeforeEach
    void setUp() {

        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(1L).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1,ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void saveExisingId() {
        Long id = 2L;

        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId());

    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner sam = ownerMapService.findByLastName(lastName);

        assertNotNull(sam);

        assertEquals(ownerId,sam.getId());
    }

    @Test
    void shouldFindAllByLastNameLike() {
        Owner AdamSmith = ownerMapService.save(Owner.builder().id(2L).firstName("Adam").lastName("Smith").build());
        Owner JasonSmith = ownerMapService.save(Owner.builder().id(3L).firstName("Jason").lastName("Smith").build());

        List<Owner> smiths = ownerMapService.findAllByLastNameLike("Smith");

        assertNotNull(smiths);
        assertEquals(2,smiths.size());
        assertEquals("Jason",smiths.get(0).getFirstName());
        assertEquals("Adam",smiths.get(1).getFirstName());

    }


    @Test
    void findByLastNameNotFound() {
        Owner sam = ownerMapService.findByLastName("foo");

        assertNull(sam);
    }
}