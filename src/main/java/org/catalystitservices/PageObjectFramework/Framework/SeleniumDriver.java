package org.catalystitservices.PageObjectFramework.Framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumDriver {
	
	protected static WebDriver driver;

	public static WebDriver getDriver()
	{
		if (driver == null)
		{
			if (SeleniumSettings.getBrowser() == SeleniumSettings.FIREFOX)
			{
				driver = new FirefoxDriver();				
			}
			else if (SeleniumSettings.getBrowser() == SeleniumSettings.CHROME)
			{
				System.setProperty("webdriver.chrome.driver", 
						SeleniumSettings.getDriver(SeleniumSettings.CHROME));
				driver = new ChromeDriver();
				
			}
			else
			{
				System.setProperty("webdriver.ie.driver", 
						SeleniumSettings.getDriver(SeleniumSettings.IE));
				driver = new InternetExplorerDriver();
			}
			configureDriver();
		}
		return driver;
	}
	
	private static void configureDriver()
	{
		setTimeout();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
	private static void setTimeout()
	{
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
}
