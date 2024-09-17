package ECommerceAutomation.pagobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ECommerceAutomation.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class='cartSection'] h3")
	private List<WebElement> productsList;

	@FindBy(css = "[class='totalRow'] button")
	WebElement checkOut;

	public Boolean VerifyProductDisplay(String productname) {
		Boolean match = productsList.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productname));
		return match;
	}

	public CheckOutPage goToCheckOut() {
		checkOut.click();
		return new CheckOutPage(driver);

	}

}
