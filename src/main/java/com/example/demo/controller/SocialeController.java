package com.example.demo.controller;


import org.apache.http.protocol.HTTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.message.request.LoginForm;
import com.example.demo.message.request.TokenSociale;
import com.example.demo.message.responce.LoginResponce;
import com.example.demo.security.service.TokenService;
import com.example.demo.security.service.UserDetailsServiceImpl;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Value;

import java.util.Collections;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/social/")
public class SocialeController {
	@Autowired
	TokenService tokenService;
	
	@Autowired
	UserDetailsServiceImpl userservice;
	
	@Autowired
	PasswordEncoder encoder;
	public static final String DEFAULT_ROLE = "ROLE_USER";
	
	@Value("${google.id}")
	private String idClient;
	
	@PostMapping("/google")
	public LoginResponce loginWithGoogle(@RequestBody TokenSociale tokenSociale) throws Exception{
		NetHttpTransport transport =new NetHttpTransport();
		JacksonFactory factory =JacksonFactory.getDefaultInstance();
	      GoogleIdTokenVerifier.Builder ver =
	                new GoogleIdTokenVerifier.Builder(transport,factory)
	                        .setAudience(Collections.singleton(idClient));
	      GoogleIdToken googleIdToken = GoogleIdToken.parse(ver.getJsonFactory(),tokenSociale.getToken());
	      GoogleIdToken.Payload payload = googleIdToken.getPayload();
		return login(payload.getEmail());
		
	}
	
	@PostMapping("/facebook")
    public LoginResponce loginWithFacebook(@RequestBody  TokenSociale tokenSociale) throws Exception {
		 Facebook facebook = new FacebookTemplate(tokenSociale.getToken());
		 String [] data = {"email","name","picture"};
		 org.springframework.social.facebook.api.User userFacebook = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class,data);
	     System.out.print("=========================>"+userFacebook.getEmail()+userFacebook.getName()+userFacebook.getFirstName()+userFacebook.getLastName());
			return login(userFacebook.getEmail());

	}
	
	
	private LoginResponce login(String email) {
		 boolean result = userservice.ifEmailExist(email);
	      LoginResponce loginResponce=new LoginResponce();
	      if (!result) {
			  com.example.demo.model.User user= new com.example.demo.model.User();
			  user.setEmail(email);
			  user.setPassword(encoder.encode("kasdjhfkadhsY776ggTyUU65khaskdjfhYuHAwjnlji"));
			 
			  user.setActive(1);
			  user.setRoles("ROLE_USER");
             userservice.editUser(user);
            
		  }
	      LoginForm loginRequest=new LoginForm();
         loginRequest.setEmail(email);
         loginRequest.setPassword("kasdjhfkadhsY776ggTyUU65khaskdjfhYuHAwjnlji");
         return loginResponce = tokenService.authenticateUser(loginRequest);
	}
	

}
