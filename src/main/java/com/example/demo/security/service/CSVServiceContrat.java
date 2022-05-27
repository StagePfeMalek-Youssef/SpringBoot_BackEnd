package com.example.demo.security.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.CSVHelper.CSVHelperContrat;
import com.example.demo.model.Contrat;
import com.example.demo.repository.ContratRepo;
@Service
public class CSVServiceContrat {
@Autowired 
ContratRepo contratRepo;
public void save(MultipartFile file) {
    try {
      List<Contrat> contrats = CSVHelperContrat.csvToTutorials(file.getInputStream());
      contratRepo.saveAll(contrats);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }
  public List<Contrat> getAllProduits() {
    return contratRepo.findAll();
  }
}
