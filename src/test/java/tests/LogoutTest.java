package tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class LogoutTest extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws Exception {
		readAndWritePropFile();
		sheetname="";
		testName="Logout Test";
		testDescription="Testing the Logout functionality of the application with valid and invalid details";
		testAuthor="Gopalakrishnan";
		testCategory="Smoke Testing";
	}
	
	@Test
	public void Logouttest() {
	
		new HomePage(driver)
		.clickLoginNav()
		.usernameLogin(prop.getProperty("validUserName"))
		.passwordLogin(prop.getProperty("validPassword"))
		.clickLoginButton()
		.clickOnLogout();
		
	}
}
