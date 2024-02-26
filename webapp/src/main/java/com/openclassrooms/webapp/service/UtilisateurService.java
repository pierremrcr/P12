package com.openclassrooms.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.webapp.model.Utilisateur;
import com.openclassrooms.webapp.repository.UtilisateurProxy;

@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurProxy utilisateurProxy;
	
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        
	    Utilisateur savedUtilisateur;

	    if (utilisateur.getId() == null) {
	        savedUtilisateur = utilisateurProxy.createUtilisateur(utilisateur);
	    } else {
	        savedUtilisateur = utilisateurProxy.updateUtilisateur(utilisateur);
	    }
	    return savedUtilisateur;
	}
	
	public Utilisateur login(String email, String password) {
		Utilisateur utilisateur = utilisateurProxy.login(email, password);
		return utilisateur;
	}


}
