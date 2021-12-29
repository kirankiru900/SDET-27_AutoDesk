package com.crm.autodesk.genericutility;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

import com.google.common.io.Files;

/**
 * it contains WenDriver specific reusable actions
 * @author AJAY KUMAR S
 *
 */

public class WebDriverUtility {
	
	/**
	 * wait for page to load before identifying any synchronized element in DOM[html doc]
	 * @param driver
	 */

	public void waitForPageToGetLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	/**
	 * wait for page to load before identifying any unsynchronized[java script actions] element in DOM 
	 * @param driver
	 */
	public void waitForPageToLoadForJSElement(WebDriver driver) {
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
	}
	
    /**
     * used to wait for element to be clickable in GUI , & check for specific element for every 500 milli seconds
     * @param driver
     * @param element
     */
	public void waitForElemnetToBeClickAble(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	/**
	 * used to wait for element to be clickable in GUI, and check for specific element for every pollingtime specified
	 * @param driver
	 * @param element
	 * @param pollingTime
	 * @throws Throwable
	 */
	public void waitForElementWithCustomTimeOut(WebDriver driver,WebElement element,int pollingTime) throws Throwable {
	FluentWait wait = new FluentWait(driver);
	wait.pollingEvery(pollingTime, TimeUnit.SECONDS);
	wait.wait(20);
	wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	/**
	 * used to switch to any window based on window title
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWindowTitle) {
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		
		while(it.hasNext()) {
			String wID=it.next();
			driver.switchTo().window(wID);
			String CurrentWindowTitle=driver.getTitle();
			if(CurrentWindowTitle.contains(partialWindowTitle)) {
				break;
			}
			
		}
	}
	
	/**
	 * used to switch to alert window and click on Ok button
	 * @param driver
	 */
	public void switchToAlertWindowAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * used to switch to alert window and click on Cancel button
	 * @param driver
	 */
	public void switchToAlertWindowAndCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * used to switch to frame window based on index
	 * @param driver
	 * @param index
	 */
	
	public void swithToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * used to switch to frame window based on id or name attribute
	 * @param driver
	 * @param id_name_attribute
	 */
	
	public void switchToFrame(WebDriver driver,String id_name_attribute) {
		driver.switchTo().frame(id_name_attribute);
	}
	
	/**
	 * used to select the value based on Index
	 * @param element
	 * @param index
	 */
	
	public void select(WebElement element,int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	/**
	 * used to select the value based on the value/option available in GUI
	 * @param element
	 * @param value
	 */
	public void select(WebElement element,String value) {
		Select sel=new Select(element);
		sel.selectByVisibleText(value);
	}
	
	/**
	 * used to place mouse cursor on specified element
	 * @param driver
	 * @param element
	 */
	public void mouseOverOnElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	/**
	 * used to right click on specified element
	 * @param driver
	 * @param element
	 */
	public void rightClickOnElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.contextClick(element);
	}
	
	public void passEnterKey(WebDriver driver) {
		Actions action=new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}
	
	public void takeScreenshot(WebDriver driver, String screenshotName) throws Throwable {
    	TakesScreenshot ts=(TakesScreenshot)driver;
    	File src=ts.getScreenshotAs(OutputType.FILE);
    	File dest=new File("./screenshot/"+screenshotName+".PNG");
    	Files.copy(src, dest);
    }

}
