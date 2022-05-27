package com.example.demo.message.responce;

import java.util.List;
import com.example.demo.model.User;

public class UserResponce {
	private List<User> Users;
	private double totalPages;
	public List<User> getUsers() {
		return Users;
	}
	public void setUsers(List<User> users) {
		Users = users;
	}
	public double getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}
	public UserResponce(List<User> users, double totalPages) {
		super();
		Users = users;
		this.totalPages = totalPages;
	}
	public UserResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
