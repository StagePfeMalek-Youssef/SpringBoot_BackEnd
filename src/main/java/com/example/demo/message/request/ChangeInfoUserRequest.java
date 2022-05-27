package com.example.demo.message.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ChangeInfoUserRequest {
	    private String prenom;
	    private String nom;
	    private String datenaissance;
	    private String codepostal;
	    private String telephone;
	    private String ville;
	    private String cin;
	    private String email;
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
	
		public String getDatenaissance() {
			return datenaissance;
		}
		public void setDatenaissance(String datenaissance) {
			this.datenaissance = datenaissance;
		}
		public String getCodepostal() {
			return codepostal;
		}
		public void setCodepostal(String codepostal) {
			this.codepostal = codepostal;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getVille() {
			return ville;
		}
		public void setVille(String ville) {
			this.ville = ville;
		}
		public String getCin() {
			return cin;
		}
		public void setCin(String cin) {
			this.cin = cin;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
	    

}
