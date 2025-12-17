package pageObjecs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locator
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement user_email;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement user_pass;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement user_login;

	// Actions
	public void setUserEmail(String email) {
		user_email.sendKeys(email);
	}

	public void setUserPass(String pass) {
		user_pass.sendKeys(pass);
	}

	public void clickLogin() {
		user_login.click();
	}
}
