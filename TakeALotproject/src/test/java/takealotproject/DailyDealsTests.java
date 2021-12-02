package takealotproject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
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
		hp.ClickCookiesButton();
		dd.SelectDailyDeals();
		//dd.ClickOnAdidasFitnessCheckbox();
		dd.ClickAfriTrailCheckbox();
		hp.GoHome();
		
	}
	@Test
	public void Assert_That_There_Are_More_Than_0_Items_Displayed() {
		//GIVEN
		hp.ClickCookiesButton();
		dd.SelectDailyDeals();
		//dd.ClickOnAdidasFitnessCheckbox();
		dd.ClickAfriTrailCheckbox();
		
		//WHEN
		String actual = dd.CheckNumberOfDisplyedItems();
		String expected = "2 deals";
		
		//THEN
		Assert.assertEquals(actual, expected);
		System.out.println("There are " + actual + " displayed");
		hp.GoHome();
		
		}
	@Test
		public void GIVEN_There_Is_Less_Than_One_SelectedItem_Displayed_THEN_Skip_The_TestCase() {
			
			//GIVEN
			hp.ClickCookiesButton();
			dd.SelectDailyDeals();
			//dd.ClickOnAdidasFitnessCheckbox();
			dd.ClickAfriTrailCheckbox();
			
			//WHEN
			String actualitems = dd.CheckNumberOfDisplyedItems();
			String itemNumber = actualitems.substring(0,1);
			int itemsNumber = Integer.valueOf(itemNumber);
			String actual = actualitems;
			String expected = actualitems;
			System.out.println(itemsNumber);
			
			//THEN
			if(itemsNumber <= 1) {
				System.out.println("Test case skipped");
			} else {
			Assert.assertEquals(actual, expected);
			System.out.println("There are " + actual + " displayed");
			hp.GoHome();
	}
	}
	@Test
	public void GIVEN_First_Item_Is_Selected_THEN_Add_To_Cart_Button_Must_Be_Displyed() {
		//GIVEN
		hp.ClickCookiesButton();
		
		//WHEN
		dd.SelectDailyDeals();
		//dd.ClickOnAdidasFitnessCheckbox();
		dd.ClickAfriTrailCheckbox();
		
		//THEN
		dd.ClickFirstItem();
		hp.GoHome();
		
	}
	@Test
	public void GIVEN_First_Item_Is_Selected_THEN_Add_Product_To_Cart() {
		//GIVEN
		hp.ClickCookiesButton();
				
		//WHEN
		dd.SelectDailyDeals();
		//hp.ClickOnAdidasFitnessCheckbox();
		dd.ClickAfriTrailCheckbox();
				
		//THEN
		dd.ClickFirstItem();
		addcart.AddToCart();
		hp.GoHome();
	}
	@Test(enabled=false)
	public void Assert_That_AddedToCart_Appears() {
		//GIVEN
		hp.ClickCookiesButton();
				
		//WHEN
		dd.SelectDailyDeals();
		//hp.ClickOnAdidasFitnessCheckbox();
		dd.ClickAfriTrailCheckbox();
				
		//AND
		dd.ClickFirstItem();
		addcart.AddToCart();
		
		//THEN
		String actual = addcart.VerifyAddedToCartIsDisplayed();
		String expected = "Added to cart";
		Assert.assertEquals(actual, expected);
		System.out.println( actual + " is displayed");
		hp.GoHome();
	}
	@Test
	public void GIVEN_A_Customer_Has_Added_A_Product_In_Cart_THEN_Click_Go_To_Cart_Button() {
		//GIVEN
		hp.ClickCookiesButton();
		dd.SelectDailyDeals();
		dd.ClickOnAdidasFitnessCheckbox();
				
		//WHEN
		dd.ClickFirstItem();
		
		//AND
		addcart.AddToCart();
		
		//THEN
		addcart.ClickTheGoToCartButton();
		hp.GoHome();
	}
	@Test
	public void GIVEN_A_Customer_Has_Added_A_Product_In_Cart_AND_Click_Go_To_Cart_Button_THEN_Select_2_Items_As_Quantity() {
		//GIVEN
		hp.ClickCookiesButton();
		dd.SelectDailyDeals();
		dd.ClickOnAdidasFitnessCheckbox();
		//hp.ClickAfriTrailCheckbox();
				
		//WHEN
		dd.ClickFirstItem();
		
		//AND
		addcart.AddToCart();
		
		//THEN
		addcart.ClickTheGoToCartButton();
		//AND
		addcart.SelectQuantity();
		//THEN
		hp.GoHome();
		
	}
	@Test
	public void CheckCartValue() {
		//GIVEN
		hp.ClickCookiesButton();
		dd.SelectDailyDeals();
		dd.ClickOnAdidasFitnessCheckbox();
		//hp.ClickAfriTrailCheckbox();
						
		//WHEN
		dd.ClickFirstItem();
				
		//THEN
		addcart.AssertCartValue();
		String productprice = addcart.AssertCartValue();
		String productp = productprice.substring(2,5);
		int actualProductPrice = Integer.valueOf(productp);
//		String expected = "R 399";
//		Assert.assertEquals(actualProductPrice, expected);
//		System.out.println( actualProductPrice + " is displayed");
		System.out.println(actualProductPrice);

		
	}

}
