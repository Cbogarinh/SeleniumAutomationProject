package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class ResetPasswordPage extends AbstractComponents {

	WebDriver driver;
	
	@FindBy(css=".orangehrm-card-container")
	WebElement resetPasswordCard;
	@FindBy(name="username")
	WebElement userName;
	@FindBy(xpath="//button[2]")
	WebElement resetPasswordButton;
	@FindBy(xpath="//div[@class=\"orangehrm-card-container\"]/h6")
	WebElement resetPasswordSuccessfullyAlert;
	@FindBy(xpath="//div[@class=\"oxd-form-row\"]/div/span")
	WebElement requiredUserAlert;
	
	public ResetPasswordPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void resetPassword(String user) {
		waitForWebElementToAppear(resetPasswordCard);
		userName.sendKeys(user);
		resetPasswordButton.click();
	}
	public WebElement getResetPasswordSucessfullyAlert() {
		waitForWebElementToAppear(resetPasswordSuccessfullyAlert);
		return resetPasswordSuccessfullyAlert;
	}
	public void resetPasswordButtonClick() {
		resetPasswordButton.click();
	}
	public WebElement getRequiredAlert() {
		waitForWebElementToAppear(requiredUserAlert);
		return requiredUserAlert;
	}
}
