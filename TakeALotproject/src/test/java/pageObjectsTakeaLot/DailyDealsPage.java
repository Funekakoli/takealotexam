package pageObjectsTakeaLot;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class DailyDealsPage extends BasePage {

	public void SelectDailyDeals() {
		ClickElement(By.xpath("//a[contains(@href, 'https://www.takealot.com/deals?sort=Relevance')]"));
	}

	public void ClickOnAdidasFitnessCheckbox() {
		ClickElement(By.xpath("//span[contains(text(), 'adidas Fitness')]"));
	}

	public void ClickAfriTrailCheckbox() {
		ClickElement(By.xpath("//span[contains(text(), 'AfriTrail')]"));
	}
	public void ClickAANDKCheckbox() {
		//ClickElement(By.xpath("//label[contains(@class, 'checkbox grid-x align-middle')] "));
		ClickElement(By.xpath("//span[contains(text(), 'A&K')]"));
		
	}
	public String CheckDisplayedMessage() {
		return getElementText(By.xpath("//h2[contains(text(), 'No results found')]"));
	}

	public String CheckNumberOfDisplyedItems() {

		return getElementText(By.xpath("//div[contains(text(), 'deals')]"));
	}

	public void ClickFirstItem() {
		ClickElement(By.xpath("//a[contains(@class, 'product-anchor product-card-module_product-anchor_TUCBV')]"));
	}

}
