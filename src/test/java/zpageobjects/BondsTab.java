package zpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BondsTab {
	WebDriver driver;

	public BondsTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(xpath = "//a[text()=\"Check Status\"]")
	public WebElement checkStatus;

	@FindBy(xpath = "(//span[text()=\"Download\"])[2]")
	public WebElement informationdownload;

	@FindBy(xpath = "//span[text()=\"Download\"]")
	public WebElement issuehighlightsdownload;

	@FindBy(xpath = "//span[text()=\"Review and Confirm\"]")
	public WebElement investreview;

	@FindBy(xpath = "(//span[text()=\"Submit\"])[2]")
	public WebElement fiftyfourbondsublitButton;

	@FindBy(xpath = "//span[text()=\"Please keep the following documents handy for KYC Upload\"]")
	public WebElement recCapitalGainTaxExemptionBonds;

	@FindBy(xpath = "//span[text()=\"Frequently Asked Questions \"]")
	public WebElement frequentlyAskedQuestions;

	@FindBy(xpath = "//span[text()=\"View All FAQ\"]")
	public WebElement viewallfaq;

	@FindBy(xpath = "//span[text()=\"Rating Rationale\"]")
	public WebElement ratingRationale;

	@FindBy(xpath = "//span[text()=\"Information Memorandum\"]")
	public WebElement downloadmemorandum;

	@FindBy(xpath = "//*[@id=\"repayment\"]/div/div/div[1]/button/img")
	public WebElement repyamentclosebutton;

	@FindBy(xpath = "//div[contains(text(), 'Interest Payout')]")
	public WebElement interestpayout;

	@FindBy(xpath = "//div[text()=\"Payment Schedule\"]")
	public WebElement paymentschedule;

	@FindBy(xpath = "//div[text()=\"Year\"]")
	public WebElement year;

	@FindBy(xpath = "//span[text()=\"Repayment Schedule\"]")
	public WebElement repaymentschedule;

	@FindBy(xpath = "//button[text()=\"INVEST NOW\"]")
	public WebElement investNowButton;

	@FindBy(xpath = "//div[text()='Security Description']")
	public WebElement securitydiscription;

	@FindBy(xpath = "//button[text()=\"INVEST NOW\"]")
	public WebElement corporateBondsinvestButton;

	@FindBy(xpath = "//a[normalize-space()='NCD IPO']")
	public WebElement ncdIPO;

	@FindBy(xpath = "//*[@id=\"app\"]/main/section/div/section[1]/div[2]/div/div[1]/div/div[2]/div[2]/button")
	public WebElement applynowbutton;

	@FindBy(xpath = "//div[normalize-space()='NCD Public Issues']")
	public WebElement ncdPublicIssues;

	@FindBy(xpath = "//*[@id=\"app\"]/main/section/div/div[1]/section/div/div[2]/div[4]/div/div[2]/p")
	public WebElement hoverelment;

	@FindBy(xpath = "//div[@id='CGBSlickSlider']//div[1]//div[1]//div[4]//button[1]")
	public WebElement investbuttonecbonds;

	@FindBy(xpath = "//*[@id=\"invest\"]/div/div/a[3]/div[2]/div")
	public WebElement fiftyfourECcapitalgainbond;

	@FindBy(xpath = "//*[@id=\"invest\"]/div/div/a[2]/div[2]/div")
	public WebElement governmentsecuties;

	@FindBy(id = "f-yield")
	public WebElement corporteBondsYield;

	@FindBy(xpath = "//*[@id=\"app\"]/header/div/div[1]/nav/div/div[1]/button")
	public WebElement investTab;

	@FindBy(xpath = "//*[@id=\"invest\"]/div/div/a[1]/div[2]/div")
	public WebElement corporatebonds;
}
