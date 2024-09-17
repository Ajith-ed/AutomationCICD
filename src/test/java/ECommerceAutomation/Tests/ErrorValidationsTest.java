package ECommerceAutomation.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ECommerceAutomation.TestComponents.BaseTest;
import ECommerceAutomation.TestComponents.Retry;
import ECommerceAutomation.pagobjects.CartPage;
import ECommerceAutomation.pagobjects.ProductCatalogs;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void InvalidLoginErrorValidation() throws IOException, InterruptedException {

		landingPage.loginApplication("ajaykrish@gmail.com", "Ajay@35");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productname = "ZARA COAT 3";

		ProductCatalogs productcatalog = landingPage.loginApplication("arunkrish@gmail.com", "Arun@1235");
		List<WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(productname);
		// we can access the method by using child class as well since we are using the
		// inheritance
		CartPage cartpage = productcatalog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
