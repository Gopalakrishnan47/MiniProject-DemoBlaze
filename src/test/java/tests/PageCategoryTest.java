package tests;



import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class PageCategoryTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() throws Exception {
		readAndWritePropFile();
		sheetname="";
		testName="CategoryPage Test";
		testDescription="Testing the CategoryPage functionality of the application with valid and invalid details";
		testAuthor="Gopalakrishnan";
		testCategory="Smoke Testing";
	}
	
	@Test	
	public void Pagecategory() throws Throwable {
	
		new HomePage(driver)
		.clickLoginNav()
		.usernameLogin(prop.getProperty("validUserName"))
		.passwordLogin(prop.getProperty("validPassword"))
		.clickLoginButton()
		.clickOnLaptopCategory()
		.validatingCategoryList();
		
	}
}
