import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task2 {


	public static void main(String[] args) throws InterruptedException {
	

		System.setProperty("webdriver.chrome.driver", "/home/apmosys/Task/chromedriver_linux64/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		
		Actions a = new Actions(driver);
	
		driver.get("https://www.amazon.in");

      
		
        WebElement amazonSearchBox = driver.findElement(By.id("twotabsearchtextbox"));
        amazonSearchBox.sendKeys("iPhone");
        amazonSearchBox.submit();
        Thread.sleep(2000);

        List<WebElement> nameElements = driver.findElements(By.xpath("//*[contains(text(),'Apple iPhone')]"));
        Thread.sleep(2000);
      // System.out.println(nameElements.size());
        List<WebElement> priceElements = driver.findElements(By.xpath("//*[@class='a-price-whole']"));
        Thread.sleep(2000);
       // System.out.println(priceElements.size());

        
        double lowestPrice = Double.MAX_VALUE;
        String lowestPriceName = "";
        
        try
        {
        for (int i = 0; i < nameElements.size(); i++) {
            String name = nameElements.get(i).getText();
            String priceText = priceElements.get(i).getText().replaceAll("[^0-9]", "");

            if (!priceText.isEmpty()) {
                double price = Double.parseDouble(priceText);
                if (price < lowestPrice) {
                    lowestPrice = price;
                    lowestPriceName = name;
                }
            }
        }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Mismatch in the number of iPhone names and prices.");
            e.printStackTrace();
        }
        
        
       System.out.println(lowestPriceName);
        
        
        driver.get("https://www.flipkart.com");
        
		
		//driver.findElement(By.xpath("//*[@class='_2KpZ6l _2doB4z']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='_2IX_2- VJZDxU']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='_2IX_2- VJZDxU']")).sendKeys("8327751730");
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[3]/button")).click();
		//Thread.sleep(3000);
		//driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[3]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='_2KpZ6l _2doB4z']")).click();
		

        
        WebElement flipkartSearchBox = driver.findElement(By.cssSelector("input[type='text']"));
        flipkartSearchBox.sendKeys(lowestPriceName);
        Thread.sleep(1000);
        a.moveToElement(driver.findElement(By.cssSelector("input[type='text']"))).build().perform();
        flipkartSearchBox.submit();

        
        Thread.sleep(7000);
        WebElement flipkartPriceElement = driver.findElement(By.cssSelector("._30jeq3._1_WHN1:first-child"));
        String flipkartPriceText = flipkartPriceElement.getText().replaceAll("[^0-9]", "");
        double flipkartPrice = Double.parseDouble(flipkartPriceText);

        
        System.out.println("Brand: " + lowestPriceName);
        System.out.println("Amazon Price: ₹" + lowestPrice);
        System.out.println("Flipkart Price: ₹" + flipkartPrice);

        if (lowestPrice < flipkartPrice) {
            System.out.println("Lowest Price: ₹" + lowestPrice + " on Amazon");
        } else if (flipkartPrice < lowestPrice) {
            System.out.println("Lowest Price: ₹" + flipkartPrice + " on Flipkart");
        } else {
            System.out.println("The prices are the same on both Amazon and Flipkart");
        }
       double pricedifference =Math.abs(lowestPrice-flipkartPrice);
       System.out.println("Price Difference : ₹" + pricedifference);
       
        driver.close();
        driver.quit();
	}

}
