package pageObjecs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
	WebDriver driver;

	// constructor
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement header;
	@FindBy(xpath = "\r\n" + "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout_btn;

	public String myAccountHeader() {
		return header.getText();

	}

	public void clickLogout() {
		logout_btn.click();
	}
}
