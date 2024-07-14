package assignment.TestCases;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import assignment.E_CommercePages.Amazon;
import assignment.E_CommercePages.BaseClass;


public class AmazonTest extends BaseClass{

	Amazon amazonpage;
	String baseURL = "https://www.amazon.in/";
	String product = "Titan Watch";

    
   
	@BeforeMethod
	public void setup() {
//		setupBrowser()
		amazonpage = new Amazon(driver);
		test = extent.createTest("Amazon product search");
	}
	
	@Test
	public void AmazonTask() throws InterruptedException, IOException{
		driver.get(baseURL);
		test.log(Status.INFO, "Navigated to Amazon.in");
		ScreenshotIntoLog("Navigating to Amazon");
        
        
		amazonpage.ProductSearch(product);
        test.log(Status.INFO, "Product search initiated");
        ScreenshotIntoLog("Product search initiated");
		
		String name = amazonpage.getProdName();
		test.log(Status.INFO, "Product Name ->" + name);
		
		String price = amazonpage.getProdPrice();
		test.log(Status.INFO, "Product Price ->" + price);
		
		String link = amazonpage.getProdLink();
		test.log(Status.INFO, "Product Link ->" + link);
		ScreenshotIntoLog("First product details fetched");
		
		amazonpage.AddToCart();
		test.log(Status.INFO, "Click on product so that we can add to Cart");
		ScreenshotIntoLog("Adding to Cart");
		
		ScreenshotIntoLog("Open Cart from nav bar");
		test.log(Status.INFO, "Opening Cart from Nav bar");
		amazonpage.OpenCartFromNav();
		test.log(Status.INFO, "Cart opened from Nav bar");
		ScreenshotIntoLog("Open Cart");
		
		ScreenshotIntoLog("Product in Cart, proceed to Checkout");
		amazonpage.CartCheckout();
		test.log(Status.INFO, "Cart checkout'");
		ScreenshotIntoLog("Cart Checkout");
	}
	

}
