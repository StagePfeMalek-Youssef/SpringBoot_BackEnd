package com.example.demo.message.request;

public class NewPassword {
  private String email;
  private String code;
  private String password;
  private String confirmepassword;
 
  

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
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
public NewPassword(String email, String code, String password, String confirmepassword) {
	super();
	this.email = email;
	this.code = code;
	this.password = password;
	this.confirmepassword = confirmepassword;
}
public NewPassword() {
	super();
	// TODO Auto-generated constructor stub
}
  

}
