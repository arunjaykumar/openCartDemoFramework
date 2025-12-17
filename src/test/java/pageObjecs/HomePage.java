package pageObjecs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Locators
	@FindBy(xpath = "//a[@title='My Account']")
	WebElement my_account;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registration;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement login;

	// Actions
	public void clickMyAcc() {
		my_account.click();
	}

	public void clickRegistration() {
		registration.click();
	}

	public void clickLogin() {
		login.click();
	}

}
