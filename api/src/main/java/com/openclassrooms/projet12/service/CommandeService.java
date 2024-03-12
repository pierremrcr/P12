package com.openclassrooms.projet12.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.projet12.model.Commande;
import com.openclassrooms.projet12.repository.CommandeRepository;

@Service
public class CommandeService {

	@Autowired
    private CommandeRepository commandeRepository;

    
    @Transactional
    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    
    public Iterable<Commande> findAllCommandes() {
        return commandeRepository.findAll();
    }

    
    public Optional<Commande> findCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    
    @Transactional
    public Commande updateCommande(Long id, Commande commandeDetails) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id " + id));
        
        commande.setDateCommande(commandeDetails.getDateCommande());
        commande.setStatut(commandeDetails.getStatut());
        commande.setUtilisateur(commandeDetails.getUtilisateur());
        
        return commandeRepository.save(commande);
    }

    
    @Transactional
    public void deleteCommandeById(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id " + id));
        commandeRepository.delete(commande);
    }


	public Iterable<Commande> findCommandesByUtilisateur(Long utilisateurId) {
		return commandeRepository.findByUtilisateurId(utilisateurId);
	}
    
}
