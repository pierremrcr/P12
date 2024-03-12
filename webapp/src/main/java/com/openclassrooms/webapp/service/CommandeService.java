package com.openclassrooms.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.webapp.model.Commande;
import com.openclassrooms.webapp.model.Utilisateur;
import com.openclassrooms.webapp.repository.CommandeProxy;

@Service
public class CommandeService {
	
	@Autowired
	private CommandeProxy commandeProxy;
	
	  public Iterable<Commande> getDetailCommandesList() {
		  return commandeProxy.getCommandes();
		  
	  }

	public Commande saveCommande(Commande commande) {
		Commande savedCommande;
		savedCommande = this.commandeProxy.createCommande(commande);
		return savedCommande;
	}

	public Iterable<Commande> findCommandesByUtilisateur(Utilisateur utilisateur) {
		return commandeProxy.findCommandesByUtilisateur(utilisateur);
	}
	  
	  

}
