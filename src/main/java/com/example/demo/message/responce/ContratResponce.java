package com.example.demo.message.responce;

import java.util.List;

import com.example.demo.model.Contrat;

public class ContratResponce {
	private List<Contrat> contrats;
	private double totalPages;
	public List<Contrat> getContrats() {
		return contrats;
	}
	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}
	public double getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}
	public ContratResponce(List<Contrat> contrats, double totalPages) {
		super();
		this.contrats = contrats;
		this.totalPages = totalPages;
	}
	public ContratResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
