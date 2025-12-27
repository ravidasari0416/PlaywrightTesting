package utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import base.BaseTest;


public class ExcelUtils extends BaseTest {
	

	
	public static Map<String, String> getCredentialsByRole(String filePath, String sheetName, String role) {
		String expectedRole = role.toUpperCase();
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			
			int rowCount = sheet.getLastRowNum();
			
			for (int i=1; i<=rowCount; i++) {
				Row row = sheet.getRow(i);
				String roleName = row.getCell(0).toString().trim();
				
				if(roleName.equalsIgnoreCase(expectedRole)) {
					Map<String, String> creds = new HashMap<>();
					creds.put("username", row.getCell(1).toString());
					creds.put("password", row.getCell(2).toString());
					
					workbook.close();
					fis.close();
					return creds;
				}
			}
			
			workbook.close();
			fis.close();
			
		} catch (Exception e) {
			throw new RuntimeException("Error reading Excel file: " + e.getMessage());
			

		}
		throw new RuntimeException("Role not found: " + expectedRole);
	}

}
