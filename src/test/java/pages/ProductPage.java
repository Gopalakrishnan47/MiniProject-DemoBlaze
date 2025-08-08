package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class ProductPage extends ProjectSpecificationMethods{

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
	
	@FindBy (linkText ="Add to cart")
	WebElement addcartButton;
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public ProductPage clickOnCartButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();
		wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (alertText.equals("Product added.")) {
            System.out.println("Alert message verified: " + alertText);
        } else {
            System.out.println("Unexpected alert message: " + alertText);
        }
        alert.accept();
        
        return this;
	}
	
	public CartPage clickOnCartNav() {
		cartNav.click();
		return new CartPage(driver);
	}
}
