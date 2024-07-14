package assignment.E_CommercePages;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Amazon {
	WebDriver driver;
	
	// locator of first search product
	By firstproduct = By.xpath("(//div[@class='sg-col-inner'])[6]");
	
	//constructor to initialize the driver
	public Amazon(WebDriver driver) {
        this.driver = driver;
    }
	
	//Step 1: Search for the product
	public void ProductSearch(String product) {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(product);
		driver.findElement(By.id("nav-search-submit-button")).click();
	}
	
	//Step 2: FindFirstProduct
//	public void FindFirstSearchProd() {
//		driver.findElement(firstproduct).click();;
//		firstproduct.click();
//	}
	
	// Get first product price
	public String getProdName() {
		WebElement product = driver.findElement(firstproduct);
		String productName = product.findElement(By.cssSelector("h2.s-line-clamp-1")).getText();
		return productName;
		
	}
	
	// Get first product link
	public String getProdLink() {
		WebElement product = driver.findElement(firstproduct);
		String productLink = product.findElement(By.cssSelector("a.a-link-normal")).getAttribute("href");
		return productLink;
		
	}
	
	// Get First product Price
	public String getProdPrice() {
		WebElement product = driver.findElement(firstproduct);
		String productPrice = product.findElement(By.cssSelector("span.a-price-whole")).getText();
		return productPrice;
	}
	
	//Step 2: Add to the cart
	public void AddToCart(){
		// click first product to add in cart
		driver.findElement(firstproduct).click();
		// product will open in new TAB so need to switch on this tab by using switchTo method
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		//by clicking on add to cart button product is added in cart
		driver.findElement(By.xpath("//input[@title='Add to Shopping Cart']")).click();
		// once product is added a side screen will come we can use this screen to go to cart but close it once it loaded
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id='attachDisplayAddBaseAlert'] h4[class='a-alert-heading']")));
		driver.findElement(By.xpath("//a[@id='attach-close_sideSheet-link']")).click();
	}
	
	//Step 3: open cart from nav bar
	public void OpenCartFromNav() throws InterruptedException{
		// Open Cart from Nav bar
//		Thread.sleep(3000);
		driver.findElement(By.id("nav-cart")).click();
	}
	
	//Step 4: checkout
	public void CartCheckout() {
		driver.findElement(By.cssSelector("input[value='Proceed to checkout']")).click();
//		Thread.sleep(2000);
	}

}
