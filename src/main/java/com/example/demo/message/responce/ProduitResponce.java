package com.example.demo.message.responce;

import java.util.List;

import com.example.demo.model.Produit;

public class ProduitResponce {
private List<Produit> produits;
private double totalPages;
public List<Produit> getProduits() {
	return produits;
}
public void setProduits(List<Produit> produits) {
	this.produits = produits;
}
public double getTotalPages() {
	return totalPages;
}
public void setTotalPages(double totalPages) {
	this.totalPages = totalPages;
}
public ProduitResponce(List<Produit> produits, double totalPages) {
	super();
	this.produits = produits;
	this.totalPages = totalPages;
}
public ProduitResponce() {
	super();
	// TODO Auto-generated constructor stub
}


}
