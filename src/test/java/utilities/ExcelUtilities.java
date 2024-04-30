package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public static void writeExcel(String sheetName, int rownum, int colnum, String data)throws IOException {

		//Opening the excel file
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\TestData\\ExcelFile.xlsx");

		XSSFWorkbook book=new XSSFWorkbook(file);

		//Creating the sheet if it does not exist
		if(book.getSheetIndex(sheetName)==-1) {
			book.createSheet(sheetName);              
		}
		//creating Sheet
		XSSFSheet sheet=book.getSheet(sheetName);
		
		//creating Row
		if(sheet.getRow(rownum)==null)
		{
			sheet.createRow(rownum);
		}
		XSSFRow row =sheet.getRow(rownum);   

		// Creating cell
		XSSFCell cell=row.createCell(colnum); 

		//Setting the data in the column
		cell.setCellValue(data); 

 
		FileOutputStream fo=new FileOutputStream(System.getProperty("user.dir")+"\\TestData\\ExcelFile.xlsx");

		book.write(fo);

		book.close();
		
		file.close();

		fo.flush();

		fo.close();

	}
	
	
}
