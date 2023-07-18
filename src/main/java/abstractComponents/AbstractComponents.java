package abstractComponents;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.AdminUserManagementPage;
import pageObject.EmployeeInformationPage;

public class AbstractComponents {

	WebDriver driver;
	
	@FindBy(css=".oxd-icon.bi-caret-down-fill.oxd-userdropdown-icon")
	WebElement userDropDown;
	@FindBy(linkText="Logout")
	WebElement logoutButton;
	@FindBy(linkText="Admin")
	WebElement adminMenuButton;
	@FindBy(linkText="PIM")
	WebElement pimMenuButton;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void dynamicDropList(List<WebElement> elements, String option) {
		int j=0;
		for(int i=0;i<elements.size();i++)
		{
			String role = elements.get(i).getText();
			if(role.contains(option))
			{
				elements.get(i).click();
				j++;
			}
			if(j==1)
				break;
		}
	}
	
	public void logout() {
		userDropDown.click();
		logoutButton.click();
	}
	public AdminUserManagementPage goToAdmin_UserManagement() {
		adminMenuButton.click();
		AdminUserManagementPage adminUserPage = new AdminUserManagementPage(driver);
		return adminUserPage;
	}
	public EmployeeInformationPage goToPIM_EmployeeInformation() {
		 pimMenuButton.click();
		 EmployeeInformationPage employeeInfoPage = new EmployeeInformationPage(driver);
		 return employeeInfoPage;
	}
	public void uploadFile(String filePath) throws IOException {
		Runtime.getRuntime().exec(filePath);
	}
	public void scrollPage(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
	}
}
