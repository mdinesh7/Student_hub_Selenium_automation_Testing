
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

public class FirstScenario {
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

			// navigating to academics, classes and registration
			WebElement ACR = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html/body/div[1]/div[2]/div[2]/div[2]/div[3]/section/article/div[1]/div/div/div/div[1]/div/div/div/div/div/div[1]/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/div/div")));
			ACR.click();

			// clicking on myTranscript
			WebElement myTranscript = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html/body/div[1]/div[2]/div[2]/div[2]/div[3]/section/article/div[1]/div/div/div/div[1]/div/div/div/div/div/div[1]/div/div/div/div/div/div/div[2]/div/div/div[1]/div/div[20]/div/div/a")));
 
			Utils.takeScreenShot(driver, "Scenario-5 - Before clicking on my transcript");

			myTranscript.click();

			// going to other tab
			for (String windowHandle : driver.getWindowHandles()) {
				if (!originalWindow.contentEquals(windowHandle)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}
			driver.manage().timeouts().implicitlyWait(30L, TimeUnit.MILLISECONDS);

			// select transcript level
			WebElement comboboxElement = driver
					.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr[1]/td[2]/select"));

			Utils.takeScreenShot(driver, "Scenario-5 - Before clicking on my type of transcript");
			// Click on the combobox to open the dropdown list
			comboboxElement.click();

			// Create a Select object from the combobox element
			Select select = new Select(comboboxElement);

			// Select an option by visible text
			select.selectByVisibleText("Graduate");

			Utils.takeScreenShot(driver, "Scenario-5 - After selecting graduate option");

			Thread.sleep(2000);

			// click on submit button
			WebElement submitBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/form/input")));

			Utils.takeScreenShot(driver, "Scenario-5 - Clicking submit button");

			submitBtn.click();

			Thread.sleep(5000);

			Utils.takeScreenShot(driver, "Scenario-5 - Right click and Save");
			// performing actions to download
			WebElement body = driver.findElement(By.tagName("body"));

			Actions actions = new Actions(driver);
			actions.moveToElement(body).perform(); // move mouse to body element
			actions.contextClick().perform(); // right-click on body element

			Robot robot;
			try {
				robot = new Robot();
				robot.keyPress(KeyEvent.VK_P); // press "P" key
				robot.keyRelease(KeyEvent.VK_P); // release "P" key
				Thread.sleep(5000); // wait for context menu to appear
				robot.keyPress(KeyEvent.VK_ENTER); // press "Enter" key
				robot.keyRelease(KeyEvent.VK_ENTER); // release "Enter" key
				robot.keyPress(KeyEvent.VK_ENTER); // press "Enter" key
				robot.keyRelease(KeyEvent.VK_ENTER); // release "Enter" key
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(2000);
//	     driver.quit();
	
		
	}

	

}
