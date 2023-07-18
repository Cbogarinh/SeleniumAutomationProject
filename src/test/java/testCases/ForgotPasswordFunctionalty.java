package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.ResetPasswordPage;
import testComponents.BaseTest;

public class ForgotPasswordFunctionalty extends BaseTest{
	
	@Test(dataProvider="forgotPasswordData")
	public void resetPasswordValidUser(HashMap<String,String> input) {
		ResetPasswordPage resetPasswordPage = loginPage.clickForgotPasswordButton();
		resetPasswordPage.resetPassword(input.get("username"));
		Assert.assertTrue(resetPasswordPage.getResetPasswordSucessfullyAlert().isDisplayed());
	}
	
	@Test
	public void resetPasswordBlankUser() {
		ResetPasswordPage resetPasswordPage = loginPage.clickForgotPasswordButton();
		resetPasswordPage.resetPasswordButtonClick();
		Assert.assertTrue(resetPasswordPage.getRequiredAlert().isDisplayed());
		
	}
	
	@DataProvider(name="forgotPasswordData")
	public Object[][] getData() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\data\\testData.json";
		List<HashMap<String,String>> data = getJsonDataToMap(filePath);
		return new Object[][] {{data.get(0)}};
	}

}
