package com.openclassrooms.webapp.model;

import java.util.List;

import com.openclassrooms.webapp.model.enums.TypeMiel;

public class Miel {
	
private Long id;
	
	private String nom;
	private String description;
	private TypeMiel typeMiel;
	private double prix;
	private int stock;
    private List<DetailCommande> detailsCommande;
    
     
	public Miel() {
	}

	public Miel(Long id, String nom, String description, double prix) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TypeMiel getTypeMiel() {
		return typeMiel;
	}
	public void setTypeMiel(TypeMiel typeMiel) {
		this.typeMiel = typeMiel;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public List<DetailCommande> getDetailsCommande() {
		return detailsCommande;
	}
	public void setDetailsCommande(List<DetailCommande> detailsCommande) {
		this.detailsCommande = detailsCommande;
	}   

}
