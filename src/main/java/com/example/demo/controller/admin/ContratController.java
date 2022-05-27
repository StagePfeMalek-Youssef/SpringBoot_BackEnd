package com.example.demo.controller.admin;

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
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.CSVHelper.CSVHelperContrat;
import com.example.demo.message.responce.AccountResponce;
import com.example.demo.message.responce.ContratResponce;
import com.example.demo.model.Contrat;
import com.example.demo.repository.ContratRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.service.CSVServiceContrat;


@RestController
@RequestMapping("/api/v1/contrats")
@CrossOrigin("*")
public class ContratController {
	@Autowired
private UserRepository userRepository;
	@Autowired 
private ContratRepo contratRepo;


@Autowired
CSVServiceContrat fileService;

@GetMapping(path = "/all")
public List<Contrat> index() { 
	 return contratRepo.findAll(); }
	
	
	 @GetMapping(path = "")
public ContratResponce index(@RequestParam int page,@RequestParam int size) { 
		 ContratResponce contratResponce=new ContratResponce();
		 long Pages=contratRepo.count();
	     double totalPages= Math.ceil(Pages/5);
	     contratResponce.setTotalPages(totalPages);
	     Pageable pageable=PageRequest.of(page,size);
		 
		 List<Contrat> contrats= contratRepo.findAll(pageable).getContent();
		 contratResponce.setContrats(contrats);
		 return contratResponce;
	 }
	 
	 
	 @GetMapping(path ="/nbContrat")
public AccountResponce nbContrat() { 
		 AccountResponce accountResponce =new AccountResponce();
          long contrat =contratRepo.count(); 	
          int intcontrat=(int)contrat;
          accountResponce.setResult(intcontrat);
  		 return accountResponce;
				 }
	 
	 
	 
	 @GetMapping(path = "/{id}")
public ResponseEntity<Contrat> show(@PathVariable(value = "id")Long id) {
    Optional<Contrat> article = contratRepo.findById(id);
    return article
            .map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}
	 @PostMapping("/{userId}")
	 public Contrat create(@PathVariable(value = "userId")long userId,@RequestBody Contrat contrat) {
		
			userRepository.findById(userId).map(user -> {
	            contrat.getUsers().add(user);
	            return contratRepo.save(contrat);
	        });
			contrat.setCreatedAt(new Date());
			contrat.setUpdatedAt(null);
			contratRepo.save(contrat);
	    
	     return contrat;
	 }



@PutMapping("/{id}")
public ResponseEntity<Void> update(@RequestBody Contrat contratRequert, @PathVariable(value = "id")Long id) {
    Optional<Contrat> contrat = contratRepo.findById(id);
    if (contrat.isPresent()) {
    	contrat.get().setNumPolice(contratRequert.getNumPolice());
    	contrat.get().setDateEffet(contratRequert.getDateEffet());
    	contrat.get().setDateFinEffet(contratRequert.getDateFinEffet());
    	contrat.get().setType(contratRequert.getType());
    	contrat.get().setEtat(contratRequert.getEtat());
    	contrat.get().setUpdatedAt(new Date());
    	contratRepo.save(contrat.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}



@DeleteMapping("{id}")
public ResponseEntity<Void> destroy(@PathVariable(value = "id")Long id) {
    Optional<Contrat> contrat = contratRepo.findById(id);
    if (contrat.isPresent()) {
    	contratRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
@PostMapping("/upload")
public AccountResponce uploadFile(@RequestParam("file") MultipartFile file) {
	AccountResponce accountResponce=new AccountResponce();

  if (CSVHelperContrat.hasCSVFormat(file)) {
    try {
      fileService.save(file);
      accountResponce.setResult(1);
      return accountResponce;
    } catch (Exception e) {
    	 accountResponce.setResult(0);
         return accountResponce;
    }
  }
  accountResponce.setResult(0);
  return accountResponce;
}

}
