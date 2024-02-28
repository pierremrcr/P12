package com.openclassrooms.projet12.model;

import java.util.List;

import com.openclassrooms.projet12.model.enums.TypeMiel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "miel")
public class Miel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	private String description;
	
	@Column(name="type_miel")
	private TypeMiel typeMiel;
	
	private double prix;
	private int stock;
	
	@OneToMany(mappedBy = "miel")
    private List<Detail_Commande> detailsCommande;
	
	
    public Miel() {
		super();
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
	
}