package com.example.demo.message.request;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.User;

public class ContratRequert {
    private int numPolice;
    private Date dateEffet;
    private Date dateFinEffet;
    private String type;
    private String etat;
  

	




	public int getNumPolice() {
		return numPolice;
	}
	public void setNumPolice(int numPolice) {
		this.numPolice = numPolice;
	}
	public Date getDateEffet() {
		return dateEffet;
	}
	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}
	public Date getDateFinEffet() {
		return dateFinEffet;
	}
	public void setDateFinEffet(Date dateFinEffet) {
		this.dateFinEffet = dateFinEffet;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
    
}
