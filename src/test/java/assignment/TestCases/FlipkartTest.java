package assignment.TestCases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import assignment.E_CommercePages.BaseClass;
import assignment.E_CommercePages.Flipkart;

public class FlipkartTest extends BaseClass {
	Flipkart flipkartpage;
	String baseURL = "https://www.flipkart.com/";
	String product = "Titan Watch";
	
	@BeforeMethod
	public void setup() {

		flipkartpage = new Flipkart(driver);
		test = extent.createTest("Flipkart product search");
	}
	
	@Test
	public void FlipkartTask() throws InterruptedException, IOException {
		

		driver.get(baseURL);
		ScreenshotIntoLog("Navigate Flipkart.in page");

		flipkartpage.ProductSearch(product);
		test.log(Status.INFO,"Searching Product in Searchbox");
		ScreenshotIntoLog("Searching product");

		WebElement firstproduct= flipkartpage.FindFirstSearchProd();
		ScreenshotIntoLog("Searched product");

		String name = flipkartpage.getProdName(firstproduct);
		test.log(Status.INFO, "Product Name ->"+ name);
		
		String price = flipkartpage.getProdPrice(firstproduct);
		test.log(Status.INFO, "Product Price ->"+price);
		
		String link = flipkartpage.getProdLink(firstproduct);
		test.log(Status.INFO, "Product link ->"+ link);

		flipkartpage.OpenProductToAdd();
		ScreenshotIntoLog("Ready to add into cart");
		
		flipkartpage.AddToCart();
		ScreenshotIntoLog("Adding the product in to cart");
		
		
		flipkartpage.CartCheckout();
		test.log(Status.INFO, "Cart checkout");

	}

}
