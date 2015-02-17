package org.catalystitservices.PageObjectFramework.Framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumDriver {
	
	protected static WebDriver _driver;

	public static WebDriver getDriver()
	{
		if (_driver == null)
		{
			_driver = new FirefoxDriver();
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
