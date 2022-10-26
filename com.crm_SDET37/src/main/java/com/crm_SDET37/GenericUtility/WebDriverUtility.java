package com.crm_SDET37.GenericUtility;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverUtility {

	public void maximizeTheBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}
	public void minimizeTheBrowser(WebDriver driver) {
		driver.manage().window().minimize();
	}
	public void refershThePage(WebDriver driver) {
		driver.navigate().refresh();
	}
	public void backToPriviousPage(WebDriver driver) {
		driver.navigate().back();
	}
	public void forwardToNextPage(WebDriver driver) {
		driver.navigate().forward();
	}
	public void waitImplicitlyToPageGetsLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IConstants.implicitlyWaitDuration));
	}
	public void waitTillElementToClick(WebDriver driver,WebElement element) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(IConstants.explictlyWaitDuration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	public void waitTillElementToVisible(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IConstants.explictlyWaitDuration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void pageLoadTitle(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IConstants.explictlyWaitDuration));
		wait.until(ExpectedConditions.titleContains(title));

	}
	public void pageLoadUrl(WebDriver driver, String URL) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IConstants.explictlyWaitDuration));
		wait.until(ExpectedConditions.urlContains(URL));
	}
	public void ignoreNoSuchElementException(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IConstants.explictlyWaitDuration));
		wait.ignoring(NoSuchElementException.class);
	}
	public void waitForAlertMsg(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IConstants.explictlyWaitDuration));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String id) {
		driver.switchTo().frame(id);
	}
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	public void switchToMainFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	public void select(WebElement element,int index)
	{
		Select select=new Select(element);
		select.selectByIndex(index);
	}
	public void select(WebElement element,String value)
	{
		Select select=new Select(element);
		select.selectByValue(value);
	}
	public void select(String text,WebElement element)
	{
		Select select=new Select(element);
		select.selectByVisibleText(text);
	}
	public void getAllTheOptiondFromDropDown(WebElement element)
	{
		Select select=new Select(element);
       List<WebElement> options = select.getOptions();
		//List options = select.getOptions();
		for(WebElement webElement : options)
		{
			String text = webElement.getText();
			System.out.println(text);
		}
	}
	public void mouseOverOnElement(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
	}		
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.contextClick(element).perform();
	}
	public void doubleClick(WebDriver driver,WebElement element) 
	{
		Actions action=new Actions(driver);
		action.doubleClick(element).perform();
	}
	public void enterKeyClick(WebDriver driver) 
	{
		Actions action=new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}
	// to takeScreenshot
	public static String takeScreenshot(WebDriver driver,String ScreenShotName)
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		 File srcFile = ts.getScreenshotAs(OutputType.FILE);
		 //File srcFile1 = ts.getScreenshotAs(OutputType.FILE);
	    File dstFile = new File("./screenshot"+ScreenShotName+".PNG");
	
		try {
			FileUtils.copyFile(srcFile, dstFile);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ScreenShotName;
	}
	public void waitAndClickUsingCustomWait(WebDriver driver)
	{
		FluentWait wait = new FluentWait(driver);
		wait.pollingEvery(Duration.ofSeconds(500));
		wait.ignoring(NoSuchElementException.class);
		try 
		{
			wait.wait(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void waitAndClick(WebElement element, int duration)
	{
		int count=0;
		while(count<duration)
		{
			try 
			{
				element.click();
				break;
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
			count++;
		}
	}

	public void switchToWindow(WebDriver driver, String actualTitle)
	{
		Set<String> set = driver.getWindowHandles();
		for (String string : set) 
		{
			driver.switchTo().window(string);
			String title = driver.getTitle();
			if(title.contains(actualTitle))
			{
				break;
			}
		}
	}
	int count =1;
	public void switchToWindow(String actualURL, WebDriver driver)
	{
		Set<String> set = driver.getWindowHandles();
		for (String string : set) {
			driver.switchTo().window(string);
			String Url = driver.getCurrentUrl();
			if(Url.contains(actualURL))
			{
				System.out.println("Driver Control is Moved to Next Window :-"+ count +" !");
				count++;
				break;
			}
			
		}
	}
	public void switchToAlertPopUpAndAccept(WebDriver driver, String expmsg)
	{
		Alert al = driver.switchTo().alert();
		if(al.getText().trim().equalsIgnoreCase(expmsg))
		{
			System.out.println("alert message verified");
		}
		else 
		{
			System.out.println("alert message is not verified");	
		}
		al.accept();
	}
	public void switchToAlertPopUpAndDismiss(String expmsg,WebDriver driver)
	{
		Alert al = driver.switchTo().alert();
		if(al.getText().trim().equalsIgnoreCase(expmsg))
		{
			System.out.println("alert message verified");
		}
		else 
		{
			System.out.println("alert message is not verified");	
		}
		al.dismiss();
	}
}

