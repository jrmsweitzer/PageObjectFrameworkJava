package org.catalystitservices.PageObjectFramework.Framework;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class PageObjectTest extends SeleniumDriver {
	
	private static String logName = SeleniumSettings.getSeleniumLogName();
	private static SeleniumLogger logger = SeleniumLogger.getLogger(logName);
	
	private static long suiteStartTime;
	private static long testStartTime;
	
	@Rule public TestName name = new TestName();

	@BeforeClass
	public static void BeforeClass()
	{
		logger.logStartTestSuite();
		suiteStartTime = System.currentTimeMillis();
	}
	
	@Before
	public void BeforeEach()
	{
		logger.logStartTest(name.getMethodName());
		testStartTime = System.currentTimeMillis();
	}
	
	@After
	public void AfterEach()
	{
		driver.manage().deleteAllCookies();
		driver.quit();
		driver = null;
	}
	
	@Rule public TestWatcher watcher = new TestWatcher() {
		@Override
		protected void failed(Throwable e, Description description) {
			logger.logFail(name.getMethodName());
		}
		
		@Override
		protected void succeeded(Description description) {
			logger.logPass(name.getMethodName());
		}
		
		@Override
		protected void finished(Description description) {
			double elapsedTime = ((double)(System.currentTimeMillis() - testStartTime))/1000;
			logger.logTime("Elapsed Time", elapsedTime);
		}
	};
	
	@AfterClass
	public static void AfterClass()
	{
		logger.logFinishTestSuite();
		double totalTime = ((double)(System.currentTimeMillis() - suiteStartTime))/1000;
		logger.logTime("Total Time", totalTime);
		logger.logDashedLine();
		killDrivers();
	}
	
	private static void killDrivers()
	{
		
	}
}
