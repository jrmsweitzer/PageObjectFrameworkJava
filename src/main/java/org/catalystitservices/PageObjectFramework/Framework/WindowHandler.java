package org.catalystitservices.PageObjectFramework.Framework;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class WindowHandler {
	
	private WebDriver _driver;
	private Map<String, String> _windowHandles;
	
	private int currentWindowHandleCount = 0;
	public static final String MainWindowHandle = "Main Window";
	
	public WindowHandler(WebDriver driver)
	{
		_driver = driver;
		setStartingWindowHandle();
	}
	
	public void addNewWindowHandle(String handleName)
	{
		int windowCount = getWindowHandleCount();
		
		if (windowCount > currentWindowHandleCount + 1)
		{
			Assert.fail(String.format("Error: Unmatched window counts. Expected there"
					+ "to be %s handles, but there are currently %s handles", 
					currentWindowHandleCount + 1, windowCount));
		}
		
		for (int i = 0; i < windowCount; i++)
		{
			_driver.switchTo().window(
					(String) _driver.getWindowHandles().toArray()[i]);
			String handle = _driver.getWindowHandle();
			if(!_windowHandles.containsValue(handle))
			{
				_windowHandles.put(handleName, handle);
				currentWindowHandleCount++;
			}
		}
	}
	
	public int getWindowHandleCount()
	{
		return _driver.getWindowHandles().size();
	}
	
	private void setStartingWindowHandle()
	{
		_windowHandles = new HashMap<String, String>();
		if(getWindowHandleCount() > 1)
		{
            Assert.fail(String.format(
                "You must call this method before opening other windows! There are currently %d window handles!",
                getWindowHandleCount()));
		}
		
		_windowHandles.put(MainWindowHandle, _driver.getWindowHandle());
		currentWindowHandleCount = 1;
	}
	
	public void switchToHandle(String handleName)
	{
		if(!_windowHandles.containsKey(handleName))
		{
            Assert.fail(String.format(
                "Window Handle with name %s has not been saved. Call AddNewWindowHandle(handleName) to add the handle.",
                handleName));
		}
		
		String targetHandle = _windowHandles.get(handleName);
		_driver.switchTo().window(targetHandle);
	}
}
