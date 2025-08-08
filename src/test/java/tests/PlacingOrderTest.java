package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class PlacingOrderTest extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws Exception {
		readAndWritePropFile();
		sheetname="PlaceOrder";
		testName="PlaceOrder Test";
		testDescription="Testing the Placing Order functionality of the application with valid and invalid details";
		testAuthor="Gopalakrishnan";
		testCategory="Smoke Testing";
	}
	
	@Test(dataProvider = "readData")	
	public void PlaceorderTest(String name, String country, String city, String creditcard, String month, String year, String testype) throws Throwable {
	
		new HomePage(driver)
		.clickLoginNav()
		.usernameLogin(prop.getProperty("validUserName"))
		.passwordLogin(prop.getProperty("validPassword"))
		.clickLoginButton()
		.clickOnProduct()
		.clickOnCartButton()
		.clickOnCartNav()
		.clickOnPlaceOrderButton()
		.fillPlacingOrder(name, country, city, creditcard, month, year, testype);
	}
}
