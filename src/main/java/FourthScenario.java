
/**
 * @author Pratik
 **/

import org.junit.jupiter.api.Test;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.JavascriptExecutor;

public class FourthScenario {

//
	@Test
	public void login() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Pratik/Downloads/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		
		driver.get("https://onesearch.library.northeastern.edu/discovery/search?vid=01NEU_INST:NU&lang=en");
		driver.manage().window().maximize();
		Thread.sleep(800);
		//

		driver.findElements(By.xpath("//*[@id=\"mainMenu\"]/div[5]/a/span")).get(0).click();
		Thread.sleep(800);


		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(800);
		
		js.executeScript("window.scrollBy(0,1000)");
		driver.findElements(By.xpath("//*[@id=\"main-content\"]/div[1]/section/div[1]/a[5]")).get(0).click();
		Thread.sleep(800);


		driver.findElements(By.xpath("//*[@id=\"main-content\"]/div[2]/main/section/ul/article[1]/a/figure/span"))
				.get(0).click();
		Thread.sleep(800);

		driver.findElements(By.xpath("//*[@id=\"metadata\"]/section/div/div/div[1]/a[1]")).get(0).click();
		Thread.sleep(800);

//    driver.quit();
	}

}
