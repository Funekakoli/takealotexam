package pageObjectsTakeaLot;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import frameWorkClasses.BasePage;

public class TakeAlotHomePage extends BasePage{
	
	public void ClickCookiesButton() {
		if(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".button.cookies-banner-module_dismiss-button_24Z98")) != null)
			ClickElement(By.cssSelector(".button.cookies-banner-module_dismiss-button_24Z98"));
	}
		
	public void GoHome() {
		//driver.navigate().back();
		driver.get(getDataConfigPropeties("systemUnderTest"));
		
			
		}
	}
	


