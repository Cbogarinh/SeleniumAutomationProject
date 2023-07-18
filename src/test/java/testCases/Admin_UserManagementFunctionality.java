package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.AddEmployeePage;
import pageObject.AddUserPage;
import pageObject.AdminUserManagementPage;
import pageObject.DashboardPage;
import pageObject.EmployeeInformationPage;
import testComponents.BaseTest;

public class Admin_UserManagementFunctionality extends BaseTest{
	
	@Test(dataProvider = "adminUsermManagementData")
	public void addUserValidation(HashMap<String, String> input) throws InterruptedException, IOException {
		DashboardPage dashboardPage = loginPage.loginApplication(input.get("username"), input.get("password"));
		EmployeeInformationPage employeeInfoPage = dashboardPage.goToPIM_EmployeeInformation();
		AddEmployeePage addEmployeePage = employeeInfoPage.addEmployeeButton_click();
		addEmployeePage.addNewEmployee(input.get("firstName"), input.get("lastName"),
				System.getProperty("user.dir") + "\\autoItScripts\\OramgeHRM-imgUpload.exe");
		AdminUserManagementPage adminUserPage = addEmployeePage.goToAdmin_UserManagement();
		AddUserPage addUserPage = adminUserPage.addUser_click();
		addUserPage.addUser(input.get("fullName"), input.get("user"), input.get("userRole"), input.get("userStatus"),
				input.get("newPassword"));
		Assert.assertTrue(addUserPage.getSuccessToast().isDisplayed());
	}

	@Test(dataProvider = "adminUsermManagementData", dependsOnMethods = "addUserValidation")
	public void deleteUserValidation(HashMap<String, String> input) throws InterruptedException {
		DashboardPage dashboardPage = loginPage.loginApplication(input.get("username"), input.get("password"));
		AdminUserManagementPage adminUserPage = dashboardPage.goToAdmin_UserManagement();
		adminUserPage.searchUser(input.get("user"), input.get("userRole"), input.get("fullName"),
				input.get("userStatus"));
		adminUserPage.deleteUser();
		Assert.assertTrue(adminUserPage.getSucessToast().isDisplayed());
	}

	@DataProvider(name = "adminUsermManagementData")
	public Object[][] getData() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\data\\testData.json";
		List<HashMap<String, String>> data = getJsonDataToMap(filePath);
		return new Object[][] { { data.get(0) } };

	}

}
