package tests;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class SignUpTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() throws Exception {
		readAndWritePropFile();
		sheetname="SignupData";
		testName="Signup Test";
		testDescription="Testing the Signup functionality of the application with valid and invalid details";
		testAuthor="Gopalakrishnan";
		testCategory="Smoke Testing";
	}
	
	@Test(dataProvider = "readData")	
	public void SignupTest(String username, String password, String expectedResult, String testType) throws IOException, Exception {
	
		new HomePage(driver)
		.clickOnSignUpNav()
		.usernameSignUp(username)
		.passwordSignUp(password)
		.clickSignupButton(testType,expectedResult);
		
	}
}
