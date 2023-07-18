package pageObject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class AddEmployeePage extends AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(name="firstName")
	WebElement firstName;
	@FindBy(name="lastName")
	WebElement lastName;
	@FindBy(xpath="//i[@class='oxd-icon bi-plus']")
	WebElement uploadPictureIcon;
	@FindBy(xpath="//div[@class='oxd-form-actions']/button[2]")
	WebElement saveButton;
	@FindBy(id="oxd-toaster_1")
	WebElement successToast;
	
	public AddEmployeePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addNewEmployee(String fName, String lName, String filePath) throws IOException, InterruptedException {
		waitForWebElementToAppear(firstName);
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		uploadPictureIcon.click();
		Thread.sleep(1000);
		uploadFile(filePath);
		Thread.sleep(1000);
		waitForWebElementToAppear(saveButton);
		saveButton.click();
	}
	public WebElement getSucessToast() {
		waitForWebElementToAppear(successToast);
		return successToast;
	}
	
}
