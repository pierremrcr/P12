package com.openclassrooms.projet12.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		try {
			Utilisateur utilisateur = utilisateurService.authenticate(loginRequest.getAdresseMail(), loginRequest.getMotDePasse());
			if (utilisateur != null && verifyPassword(loginRequest.getMotDePasse(), utilisateur.getMotDePasse()))  {
				return ResponseEntity.ok(utilisateur);
			} else { 
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de l'authentification");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur serveur est survenue");
		}
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
	
	@DeleteMapping("/utilisateur/{id}")
	public void deleteUtilisateur(@PathVariable("id") Long id) {
		utilisateurService.deleteUtilisateur(id);
	}
	
	public boolean verifyPassword(String motDePasseRequest, String motDePasse) {
	    if (motDePasseRequest.equals(motDePasse)) {
	    	return true;
	    }
	    return false;
	}
	
	  public static class LoginRequest {
		    private String adresseMail; 
		    private String motDePasse;

	        
	        public LoginRequest() {
	        }


			public String getAdresseMail() {
				return adresseMail;
			}


			public void setAdresseMail(String adresseMail) {
				this.adresseMail = adresseMail;
			}


			public String getMotDePasse() {
				return motDePasse;
			}


			public void setMotDePasse(String motDePasse) {
				this.motDePasse = motDePasse;
			}
	        
	       
	    }
}
