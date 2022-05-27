package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Sujet")
public class Sujet {
	    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)@Column(nullable = false, name = "Id_Sujet")private Long idSu;
	    @Column(nullable = false, name = "Titre_De_Sujet")private String titreSujet;
	    private String message;
	    private String username;
	    private Boolean active;
		@Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) private Date createdAt = new Date();
	    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private Date updatedAt = new Date();
	    @ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "user_ID")@JsonIgnore private User sujetuser;
	    
	    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sujetCommentaire")
	    private List<Commentaire> commentaires;

		public Sujet(String titreSujet, String message, String username, Boolean active, Date createdAt, Date updatedAt,
				User sujetuser, List<Commentaire> commentaires) {
			super();
			this.titreSujet = titreSujet;
			this.message = message;
			this.username = username;
			this.active = active;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.sujetuser = sujetuser;
			this.commentaires = commentaires;
		}

		public Sujet() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Long getIdSu() {
			return idSu;
		}
		public void setIdSu(Long idSu) {
			this.idSu = idSu;
		}
		public String getTitreSujet() {
			return titreSujet;
		}
		public void setTitreSujet(String titreSujet) {
			this.titreSujet = titreSujet;
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
		
		public User getSujetuser() {
			return sujetuser;
		}

		public void setSujetuser(User sujetuser) {
			this.sujetuser = sujetuser;
		}

		public List<Commentaire> getCommentaires() {
			return commentaires;
		}

		public void setCommentaires(List<Commentaire> commentaires) {
			this.commentaires = commentaires;
		}

		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
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
