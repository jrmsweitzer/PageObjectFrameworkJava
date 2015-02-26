package org.catalystitservices.PageObjectFramework.Framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumDriver {
	
	protected static WebDriver _driver;

	public static WebDriver getDriver()
	{
		if (_driver == null)
		{
			if (SeleniumSettings.getBrowser() == SeleniumSettings.FIREFOX)
			{
				_driver = new FirefoxDriver();				
			}
			else if (SeleniumSettings.getBrowser() == SeleniumSettings.CHROME)
			{
				System.setProperty("webdriver.chrome.driver", 
						SeleniumSettings.getDriver(SeleniumSettings.CHROME));
				_driver = new ChromeDriver();
				
			}
			else
			{
				System.setProperty("webdriver.ie.driver", 
						SeleniumSettings.getDriver(SeleniumSettings.IE));
				_driver = new InternetExplorerDriver();
			}
			configureDriver();
		}
		return _driver;
	}
	
	private static void configureDriver()
	{
		setTimeout();
		_driver.manage().deleteAllCookies();
		_driver.manage().window().maximize();
	}
	
	private static void setTimeout()
	{
		_driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
}
