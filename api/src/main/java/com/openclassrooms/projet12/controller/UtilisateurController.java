package com.openclassrooms.projet12.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.projet12.model.Adresse;
import com.openclassrooms.projet12.model.Utilisateur;
import com.openclassrooms.projet12.service.UtilisateurService;

@RestController
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping("/utilisateurs")
	public Iterable<Utilisateur> getAllUtilisateur() {
		return utilisateurService.getAllUtilisateur();
	}

	@GetMapping("/utilisateur/{id}")
	public Utilisateur getUtilisateur(@PathVariable("id") final Long id) {
		Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateur(id);
		if(utilisateur.isPresent()) {
			return utilisateur.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/login")
    public Utilisateur getUtilisateurByEmail(@PathVariable String email) {
        return utilisateurService.getUtilisateurByEmail(email);
    }

	@PostMapping("/utilisateur")
	public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
		return utilisateurService.saveUtilisateur(utilisateur);
	}

	public Utilisateur updateUtilisateur(@PathVariable("id") final Long id, @RequestBody Utilisateur utilisateur) {
		Optional<Utilisateur> u = utilisateurService.getUtilisateur(id);
		if(u.isPresent()) {
			Utilisateur currentUtilisateur = u.get();

			String nom = utilisateur.getNom();
			if (nom != null) {
				currentUtilisateur.setNom(nom);
			}

			String prenom = utilisateur.getPrenom();
			if (prenom != null) {
				currentUtilisateur.setPrenom(prenom);
			}
			
			String adresseMail = utilisateur.getAdresseMail();
			if (adresseMail != null) {
				currentUtilisateur.setAdresseMail(adresseMail);
			}

			String motDePasse = utilisateur.getMotDePasse();
			if (motDePasse != null) {
				currentUtilisateur.setMotDePasse(motDePasse);
			}

			Adresse adresse = utilisateur.getAdresse();
			if (adresse != null) {
				currentUtilisateur.setAdresse(adresse);
			}

			utilisateurService.saveUtilisateur(currentUtilisateur);
			return currentUtilisateur;

		} else {
			return null;
		}
	}
}
