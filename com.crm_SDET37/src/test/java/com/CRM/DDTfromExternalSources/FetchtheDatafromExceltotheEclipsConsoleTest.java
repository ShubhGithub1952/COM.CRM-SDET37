package com.CRM.DDTfromExternalSources;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.crm_SDET37.GenericUtility.ExcelUtility;

public class FetchtheDatafromExceltotheEclipsConsoleTest {
public static void main(String[] args) throws Throwable {
	ExcelUtility excelLibrary = new ExcelUtility();
	
	Workbook workbook = WorkbookFactory.create(new FileInputStream("./src/test/resources/SheetBook.xlsx"));
	for (int i = 2; i <=3; i++) 
	{
		String temp = "sheet"+i;
	Sheet sheet = workbook.getSheet(temp);
	for (int j = 0; j <= sheet.getLastRowNum(); j++) {
		Row row = sheet.getRow(j);
		for (int k = 0; k <= row.getLastCellNum(); k++) {
			Cell cell = row.getCell(k);
			DataFormatter dataFormatter= new DataFormatter();
			String data1 = dataFormatter.formatCellValue(cell);
			System.out.print(data1+"   ");
		}
		System.out.println(  );
	}
	System.out.println();
}
}
}
