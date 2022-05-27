package com.example.demo.message.responce;

import java.util.List;
import com.example.demo.model.Reunion;

public class ReunionResponce {
	private List<Reunion> Reunions;
	private double totalPages;
	public List<Reunion> getReunions() {
		return Reunions;
	}
	public void setReunions(List<Reunion> reunions) {
		Reunions = reunions;
	}
	public double getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}
	public ReunionResponce(List<Reunion> reunions, double totalPages) {
		super();
		Reunions = reunions;
		this.totalPages = totalPages;
	}
	public ReunionResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
