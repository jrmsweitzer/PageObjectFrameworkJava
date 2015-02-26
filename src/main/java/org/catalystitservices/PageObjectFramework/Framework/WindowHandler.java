package org.catalystitservices.PageObjectFramework.Framework;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

/// <summary>
/// A mechanism for controlling the window handles.
/// </summary>
public class WindowHandler {
    // This class is designed to help you control multiple windows with Selenium.
	
	private WebDriver _driver;
	private Map<String, String> _windowHandles;
	
	private int currentWindowHandleCount = 0;
	public static final String MainWindowHandle = "Main Window";
	
	public WindowHandler(WebDriver driver)
	{
		_driver = driver;
		setStartingWindowHandle();
	}

    /// <summary>
    /// Adds a window handle to the private dictionary _windowHandles, to allow you
    /// to switch to the window by it's new name. Call this method every
    /// time a new window appears in your code.
    /// <para>@param handleName - the name of the newly added window handle. Can be anything you like.</para>
    /// </summary>
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

    /// <summary>
    /// Gets the current window handle count
    /// </summary>
	public int getWindowHandleCount()
	{
		return _driver.getWindowHandles().size();
	}

    /// <summary>
    /// This is a private method called from the constructor of a new 
    /// WindowHandle object. This creates a new _windowHandle Dictionary,
    /// and adds the first handle into it.
    /// </summary>
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

    /// <summary>
    /// Switches control to the specified window handle.
    /// Must call AddWindowHandle(handleName) to add a handle with
    /// the given name first.
    /// <para>@param handleName - the name of the window handle</para>
    /// </summary>
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
