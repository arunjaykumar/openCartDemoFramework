package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Properties prop;
	public Logger logger;

	TakesScreenshot takeScreenshot;

	@BeforeMethod
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		logger = LogManager.getLogger(this.getClass());

// Load config file
		FileReader file = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
		prop = new Properties();
		prop.load(file);

// GRID setup
		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();

			// OS setup
			if (os.equalsIgnoreCase("window")) {
				cap.setPlatform(Platform.WIN11);

			} else if (os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);

			} else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			} else {
				System.out.println("Os not matching");
				return;
			}
			// Browser setup
			switch (br.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "Firefox":
				cap.setBrowserName("firefox");
				break;
			case "Microsoftedge":
				cap.setBrowserName("Microsoftedge");
				break;
			}
//			URL url = new URL("http://192.168.0.101:4444");
			URL url = new URL("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(url, cap);

		}
		// Environment setup
		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			// Browser setup
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;

			case "edge":
				driver = new EdgeDriver();
				break;

			case "firefox":
				driver = new FirefoxDriver();
				break;

			default:
				throw new RuntimeException("Invalid browser name: " + br);
			}

		}
		logger.info("Browser launched: " + br);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("URL"));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			logger.info("Browser closed.");
		}
	}

// Screenshot Method
	public String captureScreen(String testName) {
		try {
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

			if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
				takeScreenshot = ((RemoteWebDriver) driver);
			} else {
				takeScreenshot = (TakesScreenshot) driver;
			}
			File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

			FileUtils.copyFile(source, new File(path));
			return path;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	public String randomNum() {
		return RandomStringUtils.randomAlphanumeric(5);
	}
}
