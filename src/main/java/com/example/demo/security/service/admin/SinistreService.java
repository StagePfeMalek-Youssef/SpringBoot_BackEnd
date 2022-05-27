package com.example.demo.security.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sinistre;
import com.example.demo.repository.SinistreRepo;

@Service
public class SinistreService {
	 @Autowired SinistreRepo repositoryS;
		
		public Sinistre saveSinistre(Sinistre sinistre) {
			return repositoryS.save(sinistre);
		}
		
		public List<Sinistre> getSinistreInfos(){
			return repositoryS.findAll();
		}
		
		public Optional<Sinistre> getSinistreById(long idS) {
			return repositoryS.findById(idS);
		}
		
		public boolean checkExistedSinistre(long idS) {
			if(repositoryS.existsById((long) idS)) {
				return true;
			}
			return false;
		}
		
		public Sinistre updateSinistre(Sinistre sinistre) {
			return repositoryS.save(sinistre);		
		}
		
		public void deleteSinistreById(long idS) {
			repositoryS.deleteById(idS);
		}
}
