package com.openclassrooms.webapp.model;

import java.util.List;

public class Utilisateur {
	
    private Long id;
	private String nom;
	private String prenom;
	private String adresseMail;
	private String motDePasse;
	private String telephone;
	private Adresse adresse;
	private List<Commande> commandes;
	
	
	public Utilisateur() {	
	}
	
	public Long getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getAdresseMail() {
		return adresseMail;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}  
	
	
		
}
