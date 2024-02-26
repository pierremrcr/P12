package com.openclassrooms.projet12.model.enums;

public enum StatutCommande {
	
	EN_ATTENTE("En attente"),
    EN_COURS("En cours"),
    EXPEDIEE("Expédiée"),
    ANNULEE("Annulée");

    private final String libelle;

    StatutCommande(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

}
