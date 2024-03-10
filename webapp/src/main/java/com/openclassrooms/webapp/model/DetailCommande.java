package com.openclassrooms.webapp.model;


public class DetailCommande {

	private int id;
	private Miel miel;
	private Commande commande;
	private int quantite;


	public DetailCommande() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Miel getMiel() {
		return miel;
	}


	public void setMiel(Miel miel) {
		this.miel = miel;
	}


	public Commande getCommande() {
		return commande;
	}


	public void setCommande(Commande commande) {
		this.commande = commande;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
}
