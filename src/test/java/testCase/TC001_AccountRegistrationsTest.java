package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjecs.HomePage;
import pageObjecs.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationsTest extends BaseClass {

	@Test(groups = { "Master", "Sanity" })
	public void verify_acc_registration() {

		logger.info("============= TC001_AccountRegistration Test Started ===============");

		try {
			logger.info("Navigating to Home Page");

			HomePage hp = new HomePage(driver);
			hp.clickMyAcc();
			logger.info("Clicked on 'My Account' link");

			hp.clickRegistration();
			logger.info("Clicked on 'Registration' link");

			logger.info("Filling registration form with random user data");
			RegistrationPage rp = new RegistrationPage(driver);
			rp.setFname(randomString());
			logger.info("Entered First Name");

			rp.setLname(randomString());
			logger.info("Entered Last Name");

			rp.setEmail(randomString() + "@gmail.com");
			logger.info("Entered Email Address");

			rp.setPhone(randomNum());
			logger.info("Entered Phone Number");

			String password = randomString() + randomNum();
			rp.setPassword(password);
			logger.info("Entered Password");

			rp.confirmPass(password);
			logger.info("Confirmed Password");

			rp.clickCheckBox();
			logger.info("Clicked on Privacy Policy Checkbox");

			rp.clickContinew();
			logger.info("Clicked on Continue button");

			logger.info("Validating confirmation message...");
			String cnfrm_msj_txt = rp.getConfirmationMsj();
			Assert.assertEquals(cnfrm_msj_txt, "Your Account Has Been Created!");
			logger.info("Account Registration Test Passed — Account created successfully.");

		} catch (AssertionError ae) {
			logger.error("Assertion Failed: " + ae.getMessage());
			logger.error("Account Registration Test Failed due to assertion error.");
			throw ae;
		} catch (Exception e) {
			logger.error("Exception occurred during Account Registration Test: " + e.getMessage());
			logger.error("Account Registration Test Failed due to exception.");
			throw e;
		} finally {
			logger.info("============= TC001_AccountRegistration Test Completed ===============");
		}
	}
}
