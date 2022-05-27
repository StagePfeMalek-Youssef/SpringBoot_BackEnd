package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Produit;

@Repository
public interface ProduitRepo extends JpaRepository<Produit, Long>{
   Page<Produit> findAllByOrderByUpdatedAtDesc(Pageable pageable);
   List<Produit> findByUsername(String username);
   Page<Produit> findByUsername(String username, Pageable pageable);
   long countByUsername(String username);
}
