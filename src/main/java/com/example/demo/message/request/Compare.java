package com.example.demo.message.request;

public class Compare {
private String password;
private String confirmepassword;
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getConfirmepassword() {
	return confirmepassword;
}
public void setConfirmepassword(String confirmepassword) {
	this.confirmepassword = confirmepassword;
}
public Compare(String password, String confirmepassword) {
	super();
	this.password = password;
	this.confirmepassword = confirmepassword;
}
public Compare() {
	super();
	// TODO Auto-generated constructor stub
}

}
