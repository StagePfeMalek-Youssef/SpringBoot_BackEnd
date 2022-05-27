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
import com.example.demo.message.request.Mail;
import com.example.demo.message.responce.ReunionResponce;
import com.example.demo.model.Reunion;
import com.example.demo.model.User;
import com.example.demo.repository.ReunionRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.service.EmailService;

@RestController
@RequestMapping("/api/v1/Reunions")
@CrossOrigin("*")
public class ReunionController {
	@Autowired
private UserRepository userRepository;
	@Autowired
private ReunionRepo reunionRepo;
	
	@Autowired
	EmailService emailService;
	 @GetMapping(path = "")
	 public ReunionResponce index(@RequestParam int page,@RequestParam int size) {
		 ReunionResponce reunionResponce=new ReunionResponce();
		 long Pages=reunionRepo.count();
	     double totalPages= Math.ceil(Pages/5);
	     reunionResponce.setTotalPages(totalPages);
	     Pageable pageable=PageRequest.of(page,size);	
	     List<Reunion> reunions=reunionRepo.findAllByOrderByUpdatedAtDesc(pageable).getContent();
	     reunionResponce.setReunions(reunions);
		 return reunionResponce;}
	 
	 @GetMapping(path = "/active")
	 public List<Reunion> indexactive() {
		 return reunionRepo.findByActiveTrue(); }
	 
	 @GetMapping(path = "/{id}")
	 public ResponseEntity<Reunion> show(@PathVariable(value = "id")Long id) {
	     Optional<Reunion> reunion = reunionRepo.findById(id);
	     return reunion
	             .map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
	             .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	 }
	 
	 @GetMapping("/activeReunion/{sujet}")
	 public Reunion activeSujet(@PathVariable(value = "sujet")String sujet) {
	     Reunion reunion =reunionRepo.findBySujet(sujet);
	     reunion.setActive(false);
		 List<User> user = userRepository.findAll();
		 for (int i = 0; i < user.size(); i++) {
				 Mail mail=new Mail(user.get(i).getEmail(),"le reunion sera annuler Jusqu'à ce que quelque chose contredise ça");
		    		emailService.sendMailUsers(mail);
				 
			 }
	    	 reunionRepo.save(reunion);
         return reunion;
	 }
	 
	 
	 @PostMapping("/create")
	 public Reunion create(@RequestBody Reunion reunion) {			
		 reunion.setCreatedAt(new Date());
		 reunion.setUpdatedAt(null);
		 reunion.setActive(true);
		 List<User> user = userRepository.findAll();
		 for (int i = 0; i < user.size(); i++) {
			 Mail mail=new Mail(user.get(i).getEmail(),"on a un reuion sur le sujet "+reunion.getSujet()+"qui sera le "+reunion.getDatereunion());
	    		emailService.sendMailUsers(mail);
			 
		 }
		 reunionRepo.save(reunion);
	    
	     return reunion;
	 }
	 
	 
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Void> update(@RequestBody Reunion  reunionRequert, @PathVariable(value = "id")Long id) {
	     Optional<Reunion> reunion = reunionRepo.findById(id);
	     if (reunion.isPresent()) {
	    	 reunion.get().setDatereunion(reunionRequert.getDatereunion());
	    	 reunion.get().setSujet(reunionRequert.getSujet());
	         reunion.get().setUpdatedAt(new Date());   
	         List<User> user = userRepository.findAll();
			 for (int i = 0; i < user.size(); i++) {
				 Mail mail=new Mail(user.get(i).getEmail(),"on a changer la date de reunion a:  "+reunion.get().getDatereunion());
		    		emailService.sendMailUsers(mail);				 
			 }
	         reunionRepo.save(reunion.get());
	         return ResponseEntity.status(HttpStatus.OK).build();
	     }
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	 }
	 
	 
	 @DeleteMapping("{id}")
	 public ResponseEntity<Void> destroy(@PathVariable(value = "id") Long id) {
	     Optional<Reunion> reunion = reunionRepo.findById(id);
	     if (reunion.isPresent()) {
	    	 reunionRepo.deleteById(id);
	         return ResponseEntity.status(HttpStatus.OK).build();
	     }
	     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	 }

	public void difference(Date creatat,Date datereunion) {	
		 Reunion reunionRequert=new Reunion();
		 long diff = creatat.getTime() - datereunion.getTime();
         float res = (diff / (1000*60*60*24));
         System.out.println("Nombre de jours entre les deux dates est: "+res);
         if (res==(float) 0.0) {
        	 reunionRequert.setActive(false);
		} else {
			reunionRequert.setActive(true);
		}
       
	 }
	 
	 
}
