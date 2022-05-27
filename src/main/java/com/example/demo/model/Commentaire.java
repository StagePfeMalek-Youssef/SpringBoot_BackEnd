package com.example.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "Commentaire")
public class Commentaire {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)@Column(nullable = false, name = "Id_Commentaire")private Long id;
    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) private Date createdAt = new Date();
    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private Date updatedAt = new Date();
    private String message;
    private String titresujet;
    private String username;
    private Boolean active;
    
    @ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "sujet_ID")@JsonIgnore private Sujet sujetCommentaire;
    
    @ManyToMany(fetch = FetchType.LAZY,
  	      cascade = {
  	          CascadeType.PERSIST,
  	          CascadeType.MERGE
  	      })@JsonIgnore
  	  @JoinTable(name = "commentaire_user",
  	        joinColumns = { @JoinColumn(name = "commentaire_id") },
  	        inverseJoinColumns = { @JoinColumn(name = "id_user") })
  	  private Set<User> usersCommentaire=new HashSet<>();
	

    
	public Commentaire(Date createdAt, Date updatedAt, String message, String titresujet, String username,
			Boolean active, Sujet sujetCommentaire, Set<User> usersCommentaire) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.message = message;
		this.titresujet = titresujet;
		this.username = username;
		this.active = active;
		this.sujetCommentaire = sujetCommentaire;
		this.usersCommentaire = usersCommentaire;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Commentaire() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public Sujet getSujetCommentaire() {
		return sujetCommentaire;
	}
	public void setSujetCommentaire(Sujet sujetCommentaire) {
		this.sujetCommentaire = sujetCommentaire;
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
	
	public String getTitresujet() {
		return titresujet;
	}
	public void setTitresujet(String titresujet) {
		this.titresujet = titresujet;
	}
	public Set<User> getUsersCommentaire() {
		return usersCommentaire;
	}
	public void setUsersCommentaire(Set<User> usersCommentaire) {
		this.usersCommentaire = usersCommentaire;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	
    
    
    

}
