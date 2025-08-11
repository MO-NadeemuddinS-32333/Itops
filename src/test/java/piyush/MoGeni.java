package piyush;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import zpageobjects.BondsTab;
import zpageobjects.LoginPage;

public class MoGeni {
	WebDriver driver;
	LoginPage loginPage;
	BondsTab bondsTab;
	String websiteurl = "https://uattrade.motilaloswaluat.com/";
	String clientid = "E36905";
	String password = "abc@123";
	String Dob = "AHSPP5619F";
	String Status;

	@BeforeTest
	public void openbrowser() throws IOException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-web-security");
		driver = new ChromeDriver(options);
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
		Thread.sleep(1000);
		loginPage.dob.sendKeys(Dob);
		loginPage.loginbutton.click();

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
	}

	@Test(priority = 2)
	public void chattest() throws IOException, InterruptedException {
		System.out.println("Chat test started");
		String excelPath = "C:\\Users\\nadeemuddinsayed\\Desktop\\somu sir\\generic_faq.xlsx";

		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);
		driver.switchTo().frame("chatBotFrame");
		loginPage.chatbotbutton.click();
		Thread.sleep(2000);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				Cell questionCell = row.getCell(1); // Column B
				if (questionCell != null) {
					String question = questionCell.getStringCellValue();

					loginPage.chatboxtext.clear();
					loginPage.chatboxtext.sendKeys(question);
					Thread.sleep(60000);
					loginPage.chatboxtext.sendKeys(Keys.ENTER);

					Thread.sleep(10000);
					try {
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
						WebElement chatresponse = wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[@id='root']/div/div[1]/div[2]/div[" + (i + 1) + "]")));

						String response = chatresponse.getText();
						System.out.println("Response for row " + i + ": " + response);
						Cell responseCell = row.getCell(3);
						if (responseCell == null) {
							responseCell = row.createCell(3);
						}
						responseCell.setCellValue(response);
					} catch (Exception e) {
						System.out.println("Error capturing text for row " + i + ": " + e.getMessage());
					}
				}
			}
		}

		fis.close();
		FileOutputStream fos = new FileOutputStream(excelPath);
		workbook.write(fos);
		fos.close();
		workbook.close();
	}

}
