package com.example.demo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Suggestion")
public class Suggestion {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)@Column(nullable = false) private long id;
    @Column(name = "objet")  private String objet;
    @Column(name = "message")  private String message;
    @Column(name = "username")  private String username;
    private Boolean active;
    @Column(name = "Date_Declaration") @Temporal(TemporalType.TIMESTAMP) private Date dateDeclaration = new Date();
    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private Date updatedAt = new Date();
	@ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "id_user")@JsonIgnore private User userSuggestion;
		
	public Suggestion(String objet, String message, String username, Boolean  active, Date dateDeclaration, Date updatedAt,
			User userSuggestion) {
		super();
		this.objet = objet;
		this.message = message;
		this.username = username;
		this.active = active;
		this.dateDeclaration = dateDeclaration;
		this.updatedAt = updatedAt;
		this.userSuggestion = userSuggestion;
	}
	public Suggestion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDateDeclaration() {
		return dateDeclaration;
	}
	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getUserSuggestion() {
		return userSuggestion;
	}
	public void setUserSuggestion(User userSuggestion) {
		this.userSuggestion = userSuggestion;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean  getActive() {
		return active;
	}
	public void setActive(Boolean  active) {
		this.active = active;
	}

    

}
