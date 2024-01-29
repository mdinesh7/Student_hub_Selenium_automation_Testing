
/**
 * @author Pratik
 **/

import org.junit.jupiter.api.Test;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.JavascriptExecutor;

public class FifthScenario {
	WebDriver driver;
	ChromeOptions options = new ChromeOptions();
	DesiredCapabilities cp = new DesiredCapabilities();
	Properties prop = new Properties();
	InputStream input;
	ExtentReports reports;
	ExtentTest test;
//
	@Test
	public void login() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Pratik/Downloads/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();


		 try (FileInputStream inputStream = new FileInputStream("./config.properties")) {
			    prop.load(inputStream);
			} catch (IOException e) {
			    e.printStackTrace();
			}

		  //navigate to the web site
		  
		  
		  String originalWindow = driver.getWindowHandle();

		  JavascriptExecutor jse = (JavascriptExecutor)driver;

	     	long thirtyMilliseconds = TimeUnit.MILLISECONDS.toNanos(30);
			WebDriverWait wait= new WebDriverWait(driver, thirtyMilliseconds);

			driver.get(prop.getProperty("NEU_STUDENT_HUB"));
			Utils.takeScreenShot(driver, "Scenario-5 - Navigating to student Hub");

			// Checking if there is only 1 tab
			assert driver.getWindowHandles().size() == 1;

			// putting email address in input field
			WebElement emailIp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html/body/div/form[1]/div/div/div[2]/div[1]/div/div/div/div/div[1]/div[3]/div/div/div/div[2]/div[2]/div/input[1]")));
			emailIp.sendKeys(prop.getProperty("NEU_EMAIL"));

			Utils.takeScreenShot(driver, "Scenario-5 - Entering the email address");

			WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html/body/div/form[1]/div/div/div[2]/div[1]/div/div/div/div/div[1]/div[3]/div/div/div/div[4]/div/div/div/div/input")));
			nextBtn.click();

			WebElement myNEULogin = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div/form/div[1]/div[2]/div/span")));
			myNEULogin.click();

			// entering credentials
			WebElement userName = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
			userName.sendKeys(prop.getProperty("NEU_USERNAME"));

			WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
			pwd.sendKeys(prop.getProperty("NEU_PWD"));

			Utils.takeScreenShot(driver, "Scenario-5 - Credentials Entered");

			WebElement loginBtn = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/section/div/div[1]/div/form/div[3]/button")));
			loginBtn.click();

			 WebElement iframeElement = driver.findElement(By.id("duo_iframe"));
		      driver.switchTo().frame(iframeElement);
		      Thread.sleep(1000);
		      
		      WebElement auth = driver.findElement(By.id("auth_methods"));
		      auth.click();
	          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	          driver.switchTo().defaultContent();
	          
			WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html/body/div/form/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/input")));
			yesBtn.click();
	        Thread.sleep(1000);
	   	 // resources tab.
			try {
				driver.findElements(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[1]/span/i")).get(0).click();
			}catch (Exception e) {

			} finally {
				driver.findElements(By.linkText("Resources")).get(0).click();
			}	
			Thread.sleep(4000);
	        //career 
	        
	        driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[1]/div[2]/div/div[2]/div/p")).click();
	        Thread.sleep(4000);
	        
	        String winHandleBefore = driver.getWindowHandle();
	        
	        //Campus Employement
	        driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[1]/div")).click();
	        Thread.sleep(8000);
	        
	        for (String winHandle: driver.getWindowHandles()){
	        	driver.switchTo().window(winHandle);
	        	}
	        
	        //New window
	        //Find Job
	        driver.findElement(By.xpath("//*[@id=\"post-2\"]/div/div/div/div[1]/div[3]/div[2]/div/a")).click();
	      
	        Thread.sleep(13000);
	        //Find on campus
	      //*[@id="post-2071"]/div/div/div/div[1]/section/div[1]/div/div/a[1]
	        driver.findElement(By.xpath("//*[@id=\"post-2071\"]/div/div/div/div[1]/section/div[1]/div/div/a[1]")).click();
	        Thread.sleep(13000);
	        
	        
	        driver.findElement(By.id("tdCheckbox")).click();
	        driver.findElement(By.id("submitButton")).click();

	        Thread.sleep(1000);
	        //menu
	      //*[@id="app-chrome-container"]/div/div[6]/div[1]/div[1]/button/div
	        driver.findElement(By.xpath("//*[@id=\"app-chrome-container\"]/div/div[6]/div[1]/div[1]")).click();
	        Thread.sleep(1000);
	        //job nd career
	      //*[@id="tabpanel-vxec0-app"]/div/div[2]/div/ul/li[3]/a
	        driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/ul/li[8]/a")).click();
	        Thread.sleep(5000);
//	      driver.quit();
	        //create job alert
	        
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/section/div/div/div/div[1]/div/div[2]/div/div[2]/div[1]/div/div[2]/div/div[4]/div[1]/div/div/div[2]/div/div[1]/ul/li[1]/div/div/div")).click();
	        
	        Thread.sleep(3000);
	        
	        driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div[2]/div/div[2]/div/div/ul/li[1]/div[2]/div/div/input")).sendKeys("SDE");
	        
	        
	        driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div[2]/div/div[2]/div/div/ul/li[2]/div[2]/div/div/div/div/div[1]/div[1]/input")).click();
	        driver.findElement(By.xpath("/html/body/div[12]/div[1]/div/div/div/div[1]/div/div/div[2]/div/div[1]/div/div/div/input")).click();
	        
	        
	        driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div[2]/div/div[2]/div/div/ul/li[3]/div[2]/div/div/div/div/div/div[1]/input")).click();
	        driver.findElement(By.xpath("/html/body/div[12]/div[1]/div/div/div/div[1]/div/div/div[1]/div/div[1]/div/div/div/input")).click();
	        
	        
	        driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div[2]")).click();
	        

	        driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div[2]/div/div[2]/div/div/ul/li[4]/div[2]/div/div/div/div/div/div[1]/input")).sendKeys("Application");
	        
	        
	        Robot robot;
			try {
				robot = new Robot();
				
				robot.keyPress(KeyEvent.VK_ENTER); // press "Enter" key
				robot.keyRelease(KeyEvent.VK_ENTER); // release "Enter" key
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
			driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div[2]")).click();
			
			driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div[2]/div/div[2]/div/div/ul/li[5]/div[2]/div/div/div/div/div/div[1]/input")).click();
	        driver.findElement(By.xpath("/html/body/div[12]/div[1]/div/div/div/div[1]/div/div/div[5]/div/div[1]/div/div/div/input")).click();
	        
	        driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div[2]/div/footer/div/div[2]/div")).click();
		
	        driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div[2]/div/footer/div/div[2]/div/div[1]/div[1]/button[1]")).click();
	        
			
//	     driver.quit();
	
		
	}

	

}
