package com.example.demo.message.responce;

import java.util.List;
import com.example.demo.model.Suggestion;

public class SuggestionResponce {
	private List<Suggestion> Suggestions;
	private double totalPages;
	public List<Suggestion> getSuggestions() {
		return Suggestions;
	}
	public void setSuggestions(List<Suggestion> suggestions) {
		Suggestions = suggestions;
	}
	public double getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}
	public SuggestionResponce(List<Suggestion> suggestions, double totalPages) {
		super();
		Suggestions = suggestions;
		this.totalPages = totalPages;
	}
	public SuggestionResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
