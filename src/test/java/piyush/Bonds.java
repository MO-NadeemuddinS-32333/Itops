package piyush;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import zpageobjects.BondsTab;
import zpageobjects.LoginPage;

public class Bonds {
	WebDriver driver;
	LoginPage loginPage;
	BondsTab bondsTab;
	TableLogger logger = new TableLogger();
	String websiteurl = "https://invest.motilaloswal.com/";
	String clientid = "9819635650";
	String password = "Aug@2025$";
	String Status;
	int otpwait = 40000;

	@BeforeTest
	public void openbrowser() throws IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\Mo Trading Automation\\Itops\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(websiteurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		loginPage = new LoginPage(driver);
		bondsTab = new BondsTab(driver);

		System.out.println("Browser launched successfully");
	}

	@Test(priority = 1)
	public void Login() throws InterruptedException {
		loginPage.userid.sendKeys(clientid);
		loginPage.password.sendKeys(password);
		loginPage.loginButton.click();
		System.out.println("Enter OTP");
		Thread.sleep(otpwait);
		try {
			if (loginPage.cancelpushnotification.isDisplayed()) {
				loginPage.cancelpushnotification.click();
			}
		} catch (Exception e) {
			System.out.println("Push notification not displayed");
		}
		try {
			if (loginPage.RDD.isDisplayed()) {
				loginPage.riskDisclosureCheckbox.click();
				loginPage.Rddok.click();
			}
		} catch (Exception e) {
			System.out.println("Risk Disclosure Document is not displayed");
		}
		loginPage.bondsTab.click();
	}

	@Test(priority = 2)
	public void mainMethod() throws InterruptedException {
		logger.logTableStart("Execution Report");

		Bondstab();
		corporatebonds();
		Selectanybond();
		// investnow();
		repaymentschedule();
		downloadmemorandum();
		downloadratingrationale();
		viewallfaq();
		governmentsecurites();
		fiftyfourecbond();
		selectfiftyfourecbond();
		clicksubmitbutton();
		downloadissuehighlights();
		downloadinformation();
		checkstatus();

		ncdpublicissue();

		logger.logTableEnd();

	}

