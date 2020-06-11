package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class RegisterPageObjects {
	
	 WebDriver driver;
	public RegisterPageObjects(WebDriver driver)
	{
		this.driver=driver;
	}
	By fname=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_consumer[firstname]']");
	By lname=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_consumer[lastname]']");
	By register=By.partialLinkText("Regist");
	By remail= By.cssSelector("input[id='phdesktopbody_0_grs_account[emails][0][address]']");
	By rpass = By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_account[password][password]']");
	By rcpass = By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_account[password][confirm]']");
	By month= By.xpath("//select[@name='phdesktopbody_0$phdesktopbody_0_grs_consumer[birthdate][month]']");
	By day=By.xpath("//select[@name='phdesktopbody_0$phdesktopbody_0_grs_consumer[birthdate][day]']");
	By year=By.xpath("//select[@name='phdesktopbody_0$phdesktopbody_0_grs_consumer[birthdate][year]']");
	By addline1=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_labelgrs_account[addresses][0][line1]']");
	By addline2= By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_account[addresses][0][postalarea]']");
	By addline3= By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_labelgrs_account[addresses][0][city]']");
	By submit= By.id("phdesktopbody_0_submit");
	By gender = By.xpath("//img[@id='phdesktopbody_0_imgfemale']");
	By deday=By.xpath("//select[@name='phdesktopbody_0$ctl72']");
	By demon=By.xpath("//select[@name='phdesktopbody_0$ctl74']");
	By deyr=By.xpath("//select[@name='phdesktopbody_0$ctl76']");
	public WebElement gender()
	{
		return driver.findElement(gender);
	}
	public void deday(ArrayList<String> a)
	{
		Select s=new Select(driver.findElement(deday));
		s.selectByVisibleText(a.get(3));
			
	}
	public void demon(ArrayList<String> a)
	{
		Select s=new Select(driver.findElement(demon));
		s.selectByVisibleText(a.get(4));
		
	}
	public void deyr(ArrayList<String> a)
	{
		Select s=new Select(driver.findElement(deyr));
		s.selectByVisibleText(a.get(5));
		
	}
	public WebElement register()
	{
		return driver.findElement(register);
	}
	public WebElement fname()
	{
		return driver.findElement(fname);
	}
	
	public WebElement lname()
	{
		return driver.findElement(lname);
	}
	
	public WebElement remail()
	{
		return driver.findElement(remail);
	}
	
	public WebElement rpass()
	{
		return driver.findElement(rpass);
	}

	public WebElement rcpass()
	{
		return driver.findElement(rcpass);
	}
	
	public void day(ArrayList<String> a)
	{
		//WebElement daylist=driver.findElement(day).click();
		Select s=new Select(driver.findElement(day));
		s.selectByVisibleText(a.get(3));
		
	}
	
	public void month(ArrayList<String> a)
	{
		//WebElement daylist=driver.findElement(day).click();
		Select s=new Select(driver.findElement(month));
		s.selectByVisibleText(a.get(4));
		
	}
	
	public void year(ArrayList<String> a)
	{
		//WebElement daylist=driver.findElement(day).click();
		Select s=new Select(driver.findElement(year));
		s.selectByVisibleText(a.get(5));
		
	}
	
	public WebElement addline1()
	{
		return driver.findElement(addline1);
	}
	
	public WebElement postal()
	{
		return driver.findElement(addline2);
	}
	
	public WebElement city()
	{
		return driver.findElement(addline3);
	}
	public WebElement submitbutton()
	{
		return driver.findElement(submit);
	}
}
