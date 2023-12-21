import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Select_class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	System.setProperty("webdriver.chrome.driver", "/home/apmosys/Task/chromedriver_linux64/chromedriver");
	WebDriver driver =new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.amazon.in");
	WebElement options = driver.findElement(By.id("searchDropdownBox"));
	
	Select select = new Select(options);
	select.selectByVisibleText("Baby");
	
	
	
		
		
	}

}
