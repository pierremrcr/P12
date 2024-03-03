package com.openclassrooms.projet12.model;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "detail_commande")
public class Detail_Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "miel_id", referencedColumnName = "id")
    private Miel miel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id", referencedColumnName = "id")
    private Commande commande;

    private int quantite;


    public Detail_Commande() {
    }

    public Detail_Commande(Miel miel, Commande commande, int quantite) {
        this.miel = miel;
        this.commande = commande;
        this.quantite = quantite;
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

