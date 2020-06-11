package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SigninPageObjects {

	WebDriver driver;
	public SigninPageObjects(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By login=By.xpath("//a[@class='event_profile_login']");
	By uname= By.name("phdesktopbody_0$phdesktopbody_0_username");
	By pswd=By.name("phdesktopbody_0$phdesktopbody_0_password");
	By desubmit= By.id("phdesktopbody_0_ANMELDEN");
	By spsubmit=By.id("phdesktopbody_0_INICIAR SESIÓN");
	By signin=By.id("phdesktopbody_0_SIGN IN");
	
	public WebElement spsubmit()
	{
		return driver.findElement(spsubmit);
		
	}
	public WebElement signin()
	{
		
		return driver.findElement(signin);
		
	}
			
	public WebElement login()
	{
		return driver.findElement(login);
		
	}
	public WebElement uname()
	{
		return driver.findElement(uname);
		
	}
	
	public WebElement password()
	{
		return driver.findElement(pswd);
		
	}
	
	public WebElement desubmit()
	{
		
		return driver.findElement(desubmit);
		
	}
	
}
