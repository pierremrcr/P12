package com.openclassrooms.webapp.controller.command;

public class UtilisateurRegisterForm {
	
	    private String adresseMail;
	    private String motDePasse;
	    private String prenom;;
	    private String nom;
	    private String telephone;
	   

		public UtilisateurRegisterForm() {
		}

		public String getAdresseMail() {
			return adresseMail;
		}

		public String getMotDePasse() {
			return motDePasse;
		}

		public String getPrenom() {
			return prenom;
		}

		public String getNom() {
			return nom;
		}

		public String getTelephone() {
			return telephone;
		}
		
		

		public void setAdresseMail(String adresseMail) {
			this.adresseMail = adresseMail;
		}

		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		@Override
		public String toString() {
			return "UtilisateurRegisterForm [adresseMail=" + adresseMail + ", motDePasse=" + motDePasse + ", prenom="
					+ prenom + ", nom=" + nom + ", telephone=" + telephone + "]";
		}
		
		
	            
	}


