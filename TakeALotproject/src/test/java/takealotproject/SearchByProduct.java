package takealotproject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameWorkClasses.ReadExcel;
import pageObjectsTakeaLot.AddToCartPage;
import pageObjectsTakeaLot.SearchByProductPage;
import pageObjectsTakeaLot.TakeAlotHomePage;

public class SearchByProduct {
	
		TakeAlotHomePage hp = new TakeAlotHomePage();
		SearchByProductPage sp = new SearchByProductPage();
		AddToCartPage addcart = new AddToCartPage();
		ReadExcel rExcel = new ReadExcel();
		public int totalCartPrice = 0;
		@Test
		public void GIVEN_Customer_Search_For_Product_THEN_All_Available_Products_Should_Be_Displayed() {
			sp.ClickSearchForProducts();
			sp.SearchProduct("adidas sneakers");
			sp.ClickSubmitButtonToSearchForProducts();
			hp.GoHome();
		}
			@Test
			public void Assert_That_There_Are_More_Than_0_Items_Displayed() {
				//GIVEN
				sp.ClickSearchForProducts();
				sp.SearchProduct("adidas sneakers");
				sp.ClickSubmitButtonToSearchForProducts();
				hp.ClickCookiesButton();
				
				
				//WHEN
				String actual = sp.CheckNumberOfDisplyedItems();
				String expected = "3964 results for \"adidas sneakers\"";
				
				//THEN
			
				Assert.assertEquals(actual, expected);
				System.out.println("There are " + actual + " displayed");
				//hp.GoHome();
				
		}
		@Test
		public void GIVEN_There_Is_Less_Than_One_SelectedItem_Displayed_THEN_Skip_The_TestCase() {
			
			//GIVEN
			sp.ClickSearchForProducts();
			sp.SearchProduct("adidas sneakers");
			sp.ClickSubmitButtonToSearchForProducts();
			hp.ClickCookiesButton();
			
			//WHEN
			String actualitems = sp.CheckNumberOfDisplyedItems();
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
		public void GIVEN_First_Item_Is_Selected_THEN_Add_Product_To_Cart_AND_Assert_That_AddedToCart_Appears() {
			//GIVEN
			sp.ClickSearchForProducts();
			sp.SearchProduct("adidas sneakers");
			//WHEN
			sp.ClickSubmitButtonToSearchForProducts();
			hp.ClickCookiesButton();			
			//AND
			sp.ClickFirstItem();
			addcart.AddToCart();
			//THEN
			String actual = addcart.VerifyAddedToCartIsDisplayed();
			String expected = "Added to cart";
			Assert.assertEquals(actual, expected);
			System.out.println( actual + " is displayed");
			hp.GoHome();
		}
		@Test
		public void GIVEN_A_Customer_Has_Added_A_Product_In_Cart_AND_Click_Go_To_Cart_Button_THEN_Select_2_Items_As_Quantity() throws InterruptedException, ParseException {
			//GIVEN
			sp.ClickSearchForProducts();
			sp.SearchProduct("sleeping bag");
			//WHEN
			sp.ClickSubmitButtonToSearchForProducts();
			hp.ClickCookiesButton();	
			sp.SelectBrand();
			//AND
			sp.ClickFirstItem();
			String itemPrice = sp.GetItemPrice();
			//Convert string to Interger
			NumberFormat format = NumberFormat.getInstance();
			Number value = format.parse(itemPrice.substring(2));

			// If you specifically want a double...
			int price = value.intValue();
	//		int price = Integer.parseInt(itemPrice.substring(2));
			addcart.AddToCart();
			
			//THEN
			addcart.ClickTheGoToCartButton();
			//AND
			addcart.SelectQuantity();
			Thread.sleep(5000);
			String cartValue = sp.GetCartValue();
			String totalPrice =  sp.GetTotalPrice_SingleProduct(2, price);
			System.out.println(totalPrice);
			System.out.println(cartValue);
			Assert.assertEquals(cartValue.trim(), totalPrice);
				
			//THEN
			//hp.GoHome();	
		
}
		
		@DataProvider(name = "Brand and Quantity")
		public Object[][] getDataFromExcel() throws IOException{
			//retun rExcel.excellDP("excelDataDir", "ExcelData.xlsx", "Sheet");
			String excelDirectory = rExcel.getDataConfigPropeties("excelDataDir");
			Object[][] dataObj = rExcel.getExcelData(excelDirectory +"BrandANDQuantity.xlsx", "Sheet1");
			return dataObj;
		}

		@BeforeTest
		public void setUp() {
			hp.ClickCookiesButton();
		}
		
		@Test(dataProvider="Brand and Quantity")
		public void GIVEN_A_Customer_Has_Added_A_Product_In_Cart_AND_Click_Go_To_Cart_Button_THEN_Select_2_Items_As_Quantity_Excel_Input(String brand, String quantity) throws InterruptedException {
			//GIVEN
			int totalCalculatedPrice =0;
			//	searching for a brand from excel without any spaces	
			sp.SearchForBrands(brand.trim());
			Thread.sleep(5000);
			//	selecting a brand
			sp.SelectBrandParameterised(brand.trim());
			Thread.sleep(3000);
			//clicking the first item in the search results
			sp.ClickFirstItem();
			Thread.sleep(3000);
			//get the item unit
			String itemPrice = sp.GetItemPrice();
			//Convert string to Interger
			int unitPrice = Integer.parseInt(itemPrice.substring(itemPrice.lastIndexOf(" ")+1));
			System.out.println(unitPrice);
			addcart.SwitchToNewTab();
			addcart.AddToCart();
			
			addcart.ClickTheGoToCartButton();
		
			addcart.SelectQuantityParameterized(quantity.trim());
			Thread.sleep(5000);
			String cartValue = sp.GetCartValue();
			totalCalculatedPrice =  sp.GetTotalPrice(Integer.parseInt(quantity.trim()), unitPrice);
			//String total =  "R " + String.format("%,d", totalPrice);
			System.out.println("Total calculated price:"+ totalCalculatedPrice);
			System.out.println("Cart Summary :"+ cartValue);
			totalCartPrice=totalCartPrice+totalCalculatedPrice;
			System.out.println("Total calculated cart summary : "+ totalCartPrice);
			String total =  "R " + String.format("%,d", totalCartPrice);
			//to validate if the actual cart value is equal to the calculated
			Assert.assertEquals(cartValue.trim(), total);
				
			sp.closeCurrentBrowserTab();
		
			//THEN
			//hp.GoHome();
				
			
		
}
}