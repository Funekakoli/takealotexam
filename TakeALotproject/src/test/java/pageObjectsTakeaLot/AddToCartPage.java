package pageObjectsTakeaLot;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class AddToCartPage extends BasePage {

	public void AddToCart() {

		// ClickElement(By.xpath("//*[@id=\"shopfront-app\"]/div[4]/div[1]/div[2]/aside//a"));
		ClickElement(By.xpath(
				"//*[@class='button  expanded add-to-cart-button add-to-cart-button-module_add-to-cart-button_1a9gT']"));
	}

	public void SwitchToNewTab() {
		Set<String> handles = driver.getWindowHandles(); // selenium will check how many windows are currently open,
															// this will store the ID for both parent and child window
		Iterator<String> it = handles.iterator(); // using the it object you can access the ID
		String parentWindowID = it.next();
		String childWindowID = it.next();
		driver.switchTo().window(childWindowID); // switch to new window by passing the ID of the child window
	}

	public String VerifyAddedToCartIsDisplayed() {
		return getElementText(By.xpath("//span[contains(text(), 'Added to cart')]"));
		// return getElementText(By.xpath("//span[contains(@class,
		// 'shiitake-children')]"));
		// return getElementText(By.xpath("//span[@Class = 'shiitake-children']"));
	}

	public void ClickTheGoToCartButton() {
		ClickElement(By.xpath("//button[@class='button checkout-now dark']"));
	}

	public void SelectQuantity() {
		selectDropown(By.id("cart-item_undefined"), "2");
	}

	public void SelectQuantityParameterized(String quantity) {
		selectDropown(By.id("cart-item_undefined"), quantity);
	}

	public String AssertCartValue() {
		return getElementText(By.xpath("//span[contains(@class, 'currency plus currency-module_currency_29IIm')]"));
	}
}
