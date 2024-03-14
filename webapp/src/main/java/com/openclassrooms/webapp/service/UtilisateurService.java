package com.openclassrooms.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.webapp.model.Utilisateur;
import com.openclassrooms.webapp.repository.UtilisateurProxy;

@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurProxy utilisateurProxy;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
	    Utilisateur savedUtilisateur;

	    if (utilisateur.getId() == null) {
	    	utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
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
	
	public Utilisateur loadUser(Long id) {
		Utilisateur utilisateur = utilisateurProxy.findUtilisateurById(id);
		return utilisateur;
	}

	public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurProxy.updateUtilisateur(utilisateur);
    }


}
