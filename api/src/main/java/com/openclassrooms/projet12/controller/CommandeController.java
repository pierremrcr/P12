package com.openclassrooms.projet12.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.projet12.model.Commande;
import com.openclassrooms.projet12.model.Detail_Commande;
import com.openclassrooms.projet12.service.CommandeService;

@RestController
public class CommandeController {

	@Autowired
	private CommandeService commandeService;

	@GetMapping("/commandes")
	public Iterable<Commande> getAllCommande() {
		return commandeService.getAllCommande();
	}

	@GetMapping("/commande/{id}")
	public Commande getCommande(@PathVariable("id") final Long id) {
		Optional<Commande> commande = commandeService.getCommande(id);
		return commande.orElse(null);
	}

	@PostMapping("/commande")
	public Commande createCommande(@RequestBody Commande commande) {
		return commandeService.saveCommande(commande);
	}

	@PutMapping("/commande/{id}")
	public Commande updateCommande(@PathVariable("id") final Long id, @RequestBody Commande commande) {
		Optional<Commande> c = commandeService.getCommande(id);
		if (c.isPresent()) {
			Commande currentCommande = c.get();

			// Mise à jour des attributs de la commande
			currentCommande.setDateCommande(commande.getDateCommande());
			currentCommande.setStatut(commande.getStatut());

			if (commande.getDetailsCommande() != null) {
				for (Detail_Commande detailCommande : commande.getDetailsCommande()) {
					if (detailCommande.getId() != 0) {
						// Mettre à jour les détails de commande existants
						for (Detail_Commande currentDetail : currentCommande.getDetailsCommande()) {
							if (currentDetail.getId() == detailCommande.getId()) {
								currentDetail.setMiel(detailCommande.getMiel());
								currentDetail.setQuantite(detailCommande.getQuantite());
								// Vous pouvez mettre à jour d'autres attributs de Detail_Commande ici si nécessaire
							}
						}
					} else {
						// Ajouter de nouveaux détails de commande
						currentCommande.addDetailCommande(detailCommande);
					}
				}
			}

			// Mise à jour de la commande
			commandeService.saveCommande(currentCommande);
			return currentCommande;
		} else {
			return null;
		}
	}
}


