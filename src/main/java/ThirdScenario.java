


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ThirdScenario {
	
	private static String username;
    private static String password;
	//
	@Test
	public void login() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/dineshmurugesan/Downloads/chromedriver_mac_arm64/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//logging into classroom guide website
		driver.get("https://service.northeastern.edu/tech?id=classrooms");
		driver.manage().window().maximize();
		
		XSSFWorkbook srcBook;
		try {
			srcBook = new XSSFWorkbook("C:/Users/Pratik/Downloads/login_details.xlsx");
			XSSFSheet sourceSheet = srcBook.getSheetAt(0);
	        //int rownum=rowcounter;
	        XSSFRow sourceRow = sourceSheet.getRow(1);
	        XSSFCell cell1=sourceRow.getCell(0);
	        
	     // load data
	        DataFormatter formatter = new DataFormatter();
	        Row row = sourceSheet.getRow(1);
	        for (Cell cell : row) {
	          
	            username = formatter.formatCellValue(sourceRow.getCell(0));
	            password = formatter.formatCellValue(sourceRow.getCell(1));
	            
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
        
		
		byte[] decodeBytes = Base64.getDecoder().decode(password.getBytes());

        String decryptedPassword = new String(decodeBytes);
		
		
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(decryptedPassword);
		driver.findElement(By.xpath("/html/body/section/div/div[1]/div/form/div[3]/button")).click();
	
	Thread.sleep(4000);
	
	
		driver.switchTo().frame("duo_iframe");
		Select select = new Select(driver.findElement(By.name("device")));
		select.selectByValue("phone3");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"auth_methods\"]/fieldset[3]/div[1]/button")).click();
		
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(5000);
		// booking classroom
	
		driver.findElement(By.linkText("007 Behrakis Health Sciences Center")).click();
		
		//opening pdf
		
		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("NUFlex Auto and Manual Classroom")).click();
		
		
		
//	    driver.quit();
		

		
		
		
	}

}
