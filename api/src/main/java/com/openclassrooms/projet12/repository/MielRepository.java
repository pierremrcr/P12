package com.openclassrooms.projet12.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.projet12.model.Miel;

@Repository
public interface MielRepository extends CrudRepository<Miel, Long> {

}
