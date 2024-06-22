package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import baseClass.BaseClass;

public class FetchElements {
	
	public WebElement getWebElement(String element, String value) {
		
		switch(element) {
		case "XPATH":
		return BaseClass.driver.findElement(By.xpath(value));
		case "CSS":
			return BaseClass.driver.findElement(By.cssSelector(value));
		case "ID":
			return BaseClass.driver.findElement(By.id(value));
		case "CLASS":
			return BaseClass.driver.findElement(By.className(value));
		case "LINK_TEXT":
			return BaseClass.driver.findElement(By.linkText(value));
		case "PARTIAL_LINK_TEXT":
			return BaseClass.driver.findElement(By.partialLinkText(value));
		case "TAG_NAME":
			return BaseClass.driver.findElement(By.tagName(value));			

		default:
			return null;
		}
		
	}
	
public List<WebElement> getWebElements(String element, String value) {
		
		switch(element) {
		case "XPATH":
		return BaseClass.driver.findElements(By.xpath(value));
		case "CSS":
			return BaseClass.driver.findElements(By.cssSelector(value));
		case "ID":
			return BaseClass.driver.findElements(By.id(value));
		case "CLASS":
			return BaseClass.driver.findElements(By.className(value));
		case "LINK_TEXT":
			return BaseClass.driver.findElements(By.linkText(value));
		case "PARTIAL_LINK_TEXT":
			return BaseClass.driver.findElements(By.partialLinkText(value));
		case "TAG_NAME":
			return BaseClass.driver.findElements(By.tagName(value));			

		default:
			return null;
		}
		
	}

}
