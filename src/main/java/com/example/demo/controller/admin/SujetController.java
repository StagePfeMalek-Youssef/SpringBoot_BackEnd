package com.example.demo.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.message.request.Mail;
import com.example.demo.message.responce.SujetResponce;
import com.example.demo.model.Sujet;
import com.example.demo.model.User;
import com.example.demo.repository.SujetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.service.EmailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Api/Sujet")
public class SujetController {
	@Autowired
private UserRepository userRepository;
	@Autowired
private SujetRepository sujetRepo;
	@Autowired
	EmailService emailService;
	
	 @GetMapping
	 public SujetResponce index(@RequestParam int page,@RequestParam int size){ 
		 SujetResponce sujetResponce=new SujetResponce();
		 long Pages=sujetRepo.count();
	     double totalPages= Math.ceil(Pages/5);
	     sujetResponce.setTotalPages(totalPages);
	     Pageable pageable=PageRequest.of(page,size);
	     List<Sujet> sujets=sujetRepo.findAllByOrderByUpdatedAtDesc(pageable).getContent();
	     sujetResponce.setSujets(sujets);
		 return sujetResponce;}
	 
	
	 @GetMapping(path = "username/{username}")
	 public SujetResponce indexByUsername(@PathVariable(value = "username")String username,@RequestParam int page,@RequestParam int size) { 
		 SujetResponce sujetResponce=new SujetResponce();
		 long Pages=sujetRepo.countByUsername(username);
	     double totalPages= Math.ceil(Pages/5);
	     sujetResponce.setTotalPages(totalPages);
	     Pageable pageable=PageRequest.of(page,size);
	     List<Sujet> sujets=sujetRepo.findByUsernameAndActiveTrue(username,pageable).getContent();
	     sujetResponce.setSujets(sujets);
		 return sujetResponce;}
	 
	 
	 @PutMapping("/activeSujet/{id}")
	 public ResponseEntity<Sujet> activeSujet(@PathVariable(value = "id")Long id) {
	     Optional<Sujet> sujet = sujetRepo.findById(id);
	     if (sujet.isPresent()) {
	         sujet.get().setActive(true);
	    	 sujetRepo.save(sujet.get());
	         return ResponseEntity.status(HttpStatus.OK).build();
	     }
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	 }
	 
	 
	 @GetMapping(path = "/{id}")
	 public ResponseEntity<Sujet> show(@PathVariable(value = "id")Long id) {
	     Optional<Sujet> sujet = sujetRepo.findById(id);
	     return sujet
	             .map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
	             .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	 }
	
	 @PostMapping("/{username}")
	 public Sujet create(@PathVariable(value = "username") String username,@RequestBody Sujet sujet) {
		
			User user=userRepository.findByUsername(username);
			sujet.setSujetuser(user);
			sujet.setUsername(username);
			sujet.setCreatedAt(new Date());
			sujet.setUpdatedAt(null);
			sujet.setActive(true);
			 ArrayList<String> mauvaumot = new ArrayList<String>();
			 mauvaumot.add("voleur");
			    mauvaumot.add("panne");
			    for (int i = 0; i < mauvaumot.size(); i++) {
			    	if ((sujet.getMessage()).contains(mauvaumot.get(i))) {
			    		Mail mail=new Mail("youssefchouchene09@gmail.com",sujet.getMessage());
			    		sujet.setMessage(sujet.getMessage());
			    		emailService.sendMailSujet(mail);
			    		sujet.setActive(false);
					}else {
						
					}
			    }
			
			sujetRepo.save(sujet);
	    
	     return sujet;
	 }

	   
	 @PutMapping("/{id}")
	 public ResponseEntity<Sujet> update(@RequestBody Sujet  sujetRequert, @PathVariable(value = "id")Long id) {
	     Optional<Sujet> sujet = sujetRepo.findById(id);
	     if (sujet.isPresent()) {
	    	 sujet.get().setTitreSujet(sujetRequert.getTitreSujet());
	    	 sujet.get().setMessage(sujetRequert.getMessage());
	    	 sujet.get().setUpdatedAt(new Date());
	    	 sujetRepo.save(sujet.get());
	         return ResponseEntity.status(HttpStatus.OK).build();
	     }
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	 }
	 
	 
	 @DeleteMapping("{id}")
	 public ResponseEntity<Void> destroy(@PathVariable(value = "id") Long id) {
	     Optional<Sujet> sujet = sujetRepo.findById(id);
	     if (sujet.isPresent()) {
	    	 sujetRepo.deleteById(id);
	         return ResponseEntity.status(HttpStatus.OK).build();
	     }
	     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	 }
	 
	 
}
