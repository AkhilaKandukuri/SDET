package resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class MakeMyTripTest extends Base{
	
	//private static Logger log=LogManager.getLogger(MakeMyTripTest.class.getName());
	@Test
	public void makeMyTripTest() throws InterruptedException, IOException
	{
		Logger log=LogManager.getLogger(MakeMyTripTest.class);
		driver=initDriver();
		//WebDriverWait w=new WebDriverWait(driver,20);
		driver.get(prop.getProperty("tripurl"));
		log.info("MakeMyTrip site launhched");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//label[@for='fromCity']")).click();
		WebElement wb1=driver.findElement(By.xpath("//input[@placeholder='From']"));
		wb1.click();
		//wb1.clear();
		wb1.sendKeys("Chennai");
		WebDriverWait w=new WebDriverWait(driver,40);
		w.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='makeFlex hrtlCenter']/div/p[1]"), "Chennai, India"));
		wb1.sendKeys(Keys.ARROW_DOWN);
		wb1.sendKeys(Keys.ENTER);
		
		log.info("From location is selected");
		
		WebElement wb2=driver.findElement(By.xpath("//input[@placeholder='To']"));
		wb2.sendKeys("ben");
		w.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='makeFlex hrtlCenter']/div/p[1]"), "Bengaluru, India"));
		
		wb2.sendKeys(Keys.ARROW_DOWN);
		wb2.sendKeys(Keys.ENTER);
		log.info("To location is selected");
		
		String mon ="August";
		String dt="5";
		
		while(!driver.findElement(By.xpath("(//div[@class='DayPicker-Month'])[1]/div[1]/div")).getText().contains(mon))
		{
			driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
		}
		
		
		int cou=driver.findElements(By.cssSelector(".dateInnerCell p")).size();
		for(int i=0;i<cou;i++)
		{
			String d=driver.findElements(By.cssSelector(".dateInnerCell p")).get(i).getText();
			if(d.contains(dt))
			{
				driver.findElements(By.cssSelector(".dateInnerCell p")).get(i).click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
		Thread.sleep(10000);
		List<WebElement> fare=driver.findElements(By.xpath("//span[@class='actual-price']"));
		List<String> l= new ArrayList<String>();
		int c=fare.size();
		System.out.println(c);
		for(int i=0;i<c;i++)
		{
			l.add(driver.findElements(By.xpath("//span[@class='actual-price']")).get(i).getText());
		}
		
		log.info("dispayed the flights");
		Collections.sort(l);
		System.out.println(l.get(0));
		
		driver.findElement(By.xpath("(//div[@class='pull-left make_relative'])[2]")).click();
		
		
		
	}

}
