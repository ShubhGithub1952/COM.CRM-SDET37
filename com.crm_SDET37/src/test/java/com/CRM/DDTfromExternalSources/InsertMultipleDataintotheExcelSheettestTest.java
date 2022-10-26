package com.CRM.DDTfromExternalSources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InsertMultipleDataintotheExcelSheettestTest {
public static void main(String[] args) throws EncryptedDocumentException, FileNotFoundException, IOException {
	Workbook workbook = WorkbookFactory.create(new FileInputStream("./src/test/resources/SheetBook.xlsx"));
	Sheet sheet = workbook.getSheet("Sheet1");
	WebDriver driver= WebDriverManager.chromedriver().create();
	driver.get("http://localhost:8888/");
	WebElement userNameTextField = driver.findElement(By.name("user_name"));
	userNameTextField.clear();
	userNameTextField.sendKeys("admin");
	WebElement passwordTextField = driver.findElement(By.name("user_password"));
	passwordTextField.clear();
	passwordTextField.sendKeys("admin");
	driver.findElement(By.id("submitButton")).click();
	List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
	int count = allLinks.size();
	for (int i = 1; i < count ; i++) {
		Row row = sheet.createRow(i);
		Cell cell = row.createCell(1);
	cell.setCellValue(allLinks.get(i).getAttribute("href"));
	}
	FileOutputStream fout = new FileOutputStream("./src/test/resources/SheetBook.xlsx");
	workbook.write(fout);
}
}
