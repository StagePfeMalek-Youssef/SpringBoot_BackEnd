package com.example.demo.model;

import java.util.Date;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Contrat")
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idC;
    
    @Column(name = "Num_Police")
    private int numPolice;
    
    @Column(name = "Date_Effet")
    private String dateEffet;
    
    @Column(name = "Date_Fin_Effet")
    private String dateFinEffet;
    
    @Column(name = "Type_Contrat")
    private String type;
    
    @Column(name = "Etat")
    private String etat;
    
    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) private Date createdAt = new Date();
    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private Date updatedAt = new Date();
   

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrat")
    private List<Sinistre> sinistres;
 
    @ManyToMany(fetch = FetchType.LAZY,
    	      cascade = {
    	          CascadeType.PERSIST,
    	          CascadeType.MERGE
    	      })
    	  @JoinTable(name = "contrat_users",
    	        joinColumns = { @JoinColumn(name = "contrat_id") },
    	        inverseJoinColumns = { @JoinColumn(name = "user_id") })
    	  private Set<User> users=new HashSet<>();
 



  
 
























	public Contrat(int numPolice, String dateEffet, String dateFinEffet, String type, String etat) {
		super();
		this.numPolice = numPolice;
		this.dateEffet = dateEffet;
		this.dateFinEffet = dateFinEffet;
		this.type = type;
		this.etat = etat;

	}










	public Contrat() {
		super();
		// TODO Auto-generated constructor stub
	}









	public long getIdC() {
        return idC;
    }









    public void setIdC(long idC) {
        this.idC = idC;
    }









    public int getNumPolice() {
        return numPolice;
    }









    public void setNumPolice(int numPolice) {
        this.numPolice = numPolice;
    }








    public String getDateEffet() {
		return dateEffet;
	}










	public void setDateEffet(String dateEffet) {
		this.dateEffet = dateEffet;
	}










	public String getDateFinEffet() {
		return dateFinEffet;
	}










	public void setDateFinEffet(String dateFinEffet) {
		this.dateFinEffet = dateFinEffet;
	}










	public String getType() {
        return type;
    }









    public void setType(String type) {
        this.type = type;
    }









    public String getEtat() {
        return etat;
    }









    public void setEtat(String etat) {
        this.etat = etat;
    }









  


















    public Date getCreatedAt() {
		return createdAt;
	}









	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}









	public Date getUpdatedAt() {
		return updatedAt;
	}









	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}









	public List<Sinistre> getSinistres() {
		return sinistres;
	}



	public void setSinistres(List<Sinistre> sinistres) {
		this.sinistres = sinistres;
	}









	public Set<User> getUsers() {
		return users;
	}









	public void setUsers(Set<User> users) {
		this.users = users;
	}









	









	









	















	

    
    

}