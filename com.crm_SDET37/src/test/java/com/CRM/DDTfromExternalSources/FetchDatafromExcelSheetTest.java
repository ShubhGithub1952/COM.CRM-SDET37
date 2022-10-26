package com.CRM.DDTfromExternalSources;

import com.crm_SDET37.GenericUtility.ExcelUtility;

public class FetchDatafromExcelSheetTest {
	
public static void main(String[] args) throws Throwable {
	ExcelUtility excelLibrary = new ExcelUtility();
	String excelValue = excelLibrary.getDataFromExcel("Sheet3",1, 0);
	System.out.println(excelValue);
}
}
