package resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resource.Base;

public class AssQ1Test extends Base {
	
	@BeforeMethod
	public void driverObj() throws IOException
	{
		driver=initDriver();
	}
	
	@Test(priority=1)
	public void itemcount() throws IOException, InterruptedException
	{
		
		//log.trace("entered itemcount method");
		String url=prop.getProperty("selectableurl");
		
		driver.get(url);
		//log.info("Url launched");	
		List<WebElement> li=driver.findElements(By.xpath("//li[@class='ui-widget-content ui-selectee']"));
		//log.error("Itemcount received");
		
		for(int i=0;i<li.size()-1;i++)
		{
			
			Assert.assertFalse(driver.findElements(By.xpath("//li[@class='ui-widget-content ui-selectee']")).get(i).isSelected());
			driver.findElements(By.xpath("//li[@class='ui-widget-content ui-selectee']")).get(i).click();
			System.out.println(driver.findElement(By.xpath("//li[@class='ui-widget-content ui-selectee ui-selected']")).getText());
			
			
		}
				
	}
	@Parameters({"fname","lname","country"})
	@Test(priority=2)
	public void formfill(String fname,String lname,String country ) throws IOException
	{
			
		String url= prop.getProperty("contactform");
		System.out.println(prop.getProperty("contactform"));
		driver.get(url);
		driver.findElement(By.className("firstname")).sendKeys(fname);
		driver.findElement(By.id("lname")).sendKeys(lname);
		driver.findElement(By.xpath("//input[@name='country']")).sendKeys(country);
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		/*driver.findElement(By.linkText("Google Link")).sendKeys(Keys.CONTROL+"t");
		 ((JavascriptExecutor)driver).executeScript("window.open()");
	        ;*/
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
		driver.findElement(By.linkText("Google Link")).sendKeys(selectLinkOpeninNewTab);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
	        driver.switchTo().window(tabs.get(0));
	        driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		
	}
	
	
	@Test(priority=3)
	public void dragNdrop()
	{
		String url= prop.getProperty("droppableurl");
		//System.out.println(prop.getProperty("contactform"));
		driver.get(url);
		WebElement from = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement to = driver.findElement(By.xpath("//div[@id='droppable']"));
		Actions act=new Actions(driver);
		act.dragAndDrop(from,to).build().perform();
	}
	
	@Parameters({"yr","mon","day"})
	@Test(priority=4)
	public void datePicker(String yr,String mon,String day)
	{
		driver.get(prop.getProperty("datepicker"));
		
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		
		while(!driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText().contains(yr) )
		{
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
			
		}
		while(!driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText().contains(mon))
		{
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
		}
		
		List<WebElement> li=driver.findElements(By.xpath("//a[@class='ui-state-default']"));
		for(int i=0;i<li.size();i++)
		{
			if(li.get(i).getText().equals(day))
			{
				
				driver.findElements(By.xpath("//a[@class='ui-state-default']")).get(i).click();
				break;
			}
		}
		
	}
	
	@Test(priority=5)
	public void selectMenu() throws InterruptedException
	{
		
		driver.get(prop.getProperty("selectmenu"));
		WebElement wb=driver.findElement(By.xpath("//span[@tabindex='0']"));
		wb.click();
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='ui-id-5']")).isSelected());
		driver.findElement(By.xpath("//div[@id='ui-id-5']")).click();
		
		Assert.assertFalse(driver.findElement(By.xpath("//span[@id='files-button']")).isSelected());
		driver.findElement(By.xpath("//span[@id='files-button']")).click();
		Thread.sleep(2000);
		
		Assert.assertFalse(driver.findElement(By.xpath("//ul[@id='files-menu']/li[5]")).isSelected());
		driver.findElement(By.xpath("//ul[@id='files-menu']/li[5]")).click();
		
		Assert.assertFalse(driver.findElement(By.cssSelector("#salutation-button")).isSelected());
		driver.findElement(By.cssSelector("#salutation-button")).click();
		
		Assert.assertFalse(driver.findElement(By.xpath("//ul[@id='salutation-menu']/li[4]")).isSelected());
		driver.findElement(By.xpath("//ul[@id='salutation-menu']/li[4]")).click();
		
	}
	
	
	@Test(priority=6)
	public void controlGrp()
	{
		
		driver.get(prop.getProperty("rentalcar"));
		WebElement wb4= driver.findElement(By.xpath("//span[@id='ui-id-8-button']"));
		wb4.click();
		driver.findElement(By.xpath("//div[@id='ui-id-12']")).click();
		
		Assert.assertFalse(driver.findElement(By.xpath("(//label[text()[contains(.,'Automatic')]])[2]")).isSelected());
		driver.findElement(By.xpath("(//label[text()[contains(.,'Automatic')]])[2]")).click();
		
		Assert.assertFalse(!driver.findElement(By.xpath("(//label[text()[contains(.,'Insurance')]])[2]")).isSelected());
		driver.findElement(By.xpath("(//label[text()[contains(.,'Insurance')]])[2]")).click();
	
		
		for(int i=0;i<20;i++)
		{
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			System.out.println(driver.findElement(By.xpath("//span[@class='ui-widget ui-widget-content ui-spinner ui-controlgroup-item']/input[@id='vertical-spinner']")).getText());
			driver.findElement(By.xpath("(//span[@class='ui-button-icon ui-icon ui-icon-triangle-1-n'])[2]")).click();
		}
		
		Assert.assertFalse(driver.findElement(By.id("book")).isSelected());
		driver.findElement(By.id("book")).click();
	}
	
	
	
	
	
}
