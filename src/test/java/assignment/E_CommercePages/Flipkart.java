package assignment.E_CommercePages;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Flipkart {
WebDriver driver;
	
	// locator of first search product
//	WebElement firstproduct = driver.findElement(By.xpath("(//div[@class='hCKiGj'])[1]"));
	
	//constructor to initialize the driver
	public Flipkart(WebDriver driver) {
		this.driver = driver;
	}

	//Step 1: Search for the product
	public void ProductSearch(String product) {
		driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']")).sendKeys(product);
		driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']")).submit();
	}
//	Step 2: FindFirstProduct
	public WebElement FindFirstSearchProd() {
		WebElement firstproduct = driver.findElement(By.xpath("(//div[@class='hCKiGj'])[1]"));
		return firstproduct;
	}

	// Get first product price
	public String getProdName(WebElement firstproduct) {
		String name = firstproduct.findElement(By.className("syl9yP")).getText();
		return name;
		
	}
	
	// Get first product link
	public String getProdLink(WebElement firstproduct) {
		String link = firstproduct.findElement(By.className("+tlBoD")).getAttribute("href");
		return link;
	}
	
	// Get First product Price
	public String getProdPrice(WebElement firstproduct) {
		String price = firstproduct.findElement(By.className("Nx9bqj")).getText();
		return price;
	
	}
	
	//Step 2: Add to the cart
	public void OpenProductToAdd(){
		// click on first product to add in cart
		WebElement firstproduct = driver.findElement(By.xpath("(//div[@class='hCKiGj'])[1]"));
		firstproduct.click();
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
		// add to cart
		public void AddToCart() {
		WebElement locator = driver.findElement(By.xpath("//button[normalize-space()='Add to cart']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 2000)", "");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add to cart']")));
		locator.click();
	}
	
	
	//Step 4: checkout
	public void CartCheckout() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[span[normalize-space()='Place Order']]")));
		driver.findElement(By.xpath("//button[span[normalize-space()='Place Order']]")).click();
	}

}
