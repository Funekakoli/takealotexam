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
public String CheckNumberOfDisplyedItems() {
			
	return getElementText(By.xpath("//div[contains(text(), 'deals')]"));		
}
public void ClickFirstItem() {
	ClickElement(By.xpath("//a[contains(@class, 'product-anchor product-card-module_product-anchor_TUCBV')]"));
}


}
