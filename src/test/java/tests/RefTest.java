package tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RefTest {

	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// TODO Auto-generated method stub
		try {
		
		driver.manage().window().maximize();
		driver.get("https://www.demoblaze.com/index.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		driver.findElement(By.id("loginusername")).sendKeys("abccdd");
		driver.findElement(By.id("loginpassword")).sendKeys("12345");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Samsung galaxy s6"))).click();

        // Step 5: Click "Add to cart"
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();

        // Step 6: Handle the alert and verify text
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (alertText.equals("Product added.")) {
            System.out.println("Alert message verified: " + alertText);
        } else {
            System.out.println("Unexpected alert message: " + alertText);
        }
        alert.accept();

    } catch (Exception e) {
        System.out.println("❗ Test failed: " + e.getMessage());
    } finally {
        driver.quit();
    }
	}

}
