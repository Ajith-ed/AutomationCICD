package ECommerceAutomation.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ECommerceAutomation.TestComponents.BaseTest;
import ECommerceAutomation.pagobjects.CartPage;
import ECommerceAutomation.pagobjects.CheckOutPage;
import ECommerceAutomation.pagobjects.ConfirmationPage;
import ECommerceAutomation.pagobjects.OrderPage;
import ECommerceAutomation.pagobjects.ProductCatalogs;

public class SubmitOrderTest extends BaseTest {
	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitorder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogs productcatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(input.get("product"));
		// we can access the method by using child class as well since we are using the
		// inheritance
		CartPage cartpage = productcatalog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckOutPage checkoutPage = cartpage.goToCheckOut();
		checkoutPage.SelectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Success");

	}

	// To verify the product ZARA COAT 3 is displaying in orders page
	@Test(dependsOnMethods = { "submitorder" })
	public void OrderHistoryTest() {
		ProductCatalogs productcatalog = landingPage.loginApplication("ajaykrish@gmail.com", "Ajay@1235");
		OrderPage orderpage = productcatalog.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productname));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\ECommerceAutomation\\data\\Purchaseorder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };

	}
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "ajaykrish@gmail.com");
//	map.put("password", "Ajay@1235");
//	map.put("product", "ZARA COAT 3");
//
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "arunkrish@gmail.com");
//	map1.put("password", "Arun@1235");
//	map1.put("product", "ADIDAS ORIGINAL");
//
//	HashMap<String, String> map2 = new HashMap<String, String>();
//	map2.put("email", "rajkumar12@gmail.com");
//	map2.put("password", "Raj@1235");
//	map2.put("product", "IPHONE 13 PRO");

}
