package ECommerceAutomation.pagobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ECommerceAutomation.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// pageFactory

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement Password;

	@FindBy(css = "[type='submit']")
	WebElement login;

	// .ng-tns-c4-0.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogs loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		Password.sendKeys(password);
		login.click();
		ProductCatalogs productcatalog = new ProductCatalogs(driver);
		return productcatalog;

	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
