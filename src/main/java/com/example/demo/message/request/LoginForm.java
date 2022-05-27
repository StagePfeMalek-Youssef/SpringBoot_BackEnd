package com.example.demo.message.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {

    @Size(min=3, max = 60)
    private String email;


    @Size(min = 6)
    private String password;
    
    public LoginForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginForm(@NotBlank @Size(min = 3, max = 60) String email,
			@NotBlank @Size(min = 6, max = 40) String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
    
    
}