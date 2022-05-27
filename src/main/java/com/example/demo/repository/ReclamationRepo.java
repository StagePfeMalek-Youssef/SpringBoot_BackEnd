package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Reclamation;


public interface ReclamationRepo  extends JpaRepository<Reclamation, Long> {
List<Reclamation> findAllByOrderByUpdatedAtDesc();
Page<Reclamation> findAllByOrderByUpdatedAtDesc(Pageable pageable);
Page<Reclamation> findByUsername(String username, Pageable pageable);
long countByUsername(String username);
}
