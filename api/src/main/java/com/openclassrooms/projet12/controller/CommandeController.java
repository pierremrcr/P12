package com.openclassrooms.projet12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.projet12.model.Commande;
import com.openclassrooms.projet12.model.DetailCommande;
import com.openclassrooms.projet12.model.Utilisateur;
import com.openclassrooms.projet12.service.CommandeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CommandeController {

	@Autowired
	private final CommandeService commandeService;

	@Autowired
	public CommandeController(CommandeService commandeService) {
		this.commandeService = commandeService;
	}


	@PostMapping("/commande")
	public ResponseEntity<String>  createCommande(@RequestBody Commande commande) {
		try {
			List<DetailCommande> detailCommandes = new ArrayList<>();

			if (commande.getDetailCommandes() != null) {

				for (DetailCommande detailCommande : commande.getDetailCommandes()) {

					if (detailCommande != null) {
						detailCommande.setCommande(commande);
						detailCommandes.add(detailCommande);
					}
				}
			}

			commande.setDetailCommandes(detailCommandes);
			commandeService.saveCommande(commande);

			return ResponseEntity.ok("Commande crée avec succès !");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de la commande : " + e.getMessage());
		}
	}


	@GetMapping("/commandes")
	public Iterable<Commande> getAllCommandes() {
		return commandeService.findAllCommandes();

	}

	@GetMapping("/commande/{id}")
	public Commande getCommandeById(@PathVariable("id") Long id) {
		Optional<Commande> commande = commandeService.findCommandeById(id);
		if (commande.isPresent()) {
			return commande.get();
		} else {
			return null;
		}
	}


	@PutMapping("/{id}")
	public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commandeDetails) {
		Commande miseAJourCommande = commandeService.updateCommande(id, commandeDetails);
		return ResponseEntity.ok(miseAJourCommande);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
		commandeService.deleteCommandeById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/mesCommandes/{id}")
	public Iterable<Commande> getCommandesUtilisateur(@PathVariable("id") Long id) {
		Iterable<Commande> commandesUtilisateur = this.commandeService.findCommandesByUtilisateur(id);
		return commandesUtilisateur;  
	}
}

