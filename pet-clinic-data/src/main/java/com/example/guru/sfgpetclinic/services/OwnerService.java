package com.example.guru.sfgpetclinic.services;

import com.example.guru.sfgpetclinic.model.Owner;

import java.util.List;


public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
