package com.example.demo.controller.admin;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ResourceNotFoundException;
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
import com.example.demo.message.responce.ProduitResponce;
import com.example.demo.model.Produit;
import com.example.demo.repository.ProduitRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.service.CSVServiceProduit;

@RestController
@RequestMapping("/api/v1/produits")
@CrossOrigin("*")
public class ProduitController {
	@Autowired
private UserRepository userRepository;

    @Autowired
    private ProduitRepo produitR;

    @Autowired
    CSVServiceProduit fileService;
    
	 @GetMapping(path = "/all")
public List<Produit> index() { return produitR.findAll(); }
    
	 @GetMapping(path = "")
public ProduitResponce index(@RequestParam int page,@RequestParam int size) {
		 ProduitResponce produitResponce=new ProduitResponce();
		 long Pages=produitR.count();
	     double totalPages= Math.ceil(Pages/5);
	     produitResponce.setTotalPages(totalPages);
		 Pageable pageable=PageRequest.of(page,size);
		List<Produit> produits=produitR.findAllByOrderByUpdatedAtDesc(pageable).getContent();
		produitResponce.setProduits(produits);
		 return produitResponce;}
	 
	 @GetMapping(path = "username/{username}")
	 public ProduitResponce indexByUsername(@PathVariable(value = "username")String username,@RequestParam int page,@RequestParam int size) { 
		 ProduitResponce produitResponce=new ProduitResponce();
		 long Pages=produitR.countByUsername(username);
	     double totalPages= Math.ceil(Pages/5);
	     produitResponce.setTotalPages(totalPages);
		 Pageable pageable=PageRequest.of(page,size);
		List<Produit> produits=produitR.findByUsername(username,pageable).getContent();
		produitResponce.setProduits(produits);
		 return produitResponce; }
	     
    
	@GetMapping("{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable  long id){
        Produit produit = produitR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not exist with id:" + id, "Produit not exist with id:" + id));
        return ResponseEntity.ok(produit);
    }
	@PostMapping("/{userId}")
	 public Produit create(@PathVariable(value = "userId")Long userId,@RequestBody Produit produit) {
		
			userRepository.findById(userId).map(user -> {
				produit.getUsers().add(user);
	            return produitR.save(produit);
	        });
	
			produit.setCreatedAt(new Date());
			produit.setUpdatedAt(null);
			produitR.save(produit);
	    
	     return produit;
	 }
    
    @PutMapping("{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable long id,@RequestBody Produit produitDetails) {
        Produit updateProduit = produitR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not exist with id: " + id, "Produit not exist with id: " + id));


        updateProduit.setNomPrd(produitDetails.getNomPrd());
        updateProduit.setCategorie(produitDetails.getCategorie());
        updateProduit.setTitre(produitDetails.getTitre());
        updateProduit.setDescCourte(produitDetails.getDescCourte());
		updateProduit.setDescLong(produitDetails.getDescLong());
		updateProduit.setUpdatedAt(new Date());
		
		

        produitR.save(updateProduit);

        return ResponseEntity.ok(updateProduit);
    }

    
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduit(@PathVariable long id){
        Produit produit = produitR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not exist with id: " + id, "Produit not exist with id: " + id));
        produitR.delete(produit);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
}}
