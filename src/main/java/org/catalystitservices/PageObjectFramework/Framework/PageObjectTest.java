package org.catalystitservices.PageObjectFramework.Framework;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class PageObjectTest extends SeleniumDriver {

	@BeforeClass
	public static void BeforeClass()
	{
	}
	
	@Before
	public void BeforeEach()
	{
		_driver = SeleniumDriver.getDriver();
	}
	
	@After
	public void AfterEach()
	{
		_driver.manage().deleteAllCookies();
		_driver.quit();
		_driver = null;
	}
	
	@AfterClass
	public static void AfterClass()
	{
	}
}
