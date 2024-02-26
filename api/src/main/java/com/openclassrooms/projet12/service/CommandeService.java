package com.openclassrooms.projet12.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.projet12.model.Commande;
import com.openclassrooms.projet12.repository.CommandeRepository;

import lombok.Data;

@Data
@Service
public class CommandeService {
	
		@Autowired
		private CommandeRepository commandeRepository;
		
		public Optional<Commande> getCommande(final Long id) {
	        return commandeRepository.findById(id);
	    }

	    public Iterable<Commande> getAllCommande() {
	        return commandeRepository.findAll();
	    }

	    public void deleteCommande(final Long id) {
	    	commandeRepository.deleteById(id);
	    }

	    public Commande saveCommande(Commande commande) {
	    	Commande savedCommande = commandeRepository.save(commande);
	        return savedCommande;
	    }

	}


