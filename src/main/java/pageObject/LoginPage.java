package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{

	WebDriver driver;
	@FindBy(name="username")
	WebElement userName;
	@FindBy(name="password")
	WebElement userPassword;
	@FindBy(css=".oxd-button")
	WebElement loginButton;
	@FindBy(css=".oxd-layout")
	WebElement dashboardPageElement;
	@FindBy(css=".oxd-text.oxd-text--p.oxd-alert-content-text")
	WebElement invalidCredentialsAlert;
	@FindBy(xpath="//div[1][@class=\"oxd-form-row\"]/div/span")
	WebElement requiredUserNameAlert;
	@FindBy(xpath="//div[2][@class=\"oxd-form-row\"]/div/span")
	WebElement requiredPasswordAlert;
	@FindBy(xpath="//div[@class='orangehrm-login-forgot']/p")
	WebElement forgotPasswordButton;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToPage(String url) {
		driver.get(url);
	}
	public DashboardPage loginApplication(String user, String password) {
		userName.sendKeys(user);
		userPassword.sendKeys(password);
		loginButton.click();
		DashboardPage dashboardPage = new DashboardPage(driver);
		return dashboardPage;
	}
	public WebElement getDashboardPage() {
		waitForWebElementToAppear(dashboardPageElement);
		return dashboardPageElement;
	}
	public WebElement getInvalidCredentialsAlert() {
		waitForWebElementToAppear(invalidCredentialsAlert);
		return invalidCredentialsAlert;
	}
	public WebElement getRequiredUserNameAlert() {
		waitForWebElementToAppear(requiredUserNameAlert);
		return requiredUserNameAlert;
	}
	public WebElement getRequiredPasswordAlert() {
		waitForWebElementToAppear(requiredPasswordAlert);
		return requiredPasswordAlert;
	}
	public void clickLoginButton() {
		waitForWebElementToAppear(loginButton);
		loginButton.click();
	}
	public ResetPasswordPage clickForgotPasswordButton() {
		waitForWebElementToAppear(forgotPasswordButton);
		forgotPasswordButton.click();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
		return resetPasswordPage;
	}
	
}
