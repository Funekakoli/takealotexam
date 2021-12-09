package pageObjectsTakeaLot;

import static org.testng.Assert.assertEquals;

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

	public String getAddedToCartMessage() {
		return getElementText(By.xpath(
				"//div[@class='cell auto drawer-title drawer-screen-module_drawer-title_3BX9x']//*[@class='shiitake-children']"));
	}

	public void VerifyAddedToCartIsDisplayed() {
		assertEquals(getElementText(By.xpath(
				"//div[@class='cell auto drawer-title drawer-screen-module_drawer-title_3BX9x']//*[@class='shiitake-children']")),
				"Added to cart");
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

	public String SpendMoreMessage() {
		// return getElementText(By.xpath("//div[@class='cart-content-module_show-free-delivery_DHnaQ free-delivery-module_free-delivery-tab_3xNIm]"));
		return getElementText(By.xpath(
				"//div[@id='shopfront-app']//*[@class='cart-content-module_show-free-delivery_DHnaQ free-delivery-module_free-delivery-tab_3xNIm']"));

	}
}
