package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class AdminUserManagementPage extends AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@class=\"orangehrm-paper-container\"]/div/button")
	WebElement addUserButton;
	@FindBy(xpath="//div[@class=\"oxd-form-row\"]/div/div[1]/div/div[2]/input")
	WebElement userName;
	@FindBy(xpath="//div[@class=\"oxd-form-row\"]/div/div[2]/div/div[2]/div/div/div[2]/i")
	WebElement userRoleDrop;
	@FindBy(xpath="//div[@class='oxd-select-option']")
	List<WebElement> roleOptions;
	@FindBy(xpath="//div[@class=\"oxd-form-row\"]/div/div[3]/div/div[2]/div/div/input")
	WebElement employeeNameBox;
	@FindBy(xpath="//div[@class='oxd-autocomplete-option']")
	List<WebElement> namesList;
	@FindBy(xpath="//div[@class='oxd-form-row']/div/div[4]/div/div[2]/div/div/div[2]/i")
	WebElement employeeStatus;
	@FindBy(xpath="//div[@class='oxd-select-option']")
	List<WebElement> statusOptions;
	@FindBy(xpath="//div[@class='oxd-form-actions']/button[2]")
	WebElement searchButton;
	@FindBy(css=".oxd-icon.bi-trash")
	WebElement trashIcon;
	@FindBy(xpath="//div[@class='orangehrm-modal-footer']/button[2]")
	WebElement deleteButton;
	@FindBy(id="oxd-toaster_1")
	WebElement sucessToast;
	
	public AdminUserManagementPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public AddUserPage addUser_click() {
		addUserButton.click();
		AddUserPage addUserPage = new AddUserPage(driver);
		return addUserPage;
	}

	public void searchUser(String user, String userRole, String name, String userStatus) throws InterruptedException {
		waitForWebElementToAppear(userName);
		userName.sendKeys(user);
		userRoleDrop.click();
		dynamicDropList(roleOptions, userRole);
		employeeNameBox.sendKeys(name);
		Thread.sleep(2000);
		dynamicDropList(namesList, name);
		employeeStatus.click();
		dynamicDropList(statusOptions, userStatus);
		searchButton.click();
	}
	
	public void deleteUser() {
		waitForWebElementToAppear(trashIcon);
		trashIcon.click();
		waitForWebElementToAppear(deleteButton);
		deleteButton.click();
	}
	public WebElement getSucessToast() {
		waitForWebElementToAppear(sucessToast);
		return sucessToast;
	}
	
	
	

}
