package com.crm_SDET37.GenericUtility;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	/**
	 * this Method is used to Fetch data From Excel
	 * @param SheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws Exception
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getDataFromExcel(String SheetName, int rowNum, int cellNum) throws Throwable {
		Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelPath));
		Sheet sheet = workbook.getSheet(SheetName);
		Row row = sheet.getRow(rowNum);
		Cell cellValue = row.getCell(cellNum);
		DataFormatter format = new DataFormatter();
		String value = format.formatCellValue(cellValue);
		return value;
	}
	/** 
	 * This Method is Used to insert Data into the Excel Cell
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param CellData
	 * @throws Throwable
	 */
	public void insertDataIntoExcel(String sheetName,int rowNum,int cellNum,String CellData) throws Throwable {
		Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelPath));
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		cell.setCellValue(CellData);
		FileOutputStream fOut = new FileOutputStream(IConstants.excelPath);
		workbook.write(fOut);
	}
	/**
	 * this method is used to get last row of the Excel Sheet
	 * @param SheetName
	 * @return
	 * @throws Throwable
	 */
	public int getLastRowNumFromExcel(String SheetName) throws Throwable {
		Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelPath));
		Sheet sheet = workbook.getSheet(SheetName);
		int lastRow = sheet.getLastRowNum();
		return lastRow;
	}
}