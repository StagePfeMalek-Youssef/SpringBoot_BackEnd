package com.example.demo.CSVHelper;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.User;


public class CSVHelperUser {
	 public static String TYPE = "text/csv";
	  static String[] HEADERs ={"prenom","nom","username","datenaissance","codepostal","telephone","ville","cin","email","password","active","roles"};
	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }
	  public static List<User> csvToTutorials(InputStream is) {
		    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
		      List<User> users = new ArrayList<User>();
		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
		      
		      for (CSVRecord csvRecord : csvRecords) {
		    	  User user = new User(
		    			  csvRecord.get("prenom"),
		    			  csvRecord.get("nom"),
		    			  csvRecord.get("username"),
		    			  csvRecord.get("datenaissance"),
		    			  csvRecord.get("codepostal"), 
		    			  csvRecord.get("telephone"), csvRecord.get("ville"), 
		    			  csvRecord.get("cin"),
		    			  csvRecord.get("email"),
		    			  csvRecord.get("password") ,  Integer.parseInt(csvRecord.get("active")),  csvRecord.get("roles")
		              );
		    	  user.setDateajout(new Date());
		    	  users.add(user);
		    	  }
		      return users;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
		  }
}
