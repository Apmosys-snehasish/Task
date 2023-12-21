import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class EOD {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        // TODO Auto-generated method stub
        Properties pro=new Properties();
        

        pro.load(new FileInputStream(System.getProperty("user.dir")+"//config.properties"));
        Thread.sleep(1000);
        String username=pro.getProperty("username");
        String password=pro.getProperty("password");
        String password1=pro.getProperty("password1");
        System.out.println(username);
        System.setProperty("webdriver.chrome.driver","/home/apmosys/Task/chromedriver_linux64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://ishine.apmosys.com/#/login");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"userPassword\"]")).sendKeys(password1);
        driver.findElement(By.xpath("//button[text()='SIGN IN']")).click();
        Thread.sleep(5000);
        String original=driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
       // JavascriptExecutor js =new JavascriptExecutor();
        //((JavascriptExecutor) driver).executeScript("https://mail.apmosys.com/webmail/");
        driver.navigate().to("https://mail.apmosys.com/webmail/");
          driver.findElement(By.xpath("//input[@name=\"email-address\"]")).sendKeys(username);
          Thread.sleep(2000);
          driver.findElement(By.name("next")).click();
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(password);
       

        driver.findElement(By.xpath("//*[text()='Sign in']")).click();
        Thread.sleep(5000);
        driver.switchTo().frame("gui.frm_main.main.mailview#frame");
        WebElement yyy = driver.findElement(By.xpath("//div[@class=\"iw_webmail_msg_header\"]/following-sibling::p"));
    //    WebElement getotp=driver.findElement(By.xpath("//*[@id=\"gui.frm_main.main.mailview#message\"]/tbody/tr[2]/td"));
        String otp=yyy.getText();
        System.out.println("My otp is" + otp);
        //driver.navigate().back();
        String onlyOTP=otp.replace("Please find your otp ", "");
        System.out.println(onlyOTP);
        driver.switchTo().window(original);
        //driver.navigate().back();
        driver.findElement(By.xpath("//input[@id=\"userOtp\"]")).sendKeys(onlyOTP);
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        driver.findElement(By.xpath("//p[text()='Apply Timesheet']")).click();
        WebElement working=driver.findElement(By.xpath("//*[@id=\"timesheetTabContent\"]/div/app-my-timesheet/div[2]/div/div[2]/div[2]/div/div/div/select"));////select[@class=\"form-control ng-pristine ng-valid ng-touched\"]
        Select select=new Select(working);
        Thread.sleep(1000);

        select.selectByVisibleText("Working");
        //select.selectByIndex(0);
        driver.findElement(By.xpath("//input[@id=\"officeInTime\"]")).click();
        Date date = new Date();
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
           String str = formatter.format(date);
          System.out.print("Current date: "+str);
        
        
        driver.quit();
        
        
    }

}

	

