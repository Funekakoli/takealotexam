package pageObjectsTakeaLot;

import java.util.NoSuchElementException;

import org.apache.pdfbox.contentstream.operator.state.Concatenate;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.SendKeysAction;

import frameWorkClasses.BasePage;
import io.restassured.internal.filter.SendRequestFilter;
import takealotproject.SearchByProduct;

public class SearchByProductPage extends BasePage {

	
	public void ClickSearchForProducts() {
		ClickElement(By.xpath("//input[@name='search']"));	
		
	}
	public void SearchProduct(String brand) {
		EnterText(By.xpath("//input[@name='search']"), brand);
		
	}

	public void ClickSubmitButtonToSearchForProducts() {
		//ClickElement(By.xpath("//button[@type='submit]"));	
		ClickElement(By.xpath("//button[@class='button search-btn search-icon']"));
		
	}
	public void SelectBrand() {
		ClickElement(By.xpath("//span[contains(text(), 'Campmaster')]"));
	}
	
	public void SelectBrandParameterised(String brand) {
		ClickElement(By.xpath("//label[@for='filter_Brand_"+brand.trim()+"_filter_Brand_"+brand.trim()+"_"+brand.trim()+"']"));
	}
	
	public void SearchForBrands(String brand) {
		ClickElement(By.xpath("//input[@name='search']"));	
		ClearText(By.xpath("//input[@name='search']"));
		EnterText(By.xpath("//input[@name='search']"), brand);
		enterAction(By.xpath("//input[@name='search']"),Keys.ENTER);
	}
	
	public String CheckNumberOfDisplyedItems() {
		
	//	return getElementText(By.xpath("//div[@class='search-count toolbar-module_search-count_P0ViI search-count-module_search-count_1oyVQ')]"));	
		return getElementText(By.xpath("//div[contains(text(), 'adidas sneakers')]"));
	}
	public void ClickFirstItem() {
		ClickElement(By.xpath("//a[contains(@class, 'product-anchor product-card-module_product-anchor_TUCBV')]"));
		
	}
	public String GetItemPrice() {
		return getElementText(By.xpath("//span[contains(@class, 'currency plus currency-module_currency_29IIm')]"));
		
	}
	
	public String GetCartValue() {
		return getElementText(By.xpath("//*[contains(text(),'Cart Summary')]/..//span[contains(@class,'currency plus currency-module_currency_29IIm')]"));
	}
	
	public String GetTotalPrice_SingleProduct(int quantity, int unitPrice) {
		return "R " + String.format("%,d", quantity*unitPrice);
		
	}
	
	public int GetTotalPrice(int quantity, int unitPrice) {
		return quantity*unitPrice;
		
	}
	
	public void closeCurrentBrowserTab() {
		closeChildBrowserTab();
	}
	
	public void ClickGotIt() {
		try {
			if(driver.findElement(By.xpath("xpath_of_the_desired_element")).isDisplayed())
				System.out.println("Element is present and displayed");
			else
				System.out.println("Element is present but not displayed");

			
		}	catch (NoSuchElementException e) {

			System.out.println("Element is not present, hence not displayed as well");

			}
		
	
}
}