package com.openclassrooms.webapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class DetailCommande {
	
	private Long id;
	
	@JsonBackReference
	private Commande commande;
	
	private Miel miel;
	private int quantite;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public Miel getMiel() {
		return miel;
	}
	public void setMiel(Miel miel) {
		this.miel = miel;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	

}
