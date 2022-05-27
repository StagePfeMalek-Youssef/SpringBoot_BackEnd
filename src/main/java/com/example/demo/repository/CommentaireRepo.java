package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Commentaire;
@Repository
public interface CommentaireRepo extends JpaRepository<Commentaire,Long>{
  Page<Commentaire> findAllByOrderByUpdatedAtDesc(Pageable pageable);
  List<Commentaire>	findByTitresujet(String titresujet);
  List<Commentaire> findByUsernameAndActiveTrueAndTitresujet(String username,String titresujet);
  
}
