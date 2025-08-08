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

import com.google.gson.annotations.Until;

import zpageobjects.BondsTab;
import zpageobjects.LoginPage;

public class MoGenie {
	WebDriver driver;
	LoginPage loginPage;
	BondsTab bondsTab;
//	TableLogger logger = new TableLogger();
	String websiteurl = "https://uattrade.motilaloswaluat.com/";
	String clientid = "E36905";
	String password = "abc@123";
	String Dob = "AHSPP5619F";
	String Status;

	@BeforeTest
	public void openbrowser() throws IOException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Mo Trading Automation\\Itops\\chromedriver.exe");

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
		loginPage.dob.sendKeys("AHSPP5619F");
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
					Thread.sleep(8000);
					loginPage.chatboxtext.sendKeys(Keys.ENTER);

					Thread.sleep(3000);
					
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
                    WebElement chatresponse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='custom-element']//p[1]")));

					String response = chatresponse.getText();


					Cell responseCell = row.getCell(3);
					if (responseCell == null) {
						responseCell = row.createCell(3);
					}
					responseCell.setCellValue(response);
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
