package com.example.demo.CSVHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Contrat;
import com.example.demo.model.Produit;

public class CSVHelperContrat {
	  public static String TYPE = "text/csv";
	  static String[] HEADERs = { "numPolice", "dateEffet", "dateFinEffet", "type","etat"};
	  public static boolean hasCSVFormat(MultipartFile file) {
		    if (!TYPE.equals(file.getContentType())) {
		      return false;
		    }
		    return true;
		  }    
	  public static List<Contrat> csvToTutorials(InputStream is) {
		    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
		      List<Contrat> contrats = new ArrayList<Contrat>();
		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();   
		      for (CSVRecord csvRecord : csvRecords) {
		    	  Contrat contrat = new Contrat(
		    		 Integer.parseInt(csvRecord.get("numPolice")),
		    		 csvRecord.get("dateEffet"),
		    		 csvRecord.get("dateFinEffet"),	    		
		    		  csvRecord.get("type"),
		    		  csvRecord.get("etat")
		              );
		    	  contrats.add(contrat);
		      }
		      return contrats;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
		  }

	
}
