package pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class CategoriesPage extends ProjectSpecificationMethods {

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
	
	public CategoriesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public CategoriesPage validatingCategoryList() throws Throwable {
		Thread.sleep(2000);
		List<WebElement> productTitles = driver.findElements(By.xpath("//div[@id='tbodyid']//h4[@class='card-title']/a"));
		boolean allMatch = true;
		System.out.println("Products displayed under 'Laptops' category:");
		for (WebElement product : productTitles) {
		    String title = product.getText();
		    System.out.println(" - " + title);
		    if (!isLaptop(title)) {
		        allMatch = false;
		    }
		}
		if (allMatch) {
		    System.out.println("All products are correctly filtered by 'Laptops'.");
		} else {
		    System.out.println("Some products do not belong to 'Laptops'.");
		}
		return this;
	}
	
	public boolean isLaptop(String title) {
	    List<String> knownLaptops = Arrays.asList("Sony vaio i5", "Sony vaio i7", "MacBook air", "MacBook Pro", "Dell i7 8gb", "Dell i9 16gb", "2017 Dell 15.6 Inch");
	    return knownLaptops.contains(title);
	}

}
