package org.catalystitservices.PageObjectFramework.Framework;

public class SeleniumSettings {
	
	public static String FIREFOX = "firefox";
	public static String CHROME = "chrome";
	public static String IE = "ie";

	private static String _browser = FIREFOX;
	
	private static int _defaultTimeout = 5;
	private static String _chromeDriver = "src/main/java/org/catalystitservices/PageObjectFramework/Drivers/chromedriver.exe";
	private static String _ieDriver = "src/main/java/org/catalystitservices/PageObjectFramework/Drivers/IEDriverServer.exe";

    public static int getDefaultTimeout()
    {
    	return _defaultTimeout;
    }
    
    public static String getBrowser()
    {
    	return _browser;
    }

    public static String getDriver(String browser)
    {
    	if (browser == CHROME)
    	{
    		return _chromeDriver;
    	}
    	else if (browser == IE)
    	{
    		return _ieDriver;
    	}
    	else
    	{
    		return null;
    	}
    }
}
