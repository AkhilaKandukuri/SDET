package resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	public WebDriver driver;
	public Properties prop;
	
	public WebDriver initDriver() throws IOException
	{
		prop=new Properties();
		FileInputStream fis=new FileInputStream("E:\\eclipse\\workspace\\Selenium\\src\\main\\java\\resource\\data.properties");
		prop.load(fis);
		prop.getProperty("browser");
		String browser=prop.getProperty("browser");
		if(browser.equalsIgnoreCase("Mozilla"))
        {
        System.setProperty("webdriver.gecko.driver", "E:\\eclipse\\workspace\\Selenium\\Drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("Chrome"))
        {
        	System.setProperty("webdriver.chrome.driver","E:\\eclipse\\workspace\\Selenium\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        }
		
		//driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		return driver;
	}
}
