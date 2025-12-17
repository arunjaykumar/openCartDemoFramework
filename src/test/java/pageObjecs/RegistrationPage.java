package pageObjecs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	WebDriver driver;

	// Constructor
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement fname_txt;
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lname_txt;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement email_txt;
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement phone_txt;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement pass_txt;
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement pass_cnfrm;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement check_box;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continew_btn;

	@FindBy(xpath = "\r\n" + "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement reg_confirm;

	// Actions
	public void setFname(String fname) {
		fname_txt.sendKeys(fname);
	}

	public void setLname(String lname) {
		lname_txt.sendKeys(lname);
	}

	public void setEmail(String email) {
		email_txt.sendKeys(email);
	}

	public void setPhone(String phone) {
		phone_txt.sendKeys(phone);
	}

	public void setPassword(String pass) {
		pass_txt.sendKeys(pass);
	}

	public void confirmPass(String con_pass) {
		pass_cnfrm.sendKeys(con_pass);
	}

	public void clickCheckBox() {
		check_box.click();
		;
	}

	public void clickContinew() {
		continew_btn.click();

//		continew_btn.submit();
//		or
//		Actions action = new Actions(driver);
//		action.moveToElement(continew_btn);
//		or
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("argument[0].click()", continew_btn);
//		or
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.elementToBeClickable(continew_btn)).click();

	}

	public String getConfirmationMsj() {
		try {
			return reg_confirm.getText();
		} catch (Exception e) {
			return (e.getMessage());
		}

	}

}
