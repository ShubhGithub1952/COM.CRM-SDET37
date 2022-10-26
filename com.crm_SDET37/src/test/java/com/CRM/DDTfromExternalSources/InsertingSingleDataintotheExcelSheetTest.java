package com.CRM.DDTfromExternalSources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class InsertingSingleDataintotheExcelSheetTest {
public static void main(String[] args) throws EncryptedDocumentException, FileNotFoundException, IOException {
	Workbook workbook = WorkbookFactory.create(new FileInputStream("./src/test/resources/SheetBook.xlsx"));
	Sheet sheet = workbook.getSheet("Sheet2");
	Row row = sheet.createRow(2);
	Cell cell = row.createCell(0);
	cell.setCellValue("Satish BP");
	FileOutputStream fout = new FileOutputStream("./src/test/resources/SheetBook.xlsx");
	workbook.write(fout);
}
}
