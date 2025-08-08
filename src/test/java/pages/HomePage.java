package pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.ProjectSpecificationMethods;

public class HomePage extends ProjectSpecificationMethods{
	
	@FindBy (xpath = "//a[text()='Home ']")
	WebElement homeNav;
	
	@FindBy (xpath = "//a[text()='Contact']")
	WebElement contactNav;
	
	@FindBy (xpath = "//a[text()='About us']")
	WebElement aboutUsNav;
	
	@FindBy (xpath = "//a[text()='Cart']")
	WebElement cartNav;
	
	@FindBy (xpath = "//a[text()='Log in']")
	WebElement loginNav;
	
	@FindBy (id = "signin2")
	WebElement signupNav;
	
	@FindBy (id ="sign-username")
	WebElement signUserNameText;
	
	@FindBy (id="sign-password")
	WebElement signPasswordText;
	
	@FindBy (xpath = "//button[text()='Close']")
	WebElement closeButton;
	
	@FindBy (xpath="//button[text()='Sign up']")
	WebElement signupbutton;
	
	@FindBy (id="loginusername")
	WebElement loginUserNameText;
	
	@FindBy (id="loginpassword")
	WebElement loginPasswordText;
	
	@FindBy (xpath = "//button[text()='Log in']")
	WebElement loginButton;
	
	@FindBy (id="logout2")
	WebElement logoutNav;
	
	@FindBy (id="nameofuser")
	WebElement userName;
	
	
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void testPasswordFieldIsMasked() {
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        String fieldType = passwordField.getAttribute("type");
        Assert.assertEquals(fieldType, "password", "Password field is not masked!");
    }
	
	public HomePage clickOnSignUpNav() {
		signupNav.click();	
		return this;
	}
	
	public HomePage  usernameSignUp(String username) {
		signUserNameText.sendKeys(username);
		return this;
	}
	
	public HomePage  passwordSignUp(String password) {
		signPasswordText.sendKeys(password);
		return this;
	}
	
	public HomePage clickSignupButton(String testType, String expectedMessage) {
		signupbutton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = alert.getText();
        System.out.println("Alert message: " + alertMessage);
        
        
        if(testType.equals("ValidInput")) {
    		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
    		Assert.assertEquals(alertMessage, expectedMessage);
    		System.out.println("Signup Successful with valid data");
    		}else if(testType.equals("ExistingMailID")) {
    			Assert.assertEquals(alertMessage, expectedMessage);
    			System.out.println("Signup not Successful with Existing mailID");
    		}else if(testType.equals("emptyField") ) {
    			Assert.assertEquals(alertMessage, expectedMessage);
    			System.out.println("Signup not Successful with Empty Field");	
    		}
        alert.accept();
		return this;
	}
	
	public HomePage clickLoginNav() {
		loginNav.click();	
		return this;
	}
	
	public HomePage  usernameLogin(String username) {
		loginUserNameText.sendKeys(username);
		return this;
	}
	
	public HomePage  passwordLogin(String password) {
		loginPasswordText.sendKeys(password);
		return this;
	}
	
	public HomePage clickLoginButton(String testType, String expectedMessage, String username) {
		
		loginButton.click();  
        if(testType.equals("ValidInput")) {
    		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    		String actualText = userName.getText();
    		//System.out.println(actualText);
    		Assert.assertEquals(actualText, expectedMessage+" "+username);
    		System.out.println("Login Successful with valid data");		
    		}else if(testType.equals("ExistingMailID")) {
    			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    			alert = wait.until(ExpectedConditions.alertIsPresent());
    	        String alertMessage = alert.getText();
    	        System.out.println("Alert message: " + alertMessage);
    			Assert.assertEquals(alertMessage, expectedMessage);
    			System.out.println("Login not Successful with Existing mailID");
    			alert.accept();
    		}else if(testType.equals("emptyField") ) {
    			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    			alert = wait.until(ExpectedConditions.alertIsPresent());
    	        String alertMessage = alert.getText();
    			Assert.assertEquals(alertMessage, expectedMessage);
    			System.out.println("Login not Successful with Empty Field");
    			alert.accept();
    		}
        return this;
	}
	
	public LoginPage clickLoginButton()  {
		loginButton.click();
		return new LoginPage(driver);
	}
	
	
	
}
