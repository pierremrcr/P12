package com.openclassrooms.webapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openclassrooms.webapp.model.enums.StatutCommande;

public class Commande {
	

	private Long id;
    private LocalDateTime dateCommande;
    private StatutCommande statut;
    private Utilisateur utilisateur;
    private List<Miel> miels = new ArrayList<>();
    private double total;
     
	public Commande() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(LocalDateTime localDateTime) {
		this.dateCommande = localDateTime;
	}
	public StatutCommande getStatut() {
		return statut;
	}
	public void setStatut(StatutCommande statut) {
		this.statut = statut;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	public List<Miel> getMiels() {
		return miels;
	}

	public void setMiels(List<Miel> miels) {
		this.miels = miels;
	}

	public void ajouterMiel(Miel miel) {
        miels.add(miel);
    }

	public double getTotal() {
		double total = 0.0;
        for (Miel miel : miels) {
            total += miel.getPrix() * miel.getQuantite();
        }
        return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	

}
