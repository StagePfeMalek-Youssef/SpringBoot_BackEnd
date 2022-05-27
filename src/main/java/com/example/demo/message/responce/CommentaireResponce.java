package com.example.demo.message.responce;
import java.util.List;
import com.example.demo.model.Commentaire;
public class CommentaireResponce {
	private List<Commentaire> commentaires;
	private double totalPages;
	public List<Commentaire> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	public double getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}
	public CommentaireResponce(List<Commentaire> commentaires, double totalPages) {
		super();
		this.commentaires = commentaires;
		this.totalPages = totalPages;
	}
	public CommentaireResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
