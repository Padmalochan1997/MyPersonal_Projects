package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import baseClass.BaseClass;

public class Identifiers {

	public WebElement getWebElement(String identifierType, String identifierValue) {

		switch(identifierType)
		{

		case "XPATH":
			return BaseClass.driver.findElement(By.xpath(identifierValue));

		case "CSS":
			return BaseClass.driver.findElement(By.cssSelector(identifierValue));

		case "ClassName":
			return BaseClass.driver.findElement(By.className(identifierValue));

		case "ID":
			return BaseClass.driver.findElement(By.id(identifierValue));

		case "Name":
			return BaseClass.driver.findElement(By.name(identifierValue));

		case "LinkText":
			return BaseClass.driver.findElement(By.linkText(identifierValue));

		case "PatialLinkText":
			return BaseClass.driver.findElement(By.partialLinkText(identifierValue));

		default:
			return null;

		}
	}	
	public List<WebElement> getWebElements(String identifierType, String identifierValue) {

		switch(identifierType)
		{

		case "XPATH":
			return BaseClass.driver.findElements(By.xpath(identifierValue));

		case "CSS":
			return BaseClass.driver.findElements(By.cssSelector(identifierValue));

		case "ClassName":
			return BaseClass.driver.findElements(By.className(identifierValue));

		case "ID":
			return BaseClass.driver.findElements(By.id(identifierValue));

		case "Name":
			return BaseClass.driver.findElements(By.name(identifierValue));

		case "LinkText":
			return BaseClass.driver.findElements(By.linkText(identifierValue));

		case "PatialLinkText":
			return BaseClass.driver.findElements(By.partialLinkText(identifierValue));

		default:
			return null;

		}
	}
}
