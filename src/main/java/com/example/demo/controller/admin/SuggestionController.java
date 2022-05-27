package com.example.demo.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
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
import com.example.demo.message.responce.AccountResponce;
import com.example.demo.message.responce.SuggestionResponce;
import com.example.demo.model.Suggestion;
import com.example.demo.model.User;
import com.example.demo.repository.SuggestionRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.service.EmailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Api/Suggestion")
public class SuggestionController {
	@Autowired
private UserRepository userRepository;
	@Autowired
private SuggestionRepo suggestionRepo;
	@Autowired
	EmailService emailService;
	
	 @GetMapping(path = "")
public SuggestionResponce index(@RequestParam int page,@RequestParam int size) { 
		 SuggestionResponce suggestionResponce=new SuggestionResponce();
		 long Pages=suggestionRepo.count();
	     double totalPages= Math.ceil(Pages/5);
	     suggestionResponce.setTotalPages(totalPages);
	     Pageable pageable=PageRequest.of(page,size);	 
		 List<Suggestion> suggestions=suggestionRepo.findAllByOrderByUpdatedAtDesc(pageable).getContent();
		 suggestionResponce.setSuggestions(suggestions);
		 return suggestionResponce;
	 }
	 
	 
	 @GetMapping(path = "username/{username}")
public SuggestionResponce indexByUsername(@PathVariable(value = "username")String username,@RequestParam int page,@RequestParam int size) { 
		 SuggestionResponce suggestionResponce=new SuggestionResponce();
		 long Pages=suggestionRepo.countByUsername(username);
	     double totalPages= Math.ceil(Pages/5);
	     suggestionResponce.setTotalPages(totalPages);
	     Pageable pageable=PageRequest.of(page,size);	 
		 List<Suggestion> suggestions=suggestionRepo.findByUsernameAndActiveTrue(username,pageable).getContent();
		 suggestionResponce.setSuggestions(suggestions);
		 return suggestionResponce;		 
	 }
	 
	 @PutMapping("/activeSugetion/{id}")
	 public ResponseEntity<Void> activeSuggestion(@PathVariable(value = "id")Long id) {
	     Optional<Suggestion> suggestion = suggestionRepo.findById(id);
	     if (suggestion.isPresent()) {
	    	 suggestion.get().setActive(true);
	         suggestionRepo.save(suggestion.get());
	         return ResponseEntity.status(HttpStatus.OK).build();
	     }
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	 }
	 @GetMapping(path = "/{id}")
	 public ResponseEntity<Suggestion> show(@PathVariable(value = "id")Long id) {
	     Optional<Suggestion> suggestion = suggestionRepo.findById(id);
	     return suggestion
	             .map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
	             .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	 }
	 
	 @PostMapping("/{username}")
	 public Suggestion create(@PathVariable(value = "username") String username,@RequestBody Suggestion suggestion) {
		
			User user=userRepository.findByUsername(username);
			suggestion.setUsername(username);
			suggestion.setUserSuggestion(user);
			suggestion.setDateDeclaration(new Date());
			suggestion.setUpdatedAt(null);
			suggestion.setActive(true);
			 ArrayList<String> mauvaumot = new ArrayList<String>();
			 mauvaumot.add("voleur");
			    mauvaumot.add("panne");
			    for (int i = 0; i < mauvaumot.size(); i++) {
			    	if ((suggestion.getMessage()).contains(mauvaumot.get(i))) {
			    		Mail mail=new Mail("youssefchouchene09@gmail.com",suggestion.getMessage());
			    		suggestion.setMessage(suggestion.getMessage());
			    		emailService.sendMailSuggestion(mail);
			    		suggestion.setActive(false);
			    		
					}else {
						
					}
			    }
			    suggestionRepo.save(suggestion);
			
		
	    
	     return suggestion;
	 }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Void> update(@PathVariable(value = "id")Long id,@RequestBody Suggestion suggestionrequest) {
	     Optional<Suggestion> suggestion = suggestionRepo.findById(id);
	     if (suggestion.isPresent()) {
	    	 suggestion.get().setMessage(suggestionrequest.getMessage());
	         suggestion.get().setObjet(suggestionrequest.getObjet());
	         suggestion.get().setUpdatedAt(new Date());
	         suggestionRepo.save(suggestion.get());
	         return ResponseEntity.status(HttpStatus.OK).build();
	     }
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	 }
	 
	 
	 @DeleteMapping("{id}")
	 public void destroy(@PathVariable(value = "id") Long id) {
	     Optional<Suggestion> suggestion = suggestionRepo.findById(id);
	     if (suggestion.isPresent()) {
	    	 suggestionRepo.deleteById(id);	        
	     }	 
	 }
	 
	 
	 @PostMapping("/test")
	 public AccountResponce destroyy(@RequestParam String message) {
		 ArrayList<String> mauvaumot = new ArrayList<String>();
		 mauvaumot.add("voleur");
		    mauvaumot.add("panne");
		    AccountResponce accountResponce=new AccountResponce();
		    for (int i = 0; i < mauvaumot.size(); i++) {
		    	if (message.contains(mauvaumot.get(i))) {
		    		Mail mail=new Mail("youssefchouchene09@gmail.com",message);
		    		emailService.sendMailSuggestion(mail);
		    		accountResponce.setResult(1);	
				}else {
					accountResponce.setResult(0);
				}
		      }
			return accountResponce;
	    }
	 }
