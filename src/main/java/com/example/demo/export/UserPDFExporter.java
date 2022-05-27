package com.example.demo.export;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.IOException;

import com.example.demo.model.User;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class UserPDFExporter {
	private List<User> listUsers;
    public UserPDFExporter(List<User> listUsers) {
        this.listUsers = listUsers;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("id", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("prenom", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("nom", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("username", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("datenaissance", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("codepostal", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("telephone", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("ville", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("cin", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("password", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("roles", font));       
        table.addCell(cell);
      
    }
     
    private void writeTableData(PdfPTable table) {
        for (User user : listUsers) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getPrenom());
            table.addCell(user.getNom());
            table.addCell(user.getUsername());
            table.addCell(String.valueOf(user.getDatenaissance()));
            table.addCell(String.valueOf(user.getCodepostal()));
            table.addCell(user.getTelephone());  
            table.addCell(user.getVille());
            table.addCell(user.getCin());
            table.addCell(user.getEmail());
            table.addCell(user.getPassword());       
            table.addCell(user.getRoles());
        
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
