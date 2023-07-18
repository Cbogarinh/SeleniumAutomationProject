package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class AddUserPage extends AbstractComponents{
	
	WebDriver driver;
	@FindBy(xpath="//div[@class='oxd-form-row']/div/div[1]/div/div[2]/div/div/div[2]/i")
	WebElement userRoleDropList;
	@FindBy(xpath="//div[@class='oxd-select-option']")
	List<WebElement> roleOptions;
	@FindBy(xpath="//div[@class=\"oxd-form-row\"]/div/div[2]/div/div[2]/div/div/input")
	WebElement employeeNameBox;
	@FindBy(xpath="//div[@class='oxd-autocomplete-option']")
	List<WebElement> namesList;
	@FindBy(xpath="//div[@class=\"oxd-form-row\"]/div/div[3]/div/div[2]")
	WebElement statusDropList;
	@FindBy(xpath="//div[@class='oxd-select-option']")
	List<WebElement> statusOptions;
	@FindBy(xpath="//div[@class=\"oxd-form-row\"]/div/div[4]/div/div[2]/input")
	WebElement userName;
	@FindBy(xpath="//div[@class=\"oxd-form-row user-password-row\"]/div/div[1]/div/div[2]/input")
	WebElement userPassword;
	@FindBy(xpath="//div[@class=\"oxd-form-row user-password-row\"]/div/div[2]/div/div[2]/input")
	WebElement confirmPassword;
	@FindBy(xpath="//div[@class='oxd-form-actions']/button[2]")
	WebElement saveButton;
	@FindBy(id="oxd-toaster_1")
	WebElement successToast;
	
			
	
	
	public AddUserPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addUser(String name, String user, String userRole, String userStatus, String newPassword) throws InterruptedException {
		waitForWebElementToAppear(userRoleDropList);
		userRoleDropList.click();
		dynamicDropList(roleOptions, userRole);
		employeeNameBox.sendKeys(name);
		Thread.sleep(2000);
		dynamicDropList(namesList, name);
		statusDropList.click();
		dynamicDropList(statusOptions, userStatus);
		userName.sendKeys(user);
		userPassword.sendKeys(newPassword);
		confirmPassword.sendKeys(newPassword);
		saveButton.click();
	}
	
	public WebElement getSuccessToast() {
		waitForWebElementToAppear(successToast);
		return successToast;
	}

}
