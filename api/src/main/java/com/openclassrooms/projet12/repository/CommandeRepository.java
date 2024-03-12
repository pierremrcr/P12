package com.openclassrooms.projet12.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.projet12.model.Commande;

public interface CommandeRepository extends CrudRepository<Commande, Long> {

	List<Commande> findByUtilisateurId(Long utilisateurId);
}
