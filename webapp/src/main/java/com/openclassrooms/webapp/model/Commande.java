package com.openclassrooms.webapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.openclassrooms.webapp.model.enums.StatutCommande;

public class Commande {
	

	private Long id;
    private LocalDateTime dateCommande;
    private StatutCommande statut;
    private Utilisateur utilisateur;
    private double total;
    
    @JsonManagedReference
    private List<DetailCommande> detailCommandes = new ArrayList<>();
    
     
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<DetailCommande> getDetailCommandes() {
		return detailCommandes;
	}

	public void setDetailCommandes(List<DetailCommande> detailCommandes) {
		this.detailCommandes = detailCommandes;
	}
	
	public void ajouterDetailCommande(DetailCommande detailCommande) {
		this.detailCommandes.add(detailCommande);
		detailCommande.setCommande(this);
	}
	
	

}
