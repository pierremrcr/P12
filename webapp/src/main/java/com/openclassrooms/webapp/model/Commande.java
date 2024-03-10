package com.openclassrooms.webapp.model;

import java.util.Date;
import java.util.List;

import com.openclassrooms.webapp.model.enums.StatutCommande;

public class Commande {
	

	private Long id;
    private Date dateCommande;
    private StatutCommande statut;
    private Utilisateur utilisateur;
    private List<DetailCommande> detailsCommande;
     
	public Commande() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
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
	public List<DetailCommande> getDetailsCommande() {
		return detailsCommande;
	}
	public void setDetailsCommande(List<DetailCommande> detailsCommande) {
		this.detailsCommande = detailsCommande;
	}
    
    

}
