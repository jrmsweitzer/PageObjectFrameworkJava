package org.catalystitservices.PageObjectFramework.Framework;

public class SeleniumSettings {
	
	public static String FIREFOX = "firefox";
	public static String CHROME = "chrome";
	public static String IE = "ie";

	private static String browser = CHROME;
	
	private static int defaultTimeout = 5;
	private static String logDirectory = "C:\\Users\\"+System.getProperty("user.name")+"\\Selenium\\Logs\\";
	private static String seleniumLog = "SeleniumLog";
	private static String actionLog = "Actions";
	private static boolean logAllActions = true;
	
	private static String chromeDriver = "src/main/java/org/catalystitservices/PageObjectFramework/Drivers/chromedriver.exe";
	private static String ieDriver = "src/main/java/org/catalystitservices/PageObjectFramework/Drivers/IEDriverServer.exe";

    public static String getActionLogName()
    {
    	return actionLog;
    }
	
    public static String getBrowser()
    {
    	return browser;
    }
    
    public static int getDefaultTimeout()
    {
    	return defaultTimeout;
    }

    public static String getDriver(String browser)
    {
    	if (browser == CHROME)
    	{
    		return chromeDriver;
    	}
    	else if (browser == IE)
    	{
    		return ieDriver;
    	}
    	else
    	{
    		return null;
    	}
    }
    
    public static String getLogDirectory()
    {
    	return logDirectory;
    }
    
    public static String getSeleniumLogName()
    {
    	return seleniumLog;
    }
    
    public static boolean logAllActions()
    {
    	return logAllActions;
    }
}
