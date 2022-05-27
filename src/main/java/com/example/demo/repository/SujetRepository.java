package com.example.demo.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Sujet;
@Repository
public interface SujetRepository extends JpaRepository<Sujet,Long>{
	Optional<Sujet> findById(Long id);
	Page<Sujet> findAllByOrderByUpdatedAtDesc(Pageable pageable);
	Sujet findByTitreSujet(String titreSujet);
	List<Sujet> findByUsernameAndActiveTrue(String username);
	Page<Sujet> findByUsernameAndActiveTrue(String username, Pageable pageable);
	long countByUsername(String username);

}
