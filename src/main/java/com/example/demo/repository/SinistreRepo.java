package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Sinistre;

public interface SinistreRepo extends JpaRepository<Sinistre, Long>{
	Page<Sinistre> findAllByOrderByUpdatedAtDesc(Pageable pageable);
	List<Sinistre> findByUsername(String username);
	Page<Sinistre> findByUsername(String username, Pageable pageable);
	long countByUsername(String username);

}
