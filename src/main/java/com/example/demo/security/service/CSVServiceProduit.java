package com.example.demo.security.service;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.CSVHelper.CSVHelperProduit;
import com.example.demo.model.Produit;
import com.example.demo.repository.ProduitRepo;

@Service
public class CSVServiceProduit {
	
  @Autowired
  ProduitRepo produitRepo;
  public void save(MultipartFile file) {
	    try {
		      List<Produit> produits = CSVHelperProduit.csvToTutorials(file.getInputStream());
	      produitRepo.saveAll(produits);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }
	 
}