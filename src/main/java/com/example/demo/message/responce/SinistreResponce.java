package com.example.demo.message.responce;

import java.util.List;
import com.example.demo.model.Sinistre;

public class SinistreResponce {
	private List<Sinistre> Sinistres;
	private double totalPages;
	public List<Sinistre> getSinistres() {
		return Sinistres;
	}
	public void setSinistres(List<Sinistre> sinistres) {
		Sinistres = sinistres;
	}
	public double getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}
	public SinistreResponce(List<Sinistre> sinistres, double totalPages) {
		super();
		Sinistres = sinistres;
		this.totalPages = totalPages;
	}
	public SinistreResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
