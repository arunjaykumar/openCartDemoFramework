package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjecs.HomePage;
import pageObjecs.LoginPage;
import pageObjecs.MyAccountPage;
import testBase.BaseClass;

public class TC002_UserLoginTest extends BaseClass {
	@Test(groups = { "master", "regression", "sanity" })
	public void verifyUserLogin() throws InterruptedException {
		logger.info("===========TC002_UserLoginTest Start===========");
		HomePage hp = new HomePage(driver);

		hp.clickMyAcc();
		logger.info("Clicked on MyAccount");
		hp.clickLogin();
		logger.info("Clicked on Login");

		LoginPage lp = new LoginPage(driver);
		lp.setUserEmail(prop.getProperty("email"));
		logger.info("Set Email");
		lp.setUserPass(prop.getProperty("pass"));
		logger.info("Set Password");
		lp.clickLogin();
		logger.info("Clicked on Login Button");

		MyAccountPage ap = new MyAccountPage(driver);
		String header_txt = ap.myAccountHeader();
		Assert.assertEquals(header_txt, "My Account", "Login Failed");
		Thread.sleep(2000);
		ap.clickLogout();
		logger.info("Logout successfuly");

		logger.info("===========TC002_UserLoginTest Finished===========");

	}
}
