package ECommerceAutomation.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ECommerceAutomation.TestComponents.BaseTest;
import ECommerceAutomation.pagobjects.CartPage;
import ECommerceAutomation.pagobjects.CheckOutPage;
import ECommerceAutomation.pagobjects.ConfirmationPage;
import ECommerceAutomation.pagobjects.LandingPage;
import ECommerceAutomation.pagobjects.ProductCatalogs;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogs productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
		//code
	}

	
	@Given("Logged in with username {string} and password {string}")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	
	@When("I add product {string} to Cart")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("Checkout {string} and submit the order")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.SelectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    @Then("{string} message is displayed")
    public void something_message_is_displayed(String strArg1)
    {
    	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    	driver.close();
    }

}
