package com.example.demo.model;

import java.io.FileOutputStream;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="Sinistres")
public class Sinistre {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name="Identifier_Sin", nullable = false) private long idS;
    @Column(name = "Date_Survenance") private String dateSurvenance;
    @Column(nullable=false, name = "Etat")private String etat;
    @Column(nullable=false, name = "username")private String username;
    @Column(nullable=false, name = "numcontrat")private int numPolicecontrat;
	@Column(nullable=false, name = "Lieu_Survenance")private String lieu;
	@Lob@Column(columnDefinition = "MEDIUMBLOB")private String image;
    @Column(name="Type_Contrat")private String type;
    @Column(name = "Date_Declaration") @Temporal(TemporalType.TIMESTAMP) private Date dateDeclaration = new Date();
    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private Date updatedAt = new Date();
	@ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "id_Contrat")@JsonIgnore private Contrat contrat;
	@ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "id_User")@JsonIgnore private User userSinistre;


	

	

	public Sinistre(String dateSurvenance, String etat, String username, int numPolicecontrat,
			String lieu, String image, String type, Date dateDeclaration, Date updatedAt, Contrat contrat,
			User userSinistre) {
		super();
		this.dateSurvenance = dateSurvenance;
		this.etat = etat;
		this.username = username;
		this.numPolicecontrat = numPolicecontrat;
		this.lieu = lieu;
		this.image = image;
		this.type = type;
		this.dateDeclaration = dateDeclaration;
		this.updatedAt = updatedAt;
		this.contrat = contrat;
		this.userSinistre = userSinistre;
	}

	public Sinistre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdS() {
		return idS;
	}

	public void setIdS(long idS) {
		this.idS = idS;
	}

	public String getDateSurvenance() {
		return dateSurvenance;
	}

	public void setDateSurvenance(String  dateSurvenance) {
		this.dateSurvenance = dateSurvenance;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Date getDateDeclaration() {
		return dateDeclaration;
	}

	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getNumPolicecontrat() {
		return numPolicecontrat;
	}

	public void setNumPolicecontrat(int numPolicecontrat) {
		this.numPolicecontrat = numPolicecontrat;
	}

	public User getUserSinistre() {
		return userSinistre;
	}

	public void setUserSinistre(User userSinistre) {
		this.userSinistre = userSinistre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	



}
