package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reunion {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)@Column(nullable = false, name = "Id_Reunion")private Long id;
    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) private Date createdAt = new Date();
    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private Date updatedAt = new Date();
    @Column(nullable = false, name = "sujet")private String sujet;
    @Column(name = "datereunion") private Date datereunion;
    private Boolean active;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reunion")
    private List<User> users;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getDatereunion() {
		return datereunion;
	}
	public void setDatereunion(Date datereunion) {
		this.datereunion = datereunion;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public Reunion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reunion(Date createdAt, Date updatedAt, String sujet, Date datereunion, Boolean active, List<User> users) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.sujet = sujet;
		this.datereunion = datereunion;
		this.active = active;
		this.users = users;
	}

    
    
}
