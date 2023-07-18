package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.DashboardPage;
import testComponents.BaseTest;
import testComponents.Retry;

public class LoginFunctionalityValidation extends BaseTest{
	
	@Test(dataProvider="loginTestData", groups="LoginValidation")
	public void loginWithValidCredetials(HashMap<String,String> input) {
		loginPage.loginApplication(input.get("username"), input.get("password"));
		Assert.assertTrue(loginPage.getDashboardPage().isDisplayed());
	}
	@Test(dataProvider="loginTestData", groups="LoginValidation", retryAnalyzer=Retry.class)
	public void loginWithInvalidUser(HashMap<String,String> input) {
		loginPage.loginApplication(input.get("invalidUserName"), input.get("password"));
		Assert.assertTrue(loginPage.getInvalidCredentialsAlert().isDisplayed());
	}
	@Test(dataProvider="loginTestData", groups="LoginValidation", retryAnalyzer=Retry.class)
	public void loginWithInvalidPassword(HashMap<String,String> input) {
		loginPage.loginApplication(input.get("username"), input.get("invalidPassword"));
		Assert.assertTrue(loginPage.getInvalidCredentialsAlert().isDisplayed());
	}
	@Test(groups="LoginValidation")
	public void loginWithBlankCrendentials() {
		loginPage.clickLoginButton();
		Assert.assertTrue(loginPage.getRequiredUserNameAlert().isDisplayed());
		Assert.assertTrue(loginPage.getRequiredPasswordAlert().isDisplayed());
	}
	@Test(dataProvider="loginTestData",groups="LoginValidation")
	public void logoutFunctionality(HashMap<String,String> input) {
		DashboardPage dashboardPage = loginPage.loginApplication(input.get("username"), input.get("password"));
		dashboardPage.logout();
	}
	
	
	@DataProvider(name="loginTestData")
	public Object getData() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\data\\testData.json";
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		return new Object[][] {{data.get(0)}};
	}

}
