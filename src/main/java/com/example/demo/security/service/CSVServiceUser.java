package com.example.demo.security.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.CSVHelper.CSVHelperUser;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
@Service
public class CSVServiceUser {
	@Autowired
	  UserRepository userRepo;
	  public void save(MultipartFile file) {
		    try {
			      List<User> users = CSVHelperUser.csvToTutorials(file.getInputStream());
			      userRepo.saveAll(users);
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		    }
		  }
	  public List<User> getAllUsers() {
		    return userRepo.findAll();
		  }
		 
}
