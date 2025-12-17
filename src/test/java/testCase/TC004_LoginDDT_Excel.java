
package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjecs.HomePage;
import pageObjecs.LoginPage;
import pageObjecs.MyAccountPage;
import testBase.BaseClass;
import utility.ExcelDataProvider;

public class TC004_LoginDDT_Excel extends BaseClass {

	@Test(dataProvider = "loginData", dataProviderClass = ExcelDataProvider.class)
	public void loginDDT(String email, String pass, String expected) {

//		logger.info("========== Start TC004_LoginDDT_Excel iteration ==========");

		HomePage hp = new HomePage(driver);
		hp.clickMyAcc();
		hp.clickLogin();

		LoginPage lp = new LoginPage(driver);
		lp.setUserEmail(email);
		lp.setUserPass(pass);
		lp.clickLogin();

		MyAccountPage map = new MyAccountPage(driver);
		String headertxt = "";
		try {
			headertxt = map.myAccountHeader(); // Method should return header text safely
		} catch (Exception e) {
			logger.warn("Header not found – likely invalid login.");
		}

		if (expected.equalsIgnoreCase("valid")) {
			if (headertxt.equalsIgnoreCase("My Account")) {
				logger.info("Login successful as expected for VALID user: " + email);
				Assert.assertTrue(true);
				// Logout only if login was successful
				map.clickLogout();
			} else {
				logger.error(" Login failed unexpectedly for VALID user: " + email);
				Assert.fail("Expected valid login but failed.");
			}
		} else if (expected.equalsIgnoreCase("invalid")) {
			if (headertxt.equalsIgnoreCase("My Account")) {
				logger.error(" Login succeeded unexpectedly for INVALID user: " + email);
				// Logout immediately so next iteration runs clean
				map.clickLogout();
				Assert.fail("Expected login failure but login succeeded.");
			} else {
				logger.info("Login failed as expected for INVALID user: " + email);
				Assert.assertTrue(true);
			}
		} else {
			logger.warn("Unexpected 'exp' value in test data: " + expected);
			Assert.fail("Unexpected 'exp' value in Excel.");
		}

		logger.info("========== Completed TC004_LoginDDT_Excel iteration ==========\n");

		logger.error("Exception in DDT test: ");
		Assert.fail("Exception during DDT iteration: ");
	}
}
