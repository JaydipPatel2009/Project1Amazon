package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import moduleAmazon.*;

public class PlaceOrder{
	
	public WebDriver driver;
	public static WebElement element;
	public static String price;
	public static String priceCheckout;
	
	@Test(groups= {"PlaceOrder"}, dataProvider = "browser")
	public void initDriver(String browser, String driverlocation){
		driver = Commons.openBrowser(browser, driverlocation);
	  }
	@Parameters({"url"})
	@Test(groups= {"PlaceOrder"}, dependsOnMethods = {"initDriver"})
	public void openWebPortal(String url){
		driver.get(url);
	  }

	@Parameters({"byXPATH","searchboxXPath"})
	@Test(dependsOnMethods = {"openWebPortal"})
	public void searchBarElement(String by, String itemlocator){
		element = Commons.searchElement(driver, by, itemlocator);
	  }
	@Parameters({"itemname"})
	@Test(dependsOnMethods = {"searchBarElement"})
	public void enterText(String bookname){
		Commons.enterText(bookname,element);
	  }
	
	@Parameters({"byXPATH","searchButtonXPath"})
	@Test(dependsOnMethods = {"enterText"})
	public void clickElement(String by, String itemlocator) {
		element = Commons.searchElement(driver,by,itemlocator);
		element.click();
	}
	
	@Parameters({"byXPATH","itemXpath"})
	@Test(dependsOnMethods = {"clickElement"})
	public void clickItem(String by,String itemXpath) {
		Commons.clickElement(by,driver,itemXpath);
	}
	
	@Parameters({"byXPATH","itemPriceXpath"})
	@Test(dependsOnMethods = {"clickItem"})
	public void itemPrice(String by,String itemPriceXpath) {
		element = Commons.searchElement(driver, by, itemPriceXpath);
		price = element.getText();
		System.out.println("Price: "+price);
	}
	
	@Parameters({"byXPATH","addToCartBtnXpath"})
	@Test(dependsOnMethods = {"itemPrice"})
	public void addToCart(String by,String addToCartBtnXpath) {
		Commons.clickElement(by,driver,addToCartBtnXpath);
	}
	
	@Parameters({"byXPATH","proceedToCheckOutPriceXpath"})
	@Test(dependsOnMethods = {"addToCart"})
	public void verifyPriceBeforeCheckout(String by,String proceedToCheckOutPriceXpath) {
		element = Commons.searchElement(driver, by, proceedToCheckOutPriceXpath);
		priceCheckout = element.getText();
		Assert.assertEquals(price,priceCheckout);
	}
	
	@Parameters({"byXPATH","proceedToCheckOutBtnXpath"})
	@Test(dependsOnMethods = {"verifyPriceBeforeCheckout"})
	public void proceedToCheckOut(String by,String proceedToCheckOutBtnXpath) {
		Commons.clickElement(by,driver,proceedToCheckOutBtnXpath);
	}
	
	@DataProvider
	public Object[][] browser(){
		return new Object[][] {
			{"chrome", "C:\\Users\\jaydpate\\Selenium\\chromedriver_win32\\chromedriver.exe"}
		};
	}
	
	@DataProvider
	public Object[][] book(){
		return new Object[][] {
			{"qa testing for beginners",""}
		};
	}

}
