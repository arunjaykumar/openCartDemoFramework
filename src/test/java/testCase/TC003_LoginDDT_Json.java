package testCase;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjecs.HomePage;
import pageObjecs.LoginPage;
import pageObjecs.MyAccountPage;
import testBase.BaseClass;
import utility.JsonDataProvider;

public class TC003_LoginDDT_Json extends BaseClass {

	@Test(dataProvider = "loginJsonData", dataProviderClass = JsonDataProvider.class)
	public void loginDDT(Map<String, String> data) {

		String email = data.get("username");
		String pass = data.get("password");
		String exp_result = data.get("expected");
		try {
			logger.info("========== Start TC003_LoginDDT iteration ==========");
			logger.info("Test Data --> Email: " + email + " | Password: " + pass + " | Expected: " + exp_result);

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

			exp_result = exp_result.trim(); // Prevent mismatch due to spaces

			if (exp_result.equalsIgnoreCase("valid")) {
				if (headertxt.equalsIgnoreCase("My Account")) {
					logger.info("Login successful as expected for VALID user: " + email);
					Assert.assertTrue(true);
					// Logout only if login was successful
					map.clickLogout();
				} else {
					logger.error(" Login failed unexpectedly for VALID user: " + email);
					Assert.fail("Expected valid login but failed.");
				}
			} else if (exp_result.equalsIgnoreCase("invalid")) {
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
				logger.warn("Unexpected 'exp' value in test data: " + exp_result);
				Assert.fail("Unexpected 'exp' value in Excel.");
			}

			logger.info("========== Completed TC003_LoginDDT iteration ==========\n");

		} catch (Exception e) {
			logger.error("Exception in DDT test: " + e.getMessage(), e);
			Assert.fail("Exception during DDT iteration: " + e.getMessage());
		}
	}

}
