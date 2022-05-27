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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table
public class Reclamation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_R;


    private String username;
	private String message;
    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) private Date createdAt = new Date();
    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private Date updatedAt = new Date();
	@ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "id_User")@JsonIgnore private User userReclamation;   
	private String objet;
	
	

	
	

	public Reclamation(String username, String message, Date createdAt, Date updatedAt,
			User userReclamation, String objet) {
		super();
	
		this.username = username;
		this.message = message;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userReclamation = userReclamation;
		this.objet = objet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Reclamation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId_R() {
		return id_R;
	}
	public void setId_R(Long id_R) {
		this.id_R = id_R;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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

	public User getUserReclamation() {
		return userReclamation;
	}

	public void setUserReclamation(User userReclamation) {
		this.userReclamation = userReclamation;
	}

	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}



	
	
}
