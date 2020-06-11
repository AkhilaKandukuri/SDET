package resource;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resource.DataFromExcl;
import pageObjects.RegisterPageObjects;
import pageObjects.SigninPageObjects;

public class OlayTest extends Base {
	
	Logger log=LogManager.getLogger(OlayTest.class);
	
	@Test(dataProvider = "sendData")
	public void validate(String url) throws IOException,InterruptedException
	{
		//BasicConfigurator.configure();
		DataFromExcl dt=new DataFromExcl();
		int cou=dt.getRowCount();
		while(cou!=1)
		{
		//System.out.println("cou="+cou);
		log.info("count of rows remaining "+ (cou-1));
		ArrayList<String> a=  dt.getData();
		log.info("User details : "+a);
		driver = new ChromeDriver();
        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		driver.get(url);
		
		log.info("current url "+driver.getCurrentUrl());
		log.error("Url launching error");
		RegisterPageObjects pg=new RegisterPageObjects(driver);
		pg.register().click();
		
		log.info("Register page opens");
		if (url.equals("https://www.olay.es/es-es")) {
			spanish(pg, a);
		}
		if (url.equals("https://www.olaz.de/de-de")) {
			german(pg, a);
		}
				
		/*String em=a.get(0);
		System.out.println(em);
		String arr[]=em.split("@");
		String email=arr[0]+(int)(Math.random()*10);
		System.out.println(email);*/
		
		pg.remail().sendKeys(a.get(0));
		pg.rpass().sendKeys(a.get(1));
		pg.rcpass().sendKeys(a.get(2));
		
		if (url.equals("https://www.olaz.de/de-de")) {
			pg.deday(a);
			pg.demon(a);
			pg.deyr(a);
		}

		else {
			pg.day(a);
			pg.month(a);
			pg.year(a);
		}
		
		log.info("Entered registration details");		
		
		Thread.sleep(3000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		if(url.contains("de-de"))
		{
			js.executeScript("window.scrollBy(0,400)");
		}
		pg.submitbutton().click();
		
		log.info("Clicks on submit button");
		
		String regtxt = driver.findElement(By.xpath("//span[@id='phdesktopbody_0_ErrorMessage']")).getText();
		if (regtxt.equalsIgnoreCase("An account with this email address already exists.")
				|| regtxt.equals("Dirección de correo electrónico ya está en uso.") || 
				regtxt.equals("Wir haben unter dieser Email bereits einen Account für Sie bei P&G, z.B. weil Sie sich über eine andere P&G Marken- oder Programm-Webseite registriert haben. Bitte geben Sie Ihr Passwort ein."))
		{
			
			
			log.info("As the details alreeady exists it is navigated to signin page");
			signin(a,url);
			/*String st=driver.findElement(By.xpath("//span[@id='phdesktopbody_0_Message']")).getText();
			if(st.equals("The email and password combination you entered is incorrect. Please try again, or click the link below to create an account."))
			{
				driver.findElement(By.className("forgotpwd")).click();
				driver.findElement(By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_username']")).sendKeys(a.get(0));
				
				driver.findElement(By.xpath("//input[@name='phdesktopbody_0$NEXT']")).click();
			}*/
			
		}
		
		
		cou=cou-1;
		Thread.sleep(2000);
		driver.close();
	}
	
	}
	
	
	
	
	@DataProvider
	public Object sendData() throws IOException
	{
		driver=initDriver();
		//log.info("Browser initialized");
		Object[][] data =new Object[3][1];
		data[0][0] = prop.getProperty("engUrl");
		data[1][0] = prop.getProperty("germanUrl");
		data[2][0] = prop.getProperty("spanishUrl");
		return data;
		
	}
	
	
	public void german(RegisterPageObjects pg, ArrayList<String> a) {
		pg.gender().click();
		pg.fname().sendKeys(a.get(6));
		pg.lname().sendKeys(a.get(7));
		Select sel = new Select(driver.findElement(
				By.xpath("//select[@name='phdesktopbody_0$phdesktopbody_0_labelgrs_account[addresses][0][country]']")));
		sel.selectByValue("AUT");
		pg.addline1().sendKeys(a.get(8));
		pg.postal().sendKeys(a.get(9));
		pg.city().sendKeys(a.get(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}

	public void spanish(RegisterPageObjects pg, ArrayList<String> a) {
		pg.gender().click();
		pg.fname().sendKeys(a.get(6));
		pg.lname().sendKeys(a.get(7));

	}
	
	public void signin(ArrayList<String> a,String url) {
		SigninPageObjects si = new SigninPageObjects(driver);
		si.login().click();
		si.uname().sendKeys(a.get(0));
		si.password().sendKeys(a.get(1));
		if(url.contains("en-gb"))
		si.signin().click();
		else if(url.contains("es-es"))
			si.spsubmit().click();
		else if(url.contains("de-dee"))
			si.desubmit().click();
		
	}

}
