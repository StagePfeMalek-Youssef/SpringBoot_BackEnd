package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Reunion;


public interface ReunionRepo extends JpaRepository<Reunion,Long> {
	  Page<Reunion> findAllByOrderByUpdatedAtDesc(Pageable pageable);
	  List<Reunion> findByActiveTrue();
	  Reunion findBySujet(String sujet);
}
