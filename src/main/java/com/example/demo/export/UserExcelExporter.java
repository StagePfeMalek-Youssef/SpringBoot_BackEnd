package com.example.demo.export;
import java.io.IOException;

import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.model.User;
 
public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<User> listUsers;
     
    public UserExcelExporter(List<User> listUsers) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Users");
    }
 
 
    private void writeHeaderLine() {
        
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "User ID", style);
        createCell(row, 1, "Prenom", style);     
        createCell(row, 2, "Nom", style);     
        createCell(row, 3, "Username", style); 
        createCell(row, 4, "Datenaissance", style); 
        createCell(row, 5, "Codepostal", style); 
        createCell(row, 6, "Telephone", style);       
        createCell(row, 7, "Ville", style); 
        createCell(row, 8, "Cin", style);
        createCell(row, 9, "email", style);
        createCell(row, 10, "Roles", style);
        
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
        for (User user : listUsers) {
            Row row = sheet.createRow(rowCount++);
       
           Cell cell=row.createCell(0);
           cell.setCellValue(user.getId());
           
           cell=row.createCell(1);
           cell.setCellValue(user.getPrenom());
           
           cell=row.createCell(2);
           cell.setCellValue(user.getNom());
           
           cell=row.createCell(3);
           cell.setCellValue(user.getUsername());
           
           cell=row.createCell(4);
           cell.setCellValue(user.getDatenaissance());
           
           cell=row.createCell(5);
           cell.setCellValue(user.getCodepostal());
           
           cell=row.createCell(6);
           cell.setCellValue(user.getTelephone());
           
           cell=row.createCell(7);
           cell.setCellValue(user.getVille());
           
           cell=row.createCell(8);
           cell.setCellValue(user.getCin());
           
           cell=row.createCell(9);
           cell.setCellValue(user.getEmail());
           
           cell=row.createCell(10);
           cell.setCellValue(user.getRoles());
           
           
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}