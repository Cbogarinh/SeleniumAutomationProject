package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.AddEmployeePage;
import pageObject.DashboardPage;
import pageObject.EmployeeInformationPage;
import testComponents.BaseTest;

public class PIM_EmployeeInformationFunctionality extends BaseTest {
	
	@Test(dataProvider = "employeeInfoData", groups="addEmployee")
	public void addEmployeeValidation(HashMap<String, String> input) throws IOException, InterruptedException {
		DashboardPage dashboardPage = loginPage.loginApplication(input.get("username"), input.get("password"));
		EmployeeInformationPage employeeInfoPage = dashboardPage.goToPIM_EmployeeInformation();
		AddEmployeePage addEmployeePage = employeeInfoPage.addEmployeeButton_click();
		addEmployeePage.addNewEmployee(input.get("firstName"), input.get("lastName"),
				System.getProperty("user.dir") + "\\autoItScripts\\OramgeHRM-imgUpload.exe");
		Assert.assertTrue(addEmployeePage.getSucessToast().isDisplayed());
	}
	
	@Test(dataProvider = "employeeInfoData", dependsOnMethods="addEmployeeValidation")
	public void deleteEmployeeValidation(HashMap<String, String> input) {
		DashboardPage dashboardPage = loginPage.loginApplication(input.get("username"), input.get("password"));
		EmployeeInformationPage employeeInfoPage = dashboardPage.goToPIM_EmployeeInformation();
		employeeInfoPage.deleteEmployee(input.get("fullName"), "scrollBy(0,400)");
		Assert.assertTrue(employeeInfoPage.getSucessToast().isDisplayed());
	}
	
	@DataProvider(name="employeeInfoData")
	public Object[][] getData() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\data\\testData.json";
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		return new Object[][] {{data.get(0)}};
}
}
