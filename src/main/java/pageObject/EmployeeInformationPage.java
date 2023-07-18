package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class EmployeeInformationPage extends AbstractComponents{

	WebDriver driver;
	
	@FindBy(xpath="//div[@class=\"orangehrm-header-container\"]/button")
	WebElement addEmployeeButton;
	@FindBy(xpath="//div[@class=\"oxd-form-row\"]/div/div[1]/div/div[2]/div/div/input")
	WebElement employeeNameInput;
	@FindBy(xpath="//div[@role='listbox']/div")
	List<WebElement> employeeNameListBox;
	@FindBy(xpath="//div[@class='oxd-form-actions']/button[2]")
	WebElement searchButton;
	@FindBy(css=".oxd-icon.bi-trash")
	WebElement trashIcon;
	@FindBy(xpath="//div[@class='orangehrm-modal-footer']/button[2]")
	WebElement deleteButton;
	@FindBy(id="oxd-toaster_1")
	WebElement successToast;
	
	public EmployeeInformationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public AddEmployeePage addEmployeeButton_click(){
		addEmployeeButton.click();
		AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
		return addEmployeePage;
	}
	
	public void deleteEmployee(String name, String script) {
		employeeNameInput.sendKeys(name);
		dynamicDropList(employeeNameListBox, name);
		waitForWebElementToAppear(searchButton);
		searchButton.click();
		scrollPage(script);
		waitForWebElementToAppear(trashIcon);
		trashIcon.click();
		waitForWebElementToAppear(deleteButton);
		deleteButton.click();
	}
	public WebElement getSucessToast() {
		waitForWebElementToAppear(successToast);
		return successToast;
	}
	
	
}
