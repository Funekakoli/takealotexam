package takealotproject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjectsTakeaLot.AddToCartPage;
import pageObjectsTakeaLot.DailyDealsPage;
import pageObjectsTakeaLot.TakeAlotHomePage;

public class DailyDealsTests {
	// Instatiate the page objects
	TakeAlotHomePage hp = new TakeAlotHomePage();
	DailyDealsPage dd = new DailyDealsPage();
	AddToCartPage addcart = new AddToCartPage();
	

	@Test
	public void GIVEN_Customer_Clicks_On_DailyDealsButton_THEN_All_Available_Deals_Should_Be_Displayed() {
		dd.SelectDailyDeals();
		// dd.ClickOnAdidasFitnessCheckbox();
		dd.ClickAfriTrailCheckbox();
		System.out.println("Searched items successully displayed");
		hp.GoHome();
	}

	@BeforeTest
	public void setUp() {
		hp.ClickCookiesButton();

	}

	@Test
	public void Assert_That_There_Are_More_Than_0_Items_Displayed() {
		// GIVEN a customer search for a product
		dd.SelectDailyDeals();
		// dd.ClickOnAdidasFitnessCheckbox();
		dd.ClickAfriTrailCheckbox();

		// WHEN the customer checks the number of displayed items for that product  //I did this test different from Search for products, 
		//I was playing with the  asserts options
		String actual = dd.CheckNumberOfDisplyedItems();
		String expected = "2 deals";

		// THEN the system must print the number of items displayed
		Assert.assertEquals(actual, expected);
		System.out.println("There are " + actual + " displayed");
		hp.GoHome();

	}

	@Test
	public void GIVEN_There_Is_Less_Than_One_SelectedItem_Displayed_THEN_Skip_The_TestCase() {

//		// GIVEN a customer search for a product
		dd.SelectDailyDeals();
		dd.ClickAANDKCheckbox();
		//THEN check the message that is displayed and skip the test if the brand is not found
		String actualmessage = dd.CheckDisplayedMessage();
		String expectedmessage = "No results found";

			if (actualmessage == expectedmessage) { 
				System.out.println("Test case skipped, brand is not available");
			}
			throw new SkipException("This is a skipped test case");

	}

	@Test(enabled = false)
	public void Assert_That_AddedToCart_Appears() {
		// GIVEN
		hp.ClickCookiesButton();

		// WHEN
		dd.SelectDailyDeals();
		// hp.ClickOnAdidasFitnessCheckbox();
		dd.ClickAfriTrailCheckbox();

		// AND
		dd.ClickFirstItem();
		addcart.AddToCart();

		// THEN
		String actual = addcart.getAddedToCartMessage();
		String expected = "Added to cart";
		Assert.assertEquals(actual, expected);
		System.out.println(actual + " is displayed");
		hp.GoHome();
	}

}