package zpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='btnVerify2FAPanDob']//img[@class='ml10']")
	public WebElement loginbutton;
	
	@FindBy(xpath = "//div[@class='custom-element']//p[1]")
	public WebElement chatResponse;
	
	@FindBy(xpath = "//input[@placeholder='Type your message here...']")
	public WebElement chatboxtext;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/img[1]")
	public WebElement chatbotbutton;
	
	@FindBy(id = "Login2FAPanDOB")
	public WebElement dob;
	
	@FindBy(xpath = "//*[@id=\"app\"]/main/section/div/section[1]/div[2]/div/div[1]/div/div[2]/div[2]/button")
	public WebElement applynowbutton;
	
	@FindBy(id = "wzrk-cancel")
	public WebElement cancelpushnotification;

	@FindBy(id = "liBonds")
	public WebElement bondsTab;

	@FindBy(id = "btnLogRiskDisc")
	public WebElement Rddok;

	@FindBy(xpath = "//*[@id=\"dvDNDRiskDisc\"]/div/label/span")
	public WebElement riskDisclosureCheckbox;

	@FindBy(xpath = "//*[@id=\"riskDiscModal\"]/div/div/div/div[1]/h5")
	public WebElement RDD;

	@FindBy(xpath = "//*[@id=\"LoginId\"]")
	public WebElement userid;

	@FindBy(xpath = "//*[@id=\"MainPassword\"]")
	public WebElement password;

	@FindBy(xpath = "//*[@id=\"btnLoginInv\"]")
	public WebElement loginButton;

}
