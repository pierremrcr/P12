package com.openclassrooms.webapp.model.enums;

public enum TypeMiel {
	
	MIEL_DE_FLEURS_SAUVAGES("Miel de fleurs sauvages"),
	MIEL_D_ACACIA("Miel d'acacia"),
	MIEL_DE_LAVANDE("Miel de lavande"),
	MIEL_DE_THYM("Miel de thym"),
	MIEL_DE_MANUKA("Miel de manuka"),
	MIEL_DE_SARRASIN("Miel de sarrasin"),
	MIEL_DE_CITRONNIER("Miel de citronnier"),
	MIEL_D_EUCALYPTUS("Miel d'eucalyptus"),
	MIEL_DE_CHATAIGNIER("Miel de châtaignier"),
	MIEL_DE_TOURNESOL("Miel de tournesol"),
	MIEL_DE_FORET("Miel de forêt"),
	MIEL_DE_MONTAGNE("Miel de montagne");

	private final String libelle;

	TypeMiel(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}
}



