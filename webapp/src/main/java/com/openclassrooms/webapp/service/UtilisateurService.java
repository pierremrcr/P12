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
        System.out.println(utilisateur.getAdresse().getRue());
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

	public void deleteUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurProxy.findUtilisateurById(id);
        if (utilisateur != null) {
        	utilisateurProxy.deleteUtilisateurById(id);
        }
    }

	public Utilisateur getUtilisateurById(Long id) {
		Utilisateur utilisateur = utilisateurProxy.findUtilisateurById(id);
		return utilisateur;
	}

	public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurProxy.updateUtilisateur(utilisateur);
    }


}
