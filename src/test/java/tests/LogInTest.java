package tests;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class LogInTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() throws Exception {
		readAndWritePropFile();
		sheetname="LoginData";
		testName="Login Test";
		testDescription="Testing the Login functionality of the application with valid and invalid details";
		testAuthor="Gopalakrishnan";
		testCategory="Smoke Testing";
	}
	
	@Test(dataProvider = "readData")	
	public void LoginTest(String username, String password, String expectedResult, String testType) throws IOException, Exception {
	
		new HomePage(driver)
		.clickLoginNav()
		.usernameLogin(username)
		.passwordLogin(password)
		.clickLoginButton(testType, expectedResult, username);
		
	}
}

