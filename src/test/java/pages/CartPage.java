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

public class CartPage extends ProjectSpecificationMethods {
	
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
	
	@FindBy (xpath="//tbody[@id='tbodyid']/tr[1]/td[4]")
	WebElement deleteLink;
	
	@FindBy (xpath="//tbody[@id='tbodyid']/tr[1]/td[2]")
	WebElement productTitle;
	
	@FindBy (xpath = "//tbody[@id='tbodyid']/tr[1]/td[3]")
	WebElement productPrice;
	
	@FindBy (xpath = "//tbody[@id='tbodyid']/tr[1]")
	WebElement productRow;
	
	@FindBy (id="name")
	WebElement namePO;
	
	@FindBy (id="country")
	WebElement countryPO;
	
	@FindBy (id="city")
	WebElement cityPO;
	
	@FindBy (id="card")
	WebElement cardPO;
	
	@FindBy (id="month")
	WebElement monthPO;
	
	@FindBy (id="year")
	WebElement yearPO;
	
	@FindBy (xpath = "//button[text()='Close']")
	WebElement closeButton;
	
	@FindBy (xpath = "//button[text()='Purchase']")
	WebElement purchaseButton;
	
	@FindBy (xpath = "//button[text()='Place Order']")
	WebElement placeOrderButton;
	
	@FindBy (xpath = "//button[text()='OK']")
	WebElement okButton;
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	public CartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public CartPage validatingProduct() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
		
		System.out.println("Product in cart: " + productTitle.getText());
		System.out.println("Price: $" + productPrice.getText());
		
		if (productTitle.getText().equals("Samsung galaxy s6") && productPrice.getText().equals("360")) {
		    System.out.println("Cart verification passed.");
		} else {
		    System.out.println("Cart verification failed.");
		}
		return this;
	}
	
	public CartPage deleteRowandValidating() {
		WebElement deleteLink = productRow.findElement(By.xpath("./td[4]/a"));
		deleteLink.click();
		wait.until(ExpectedConditions.invisibilityOf(productRow));
		WebElement totalElement = driver.findElement(By.id("totalp"));
		String totalText = totalElement.getText();
		System.out.println("Updated total: $" + totalText);
		
		if (totalText.isEmpty()) {
		    System.out.println("Product deleted and total updated correctly.");
		} else {
		    System.out.println("Product deleted but total is not zero (other items may remain).");
		}
		return this;
	}
	
	public CartPage fillPlacingOrder(String name, String country, String city, String creditcard, String month, String year, String testType) {
		if(testType.equals("ValidData")) {
			namePO.sendKeys(name);
			countryPO.sendKeys(country);
			cityPO.sendKeys(city);
			cardPO.sendKeys(creditcard);
			monthPO.sendKeys(month);
			yearPO.sendKeys(year);
			purchaseButton.click();
			WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sweet-alert")));
			String confirmationText = confirmation.getText();
			System.out.println(confirmationText);
			okButton.click();
		}else {
			namePO.sendKeys(name);
			countryPO.sendKeys(country);
			cityPO.sendKeys(city);
			cardPO.sendKeys(creditcard);
			monthPO.sendKeys(month);
			yearPO.sendKeys(year);
			purchaseButton.click();
			alert= driver.switchTo().alert();
			String alertText = alert.getText();
			System.out.println("Alert Text : "+ alertText);
			alert.accept();
		}
		
		return this;
	}
	
	public CartPage clickOnPlaceOrderButton() {
		placeOrderButton.click();
		return this;
	}
}