	public void Bondstab() {
		String originalWindow = driver.getWindowHandle();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(originalWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		long startTime = System.currentTimeMillis();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(bondsTab.investTab));
			bondsTab.investTab.isDisplayed();
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Select BONDS Tab \\u2192 Invest" + "[https://bonds.motilaloswal.com/]", Status,
					endTime - startTime);
		}
		System.out.println("New Tab Title: " + driver.getTitle());
	}

	public void corporatebonds() throws InterruptedException {

		bondsTab.investTab.click();
		bondsTab.corporatebonds.click();
		long startTime = System.currentTimeMillis();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(bondsTab.corporteBondsYield));
			bondsTab.corporteBondsYield.isDisplayed();
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Corporate bonds", Status, endTime - startTime);
		}
	}

	public void Selectanybond() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(5000);
		long startTime = System.currentTimeMillis();
		try {
			bondsTab.corporateBondsinvestButton.click();
			wait.until(ExpectedConditions.visibilityOf(bondsTab.securitydiscription));
			bondsTab.securitydiscription.isDisplayed();
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Corporate bonds Select any bond", Status, endTime - startTime);
		}
	}

	public void investnow() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(bondsTab.corporateBondsinvestButton));
		bondsTab.investNowButton.click();
		Thread.sleep(1000);
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(bondsTab.investreview));
			bondsTab.investreview.isDisplayed();
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			// driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Corporate bonds Invest now", Status, endTime - startTime);
		}
	}

	public void repaymentschedule() {
		/*
		 * bondsTab.investTab.click(); bondsTab.corporatebonds.click();
		 * bondsTab.corporateBondsinvestButton.click();
		 */
		bondsTab.repaymentschedule.click();
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,600)");

			wait.until(ExpectedConditions.visibilityOf(bondsTab.year));
			bondsTab.year.isDisplayed();
			bondsTab.paymentschedule.isDisplayed();
			bondsTab.interestpayout.isDisplayed();
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			bondsTab.repyamentclosebutton.click();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Corporate bonds Repayment Schedule year, payment schedule, interest", Status,
					endTime - startTime);
		}
	}

	public void downloadmemorandum() throws InterruptedException {
		Thread.sleep(1000);
		String pdfWindow = driver.getWindowHandle();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		bondsTab.downloadmemorandum.click();
		Thread.sleep(1000);
		Set<String> windowHandles = driver.getWindowHandles();
		String newWindow = null;
		for (String handle : windowHandles) {
			if (!handle.equals(pdfWindow)) {
				newWindow = handle;
				driver.switchTo().window(handle);
				break;
			}
		}
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
			String title = driver.getTitle();
			title.equals("MO Investor");
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			driver.close();
			driver.switchTo().window(pdfWindow);
			long endTime = System.currentTimeMillis();
			System.out.println(newWindow);
			logger.logTableRow("Corporate bonds Download  \u2192 Information Memorandum", Status, endTime - startTime);
		}
	}

	public void downloadratingrationale() throws InterruptedException {
		String ratingwindow = driver.getWindowHandle();
		bondsTab.ratingRationale.click();
		Thread.sleep(1000);
		Set<String> windowHandles = driver.getWindowHandles();
		String newWindow = null;
		for (String handle : windowHandles) {
			if (!handle.equals(ratingwindow)) {
				newWindow = handle;
				driver.switchTo().window(handle);
				break;
			}
		}
		long startTime = System.currentTimeMillis();
		try {
			String title = driver.getTitle();
			Thread.sleep(1000);
			title.equals("MO Investor");
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			driver.close();
			driver.switchTo().window(ratingwindow);
			long endTime = System.currentTimeMillis();
			System.out.println(newWindow);
			logger.logTableRow("Corporate bonds Download \u2192 Rating Rationale", Status, endTime - startTime);
		}

	}

	public void viewallfaq() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1300)");
		bondsTab.viewallfaq.click();
		Thread.sleep(1000);
		long startTime = System.currentTimeMillis();
		try {
			bondsTab.frequentlyAskedQuestions.isDisplayed();
			Thread.sleep(1000);
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			driver.navigate().back();
			logger.logTableRow("Corporate bonds View all FAQs", Status, endTime - startTime);

		}

	}

	public void governmentsecurites() throws InterruptedException {
		bondsTab.investTab.click();
		bondsTab.governmentsecuties.click();
		Thread.sleep(1000);
		long startTime = System.currentTimeMillis();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(bondsTab.corporteBondsYield));
			bondsTab.corporteBondsYield.isDisplayed();
			Thread.sleep(2000);
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Government Securities webpage redirection", Status, endTime - startTime);
		}
	}

	public void fiftyfourecbond() throws InterruptedException {
		bondsTab.investTab.click();
		bondsTab.fiftyfourECcapitalgainbond.click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		long startTime = System.currentTimeMillis();
		try {
			bondsTab.fiftyfourbondsublitButton.isDisplayed();
			Thread.sleep(1000);
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("54 EC bonds Select any bond", Status, endTime - startTime);
		}
	}

	public void selectfiftyfourecbond() throws InterruptedException {
		bondsTab.investTab.click();
		bondsTab.fiftyfourECcapitalgainbond.click();
		Thread.sleep(1000);
		bondsTab.investbuttonecbonds.click();
		Thread.sleep(1000);
		long startTime = System.currentTimeMillis();
		try {
			bondsTab.recCapitalGainTaxExemptionBonds.isDisplayed();
			Thread.sleep(1000);
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("54 EC bonds Invest now", Status, endTime - startTime);
		}
	}

	public void clicksubmitbutton() throws InterruptedException {
		String awindow = driver.getWindowHandle();
		bondsTab.investTab.click();
		bondsTab.fiftyfourECcapitalgainbond.click();
		Thread.sleep(1000);
		bondsTab.investbuttonecbonds.click();
		Thread.sleep(1000);
		bondsTab.fiftyfourbondsublitButton.click();
		Thread.sleep(1000);
		Set<String> windowHandles = driver.getWindowHandles();
		String newWindow = null;
		for (String handle : windowHandles) {
			if (!handle.equals(awindow)) {
				newWindow = handle;
				driver.switchTo().window(handle);
				break;
			}
		}
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
			String title = driver.getTitle();
			System.out.println("clicksubmitbutton()" + title);
			title.equals("MO Investor");
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			driver.close();
			driver.switchTo().window(awindow);
			long endTime = System.currentTimeMillis();
			System.out.println(newWindow);
			logger.logTableRow("54 EC bonds Click on submit button", Status, endTime - startTime);

		}
	}

	public void downloadissuehighlights() throws InterruptedException {
		String mWindow = driver.getWindowHandle();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		bondsTab.issuehighlightsdownload.click();
		Thread.sleep(1000);

		Set<String> windowHandles = driver.getWindowHandles();
		String newWindow = null;
		for (String handle : windowHandles) {
			if (!handle.equals(mWindow)) {
				newWindow = handle;
				driver.switchTo().window(handle);
				break;
			}
		}
		long startTime = System.currentTimeMillis();
		try {
			String downloadissuetitle = driver.getTitle();
			Thread.sleep(1000);
			System.out.println("downloadissuehighlights()" + downloadissuetitle);
			downloadissuetitle.equals("MO Investor");
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			driver.close();
			driver.switchTo().window(mWindow);
			long endTime = System.currentTimeMillis();
			System.out.println(newWindow);
			logger.logTableRow("54 EC bonds issue highlights pdf download", Status, endTime - startTime);

		}

	}

	public void downloadinformation() {
		String informationWindow = driver.getWindowHandle();
		bondsTab.informationdownload.click();
		Set<String> windowHandles = driver.getWindowHandles();
		String newWindow = null;
		for (String handle : windowHandles) {
			if (!handle.equals(informationWindow)) {
				newWindow = handle;
				driver.switchTo().window(handle);
				break;
			}
		}
		long startTime = System.currentTimeMillis();
		try {
			String downloadinformation = driver.getTitle();
			Thread.sleep(1000);
			System.out.println("downloadinformation()" + downloadinformation);
			downloadinformation.equals("MO Investor");
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			driver.close();
			driver.switchTo().window(informationWindow);
			long endTime = System.currentTimeMillis();
			System.out.println(newWindow);
			logger.logTableRow("54 EC bonds information pdf download", Status, endTime - startTime);

		}
	}

	public void checkstatus() throws InterruptedException {
		String statusWindow = driver.getWindowHandle();
		bondsTab.checkStatus.click();
		Thread.sleep(1000);

		Set<String> windowHandles = driver.getWindowHandles();
		String newWindow = null;
		for (String handle : windowHandles) {
			if (!handle.equals(statusWindow)) {
				newWindow = handle;
				driver.switchTo().window(handle);
				break;
			}
		}
		long startTime = System.currentTimeMillis();
		try {
			String kfin = driver.getTitle();
			System.out.println("checkstatus()" + kfin);
			kfin.equals("MO Investor");
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			driver.close();
			driver.switchTo().window(statusWindow);
			long endTime = System.currentTimeMillis();
			System.out.println(newWindow);
			logger.logTableRow("54 EC bonds Click on check status button", Status, endTime - startTime);

		}

	}

	public void ncdpublicissue() {
		bondsTab.investTab.click();
		bondsTab.ncdPublicIssues.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		long startTime = System.currentTimeMillis();
		try {
			bondsTab.applynowbutton.click();
			bondsTab.ncdIPO.isDisplayed();
			Status = "Pass";
		} catch (Exception e) {
			Status = "Fail";
		} finally {
			driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("NCD public issues webpage redirection", Status, endTime - startTime);
		}
	}

	// Helper Methods for Logging Tables
	public class TableLogger {
		private int rowCounter = 0; // To keep track of the serial number

		// Start the table with a title
		public void logTableStart(String tableName) {
			Reporter.log("<h3>" + tableName + "</h3>", true);
			Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
			Reporter.log("<tr><th>Sr. No</th><th>Test Case</th><th>Status</th><th>Time Taken (ms)</th></tr>", true);
		}

		// Add a row to the table
		public void logTableRow(String testCase, String status, long timeTaken) {
			rowCounter++; // Increment the serial number

			// Define the color and text styles based on the status
			String statusColor = "";
			String statusTextStyle = "color: white; font-weight: bold;";

			if ("Fail".equalsIgnoreCase(status)) {
				statusColor = "background-color: red;";
			} else if ("Pass".equalsIgnoreCase(status)) {
				statusColor = "background-color: green;";
			}

			// Create the row with the styled status cell
			Reporter.log("<tr><td>" + rowCounter + "</td><td>" + testCase + "</td><td style='" + statusColor
					+ statusTextStyle + "'>" + status + "</td><td>" + timeTaken + "</td></tr>", true);
		}

		// End the table
		public void logTableEnd() {
			Reporter.log("</table>", true);
		}

	}

	@AfterTest
	public void closebrowser() {
		if (driver != null) {
			// driver.quit();
			System.out.println("Browser closed.");
		}
	}
}
