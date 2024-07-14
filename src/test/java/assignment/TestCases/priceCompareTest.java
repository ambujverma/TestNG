package assignment.TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import assignment.E_CommercePages.Amazon;
import assignment.E_CommercePages.BaseClass;
import assignment.E_CommercePages.Flipkart;

public class priceCompareTest extends BaseClass {
	Amazon amazonpage;
	Flipkart flipkartpage;
	
	@BeforeMethod
	public void setup() {
        amazonpage = new Amazon(driver);
        flipkartpage = new Flipkart(driver);
        test = extent.createTest("Pice comparsion products");
    }
	
	@Test
	public void comparePrices() throws IOException {
		
		//Amazon
		driver.get("https://www.amazon.in/");
		amazonpage.ProductSearch("Titan Watch"); 
        String amazonProductName = amazonpage.getProdName();
        String amazonProductPrice = amazonpage.getProdPrice();
        test.log(Status.INFO, "Amazon Product name and price ->" + amazonProductName + " " + amazonProductPrice );
        ScreenshotIntoLog("Searched product in amzzon");
        
       
        //Flipkart
        driver.get("https://www.flipkart.com/");
        flipkartpage.ProductSearch("Titan Watch");  
        WebElement firstprod = flipkartpage.FindFirstSearchProd(); 
        String flipkartProductName = flipkartpage.getProdName(firstprod); 
        String flipkartProductPrice = flipkartpage.getProdPrice(firstprod);
        test.log(Status.INFO, "Flipkart Product name and price ->" + flipkartProductName + " " + flipkartProductPrice );
        ScreenshotIntoLog("Searched product in amzzon");
        
//         compare price results
        test.log(Status.INFO, "Price comparison of Amazon and Flipkar products and find the lowest price product");
        double amazonPrice = Double.parseDouble(amazonProductPrice.replace("₹", "").replace(",", ""));
        double flipkartPrice = Double.parseDouble(flipkartProductPrice.replace("₹", "").replace(",", ""));
        if (amazonPrice < flipkartPrice) {
            test.log(Status.PASS, "Amazon offers the lower price for 'Titan watch' ->" + amazonPrice);
        } else {
            test.log(Status.PASS, "Flipkart offers the lower price for 'Titan watch' ->" + flipkartPrice);
        }
    }
        
	}
