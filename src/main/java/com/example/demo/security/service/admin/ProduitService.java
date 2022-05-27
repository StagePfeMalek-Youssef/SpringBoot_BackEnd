package com.example.demo.security.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Produit;
import com.example.demo.repository.ProduitRepo;
@Service
public class ProduitService {
	  @Autowired ProduitRepo repositoryP;
		
		public Produit saveProduit(Produit produit) {
			return repositoryP.save(produit);
		}
		
		public List<Produit> getProduitInfos(){
			return repositoryP.findAll();
		}
		
		public Optional<Produit> getProduitById(long idPrd) {
			return repositoryP.findById(idPrd);
		}
		
		public boolean checkExistedProduit(long idPrd) {
			if(repositoryP.existsById((long) idPrd)) {
				return true;
			}
			return false;
		}
		
		public Produit updateProduit(Produit produit) {
			return repositoryP.save(produit);		
		}
		
		public void deleteProduitById(long idPrd) {
			repositoryP.deleteById(idPrd);
		}
}
