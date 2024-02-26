package com.openclassrooms.projet12.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.projet12.model.Commande;

public interface CommandeRepository extends CrudRepository<Commande, Long> {

}
