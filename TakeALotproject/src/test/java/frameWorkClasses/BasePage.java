package frameWorkClasses;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	// Declare the WebDriver
	public static WebDriver driver;
	
	// Constructor of the base class
	public BasePage() {
		if (driver == null) {
			//Get Parameter values
			String browser = getDataConfigPropeties("browser");
			String URL = getDataConfigPropeties("systemUnderTest");
			String pdriverDir = getDataConfigPropeties("driverdir");
			// String pdriverDirFireFox = getDataConfirgPropeties("driverdirFirefox");
			// String pdriverDirEdge = getDataConfirgPropeties("driverdirEdge");
			
			//check if parameter passed as "chrome"
			if (browser.equalsIgnoreCase("chrome")) {
				//Set path to chromedriver.exe
				System.setProperty("webdriver.chrome.driver", pdriverDir + "chromedriver.exe");
				//create an instance of chrome
				driver = new ChromeDriver();
				driver.get(URL);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
		}
	}
					
	// Create a method to read the config
	public String getDataConfigPropeties(String propertyName) {
		// Properties set
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream("config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(propertyName);

	}

	// Create a method to wait for Element
	public void waitForElement(int elementWait, By pLocator) {
		WebDriverWait wait = new WebDriverWait(driver, elementWait);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pLocator));
	}

	// Create a method to wait for URL
	public void waitForUrl(int elementWait, String pLocator) {
		WebDriverWait wait = new WebDriverWait(driver, elementWait);
		wait.until(ExpectedConditions.urlContains(pLocator));
	}

	// Create a method to wait for Click
	public void waitforClick(int elementWait, By pLocator) {
		WebDriverWait wait = new WebDriverWait(driver, elementWait);
		wait.until(ExpectedConditions.elementToBeClickable(pLocator));
	}

	// create a method to get Element Text
	public String getElementText(By pLocator) {
		waitForElement(20, pLocator);
		String h1 = getElement(pLocator).getText();
		return h1;
	}

	// Create a method to click Element
	public void ClickElement(By pLocator) {
		waitforClick(30, pLocator);
		getElement(pLocator).click();
	}

	// create a method to get Element
	public WebElement getElement(By pLococator) {
		waitforClick(30, pLococator);
		return driver.findElement(pLococator);
	}

	// Clean up (close the browser)
	public void cleanUp() {
		driver.close();
	}

	public void closeChildBrowserTab() {
		Set<String> handles = driver.getWindowHandles();         //selenium will check how many windows are currently open, this will store the ID for both parent and child window
		Iterator<String> it = handles.iterator();                //using the it object you can access the ID
		String parentWindowID = it.next();
		String childWindowID = it.next();
	    driver.switchTo().window(childWindowID);
	    driver.close();
	    driver.switchTo().window(parentWindowID);
	}
	
	// Create a method to EnterText
	public void EnterText(By pLocator, String pText) {
		waitforClick(30, pLocator);
		driver.findElement(pLocator).sendKeys(pText);
	}

	//Create a method to clear the exiting value in a textbox
	public void ClearText(By pLocator) {
		waitforClick(30, pLocator);
		driver.findElement(pLocator).clear();
	}
	
	// Create a method to select the drop down

	public void selectDropown(By pLocator, String pVaue) {
		// Create an instance of the dropdown object

		Select sDrpDown = new Select(getElement(pLocator));
		// Populates the DropDown
		sDrpDown.selectByVisibleText(pVaue);

	}
	
	public void enterAction(By pLocator, Keys action) {
		driver.findElement(pLocator).sendKeys(action);
	}

}
	
