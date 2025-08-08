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
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;


public class LoginPage extends ProjectSpecificationMethods{

	@FindBy (xpath = "//a[text()='Home ']")
	WebElement homeNav;
	
	@FindBy (xpath = "//a[text()='Contact']")
	WebElement contactNav;
	
	@FindBy (xpath = "//a[text()='About us']")
	WebElement aboutUsNav;
	
	@FindBy (xpath = "//a[text()='Cart']")
	WebElement cartNav;
	
	@FindBy (id="logout2")
	WebElement logoutNav;
	
	@FindBy (id="nameofuser")
	WebElement userName;
	
	@FindBy (xpath = "//div[@class='row']//a[text()='Laptops']")
	WebElement laptopCategory;
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public ProductPage clickOnProduct() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Samsung galaxy s6"))).click();
		return new ProductPage(driver);
	}
	
	public CategoriesPage clickOnLaptopCategory() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
		WebElement laptopsCategory = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Laptops")));
		laptopsCategory.click();
		return new CategoriesPage(driver);
	}
	
	public HomePage clickOnLogout() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
		logoutNav.click();
		WebElement loginVisible = driver.findElement(By.id("login2"));
          if (loginVisible.isDisplayed()) {
              System.out.println("Test Passed: User successfully logged out and 'Log in' is visible.");
          } else {
              System.out.println("Test Failed: 'Log in' not visible after logout.");
          }
		return new HomePage(driver);
	}
	
}
