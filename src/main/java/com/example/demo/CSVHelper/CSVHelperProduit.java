package com.example.demo.CSVHelper;


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Produit;
import com.example.demo.model.User;
import com.example.demo.repository.ProduitRepo;
import com.example.demo.repository.UserRepository;

import net.bytebuddy.utility.dispatcher.JavaDispatcher.IsStatic;


public class CSVHelperProduit {
	private static UserRepository userRepository;
	
	  public static String TYPE = "text/csv";
	  static String[] HEADERs = { "NomProduit", "categorie", "titre","DescriptionCourte","DescriptionLong" };
	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }
	  public static List<Produit> csvToTutorials(InputStream is) {
		    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
		      List<Produit> produits = new ArrayList<Produit>();
		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
		      
		      for (CSVRecord csvRecord : csvRecords) {
		    	  Produit produit = new Produit(
		    		  csvRecord.get("NomProduit"),
		    		  csvRecord.get("categorie"),
		    		  csvRecord.get("titre"),
		    		  csvRecord.get("DescriptionCourte"),
		              csvRecord.get("DescriptionLong"),"admin"
		              );
		    	  produit.setUpdatedAt(new Date());
		            produits.add(produit);
		      }
		      return produits;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
		  }
}
