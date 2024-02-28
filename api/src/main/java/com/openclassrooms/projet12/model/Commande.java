package com.openclassrooms.projet12.model;

import java.util.Date;
import java.util.List;

import com.openclassrooms.projet12.model.enums.StatutCommande;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "commande")
public class Commande {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date dateCommande;
	
	@Enumerated(EnumType.STRING)
    private StatutCommande statut;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", referencedColumnName = "id")
    private Utilisateur utilisateur;
    
    @OneToMany(mappedBy = "commande")
    private List<Detail_Commande> detailsCommande;

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

	public List<Detail_Commande> getDetailsCommande() {
		return detailsCommande;
	}

	public void setDetailsCommande(List<Detail_Commande> detailsCommande) {
		this.detailsCommande = detailsCommande;
	}
	
	
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void addDetailCommande(Detail_Commande detailCommande) {
        detailsCommande.add(detailCommande);
        detailCommande.setCommande(this);
    }

    public void removeDetailCommande(Detail_Commande detailCommande) {
        detailsCommande.remove(detailCommande);
        detailCommande.setCommande(null);
    }
    
	
    
}

