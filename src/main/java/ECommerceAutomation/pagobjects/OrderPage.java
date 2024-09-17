package ECommerceAutomation.pagobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ECommerceAutomation.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> cartProducts;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyOrderDisplay(String productname) {
		Boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productname));
		return match;
	}

}
