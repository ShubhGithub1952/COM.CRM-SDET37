package com.crm_SDET37.GenericUtility;
/**
 * 
 * @author SHUBH
 *
 */
public interface IConstants {
	String filePath="./src/test/resources/config.properties";
	String excelPath="./src/test/resources/SheetBook.xlsx";
	String chromeKey="webdriver.chrome.driver";
	String chromeValue="/src/main/resources/chromedriver.exe";
	String DBUrl="jdbc:mysql://localhost:3306/";
	String DBUserName="root";
	String DBPassword="root";
	int implicitlyWaitDuration=10;
	int explictlyWaitDuration=10;
}
