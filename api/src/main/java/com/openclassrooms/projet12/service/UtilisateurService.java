package com.openclassrooms.projet12.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.projet12.model.Utilisateur;
import com.openclassrooms.projet12.repository.UtilisateurRepository;


@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	
	
	public Optional<Utilisateur> getUtilisateur(final Long id) {
        return utilisateurRepository.findById(id);
    }
	
	public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByAdresseMail(email);
    }

    public Iterable<Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }

    public void deleteUtilisateur(final Long id) {
    	utilisateurRepository.deleteById(id);
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
    	Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return savedUtilisateur;
    }

    public Utilisateur authenticate(String email, String password) {
        Utilisateur utilisateur = utilisateurRepository.findByAdresseMail(email);
        if (utilisateur != null) {
            return utilisateur;
        } else {
            return null;
        }
    }

}
