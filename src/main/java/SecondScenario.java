
/**
 * @author Dinesh
 **/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import java.util.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondScenario {

//
	@Test
	public void login() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/dineshmurugesan/Downloads/chromedriver-mac-arm64/chromedriver");
		WebDriver driver = new ChromeDriver();

		
		driver.get("https://northeastern.instructure.com");
		driver.manage().window().maximize();
		Thread.sleep(800);
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		

		WebElement name = driver.findElement(By.id("username"));
		name.sendKeys("murugesan.d");
        driver.findElement(By.id("password")).sendKeys("Msdvilla@641030");

        driver.findElement(By.className("form-button")).click();
        
        //Thread.sleep(20000);
        
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("global_nav_calendar_link")));
        element.click();
		
		driver.findElement(By.id("create_new_event_link")).click();
		Thread.sleep(800);
		driver.findElement(By.id("ui-id-5")).click();
		driver.findElement(By.id("planner_note_title")).sendKeys("Class presentation");
		driver.findElement(By.id("planner_note_time")).sendKeys("11:30 AM");
		driver.findElement(By.id("details_textarea")).sendKeys("Selenium Presenation");
		driver.findElements(By.xpath("(//button[@type='submit'])[2]")).get(0).click();
		Thread.sleep(1000);
		driver.findElement(By.className("fc-event-container")).click();

		driver.close();
//    driver.quit();
	}

}
