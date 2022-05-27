package com.example.demo.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Suggestion;

public interface SuggestionRepo extends JpaRepository<Suggestion,Long> {
     Page<Suggestion> findAllByOrderByUpdatedAtDesc(Pageable pageable);
	List<Suggestion> findByUsernameAndActiveTrue(String username);
	void deleteByMessage(String message);
	Page<Suggestion> findByUsernameAndActiveTrue(String username, Pageable pageable);
	long countByUsername(String username);
	
}
