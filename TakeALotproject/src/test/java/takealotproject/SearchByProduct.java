package takealotproject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import org.testng.Assert;
import org.testng.SkipException;
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
		sp.SearchForBrands("sleeping bag");
		System.out.println("Searched items successully displayed");
		hp.GoHome();
	}

	@Test
	public void Assert_That_There_Are_More_Than_0_Items_Displayed() {
		// GIVEN a customer search for a product
		sp.ClickSearchForProducts();
		sp.SearchForBrands("adidas sneakers");

		// WHEN the customer checks the number of displayed items for that product
		String ActualNumberOfItems = sp.CheckNumberOfDisplyedItems();
		System.out.println("Number of Items displayed: " + ActualNumberOfItems);
		// actual.substring(ActualNumberOfItems.length(), ActualNumberOfItems.indexOf(" ")-1);
		String expectedNoOfItems = sp.CheckNumberOfDisplyedItems().replaceAll("\\D+", "");
		Integer itemsNumber = Integer.valueOf(expectedNoOfItems);
		// THEN system must print the number of items displayed ,if the items are greater than one
		if (itemsNumber >= 1) {
			System.out.println("There are more than 0 Items displayed");
			Assert.assertTrue(true);
			hp.GoHome();

		}
	}

	@Test
	public void GIVEN_There_Is_Less_Than_One_Selected_Item_Or_Brand_Displayed_THEN_Skip_The_TestCase() {

		// GIVEN a customer search for a product
		sp.ClickSearchForProducts();
		// WHEN a customer searches for a brand that is not available on Takealot
		sp.SearchForBrands("Maxhosa");

		// THEN check the message that is displayed and skip the test if the brand is not found
		String actualmessage = sp.CheckDisplayedMessage();
		String expectedmessage = "No results found";

		if (actualmessage == expectedmessage) { 
			System.out.println("Test case skipped, brand is not available");
		}
		throw new SkipException("This is a skipped test case");

	}

	@Test
	public void GIVEN_First_Item_Is_Selected_THEN_Add_Product_To_Cart_AND_Assert_That_AddedToCart_Appears()
			throws InterruptedException {
		// GIVEN a customer search for a product
		sp.ClickSearchForProducts();
		// AND search for a brand
		sp.SearchForBrands("Camp master");
		sp.SelectBrand();
		// WHEN customer clicks on the first item/brand
		sp.ClickFirstItem();
		// THEN switches to the new open tab
		addcart.SwitchToNewTab();
		Thread.sleep(5000);
		// AND add items to cart
		addcart.AddToCart();
		String actual = addcart.getAddedToCartMessage();
		String expected = "Added to cart";
		Assert.assertEquals(actual, expected);
		// THEN system must check that "added to Cart message is displayed"
		System.out.println(actual + " is displayed");
		sp.closeCurrentBrowserTab();
		hp.GoHome();
	}

	@Test
	public void GIVEN_A_Customer_Has_Added_A_Product_In_Cart_AND_Click_Go_To_Cart_Button_THEN_Select_2_Items_As_Quantity()
			throws InterruptedException, ParseException {
		// GIVEN a customer search for a product
		sp.ClickSearchForProducts();
		// AND search for a brand
		sp.SearchForBrands("sleeping bag");
		sp.SelectBrand();
		// WHEN customer clicks on the first item/brand
		sp.ClickFirstItem();
		addcart.SwitchToNewTab();
		String itemPrice = sp.GetItemPrice();
		// Convert a string to Integer
		NumberFormat format = NumberFormat.getInstance();
		Number value = format.parse(itemPrice.substring(2));

		// WHEN you specifically want a double...
		int price = value.intValue();
		// int price = Integer.parseInt(itemPrice.substring(2));
		addcart.AddToCart();
		Thread.sleep(2000);

		// THEN click on the go to button
		addcart.ClickTheGoToCartButton();
		// AND select the quantity of items
		addcart.SelectQuantity();
		Thread.sleep(5000);
		String cartValue = sp.GetCartValue();
		String totalPrice = sp.GetTotalPrice_SingleProduct(2, price);
		// THEN print out the price of the total items and cart value and assert if they are both the same
		System.out.println("The total price of the 2 items is: " + totalPrice);
		System.out.println("The total cart value is: " + cartValue);
		Assert.assertEquals(cartValue.trim(), totalPrice);
		sp.closeCurrentBrowserTab();
		hp.GoHome();

	}

	@DataProvider(name = "Brand and Quantity")
	public Object[][] getDataFromExcel() throws IOException {
		// retun rExcel.excellDP("excelDataDir", "ExcelData.xlsx", "Sheet");
		String excelDirectory = rExcel.getDataConfigPropeties("excelDataDir");
		Object[][] dataObj = rExcel.getExcelData(excelDirectory + "BrandANDQuantity.xlsx", "Sheet1");
		return dataObj;
	}

	@BeforeTest
	public void setUp() {
		hp.ClickCookiesButton();
	}

	@Test(dataProvider = "Brand and Quantity")
	public void Read_Brands_AND_Quamtity_From_Excel_And_Loop_All_The_Steps(
			String brand, String quantity) throws InterruptedException {

		int totalCalculatedPrice = 0;
		 // GIVEN you are searching the brands from excel data sheet (without any spaces)
		sp.SearchForBrands(brand.trim());
		Thread.sleep(5000);
		// AND selecting a brand
		sp.SelectBrandParameterised(brand.trim());
		Thread.sleep(5000);
		// THEN click the first item in the search results
		sp.ClickFirstItem();
		Thread.sleep(5000);
		// AND get the item price
		String itemPrice = sp.GetItemPrice();
		// AND convert the string to an Integer, replacing the "," with nothing ""
		int unitPrice = Integer.parseInt(itemPrice.substring(itemPrice.lastIndexOf(" ") + 1).replace(",", ""));
		System.out.println(unitPrice);
		addcart.SwitchToNewTab();
		// THEN add the items to cart
		addcart.AddToCart();
		Thread.sleep(2000);
		addcart.VerifyAddedToCartIsDisplayed();
		addcart.ClickTheGoToCartButton();
		// AND selecting quantity as displayed in the excel spreadsheet
		addcart.SelectQuantityParameterized(quantity.trim());
		Thread.sleep(5000);
		String cartValue = sp.GetCartValue();
		totalCalculatedPrice = sp.GetTotalPrice(Integer.parseInt(quantity.trim()), unitPrice);
		// String total = "R " + String.format("%,d", totalPrice);
		// THEN print out all the totals, and assert that the cart value does equal to total cart price
		System.out.println("Total calculated price:" + totalCalculatedPrice);
		System.out.println("Cart Summary :" + cartValue);
		totalCartPrice = totalCartPrice + totalCalculatedPrice;
		System.out.println("Total calculated cart summary : " + totalCartPrice);
		String total = "R " + String.format("%,d", totalCartPrice);
		// to validate if the actual cart value is equal to the calculated
		Assert.assertEquals(cartValue.trim(), total);

		sp.closeCurrentBrowserTab();
		hp.GoHome();
		
	}
	@DataProvider(name = "Brand and Quantity_BonusTest")
	public Object[][] getDataFromExcelTwo() throws IOException {
		// retun rExcel.excellDP("excelDataDir", "ExcelData.xlsx", "Sheet");
		String excelDirectory = rExcel.getDataConfigPropeties("excelDataDir1");
		Object[][] dataObj = rExcel.getExcelData(excelDirectory + "Brand and Quantity_BonusTest.xlsx", "Sheet1");
		return dataObj;
	}
	@Test(dataProvider = "Brand and Quantity_BonusTest")
	public void GIVEN_A_Customer_Add_Items_Less_Than_R450_THEN_Verify_If_Free_Delivery_Message_is_Displayed(
			String brand, String quantity) throws InterruptedException {

		int totalCalculatedPrice = 0;
		 // GIVEN you are searching the brands from excel data sheet (without any spaces)
		sp.SearchForBrands(brand.trim());
		Thread.sleep(5000);
		// AND selecting a brand
		sp.SelectBrandParameterised(brand.trim());
		Thread.sleep(5000);
		// THEN click the first item in the search results
		sp.ClickFirstItem();
		Thread.sleep(5000);
		// AND get the item price
		String itemPrice = sp.GetItemPrice();
		// AND convert the string to an Integer, replacing the "," with nothing ""
		int unitPrice = Integer.parseInt(itemPrice.substring(itemPrice.lastIndexOf(" ") + 1).replace(",", ""));
		System.out.println(unitPrice);
		addcart.SwitchToNewTab();
		// THEN add the items to cart
		addcart.AddToCart();
		Thread.sleep(2000);
		addcart.VerifyAddedToCartIsDisplayed();
		addcart.ClickTheGoToCartButton();
		// AND selecting quantity as displayed in the excel spreadsheet
		addcart.SelectQuantityParameterized(quantity.trim());
		Thread.sleep(5000);
		String cartValue = sp.GetCartValue();
		totalCalculatedPrice = sp.GetTotalPrice(Integer.parseInt(quantity.trim()), unitPrice);
		// String total = "R " + String.format("%,d", totalPrice);
		// THEN print out all the totals, and assert that the cart value does equal to total cart price
		System.out.println("Total calculated price:" + totalCalculatedPrice);
		System.out.println("Cart Summary :" + cartValue);
		totalCartPrice = totalCartPrice + totalCalculatedPrice;
		System.out.println("Total calculated cart summary : " + totalCartPrice);
		String total = "R " + String.format("%,d", totalCartPrice);
		// Validate if the actual cart value is equal to the calculated
		Assert.assertEquals(cartValue.trim(), total);
		String actualShoppingCartMessage = addcart.SpendMoreMessage();
		String expectedShoppingCartMessage = "Spend R450 or more to get FREE DELIVERY. T&Cs apply";
		Assert.assertEquals(actualShoppingCartMessage, expectedShoppingCartMessage);

		sp.closeCurrentBrowserTab();
		hp.GoHome();

	}
}