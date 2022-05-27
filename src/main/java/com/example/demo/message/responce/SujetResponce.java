package com.example.demo.message.responce;

import java.util.List;
import com.example.demo.model.Sujet;

public class SujetResponce {
	private List<Sujet> sujets;
	private double totalPages;
	public List<Sujet> getSujets() {
		return sujets;
	}
	public void setSujets(List<Sujet> sujets) {
		this.sujets = sujets;
	}
	public double getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}
	public SujetResponce(List<Sujet> sujets, double totalPages) {
		super();
		this.sujets = sujets;
		this.totalPages = totalPages;
	}
	public SujetResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
}
