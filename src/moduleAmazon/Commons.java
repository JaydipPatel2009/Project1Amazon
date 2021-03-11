package moduleAmazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Commons {
	static WebElement element = null;
	public static WebDriver openBrowser(String browsername, String driverlocation){
		WebDriver driver = null;
		
		switch (browsername) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", driverlocation);
				driver = new FirefoxDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", driverlocation);
				driver = new ChromeDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver", driverlocation);
				driver = new InternetExplorerDriver();
				break;
			default:
				break;
		}
		return driver;
	}
	
	public static WebElement searchElement(WebDriver driver, String by,String locator) {
		switch(by) {
			case "XPATH":
				element = driver.findElement(By.xpath(locator));
			default:
				
		}
		return element;
	}
	
	public static void enterText(String itemName, WebElement element) {
		element.sendKeys(itemName);
	}
	
	public static void clickElement(String by, WebDriver driver, String itemlocator) {
		element = searchElement(driver,by,itemlocator);
		element.click();
	}

}
