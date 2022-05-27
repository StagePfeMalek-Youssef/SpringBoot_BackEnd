package com.example.demo.repository;


import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contrat;

@Repository
public interface ContratRepo extends JpaRepository<Contrat, Long>{
    <Optional> Contrat findById(long id);
	 List<Contrat> findAllByOrderByUpdatedAtDesc();
	 Contrat findByType(String TYpe);
	 Contrat findByNumPolice(int numPolice);
	 long count();
	 
}
