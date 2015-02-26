package org.catalystitservices.PageObjectFramework.Framework;

import java.util.Collection;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PageObject {
	
	protected WebDriver _driver;
	protected String _url;
	protected String _title;
	
	private int _defaultTimeout = SeleniumSettings.getDefaultTimeout();
	
	protected WindowHandler _windowHandler;
	
	public PageObject(WebDriver driver)
	{
		_driver = driver;
		_windowHandler = new WindowHandler(_driver);
	}
	
	// Shared XPaths
	protected static By body = By.xpath("//body");
	protected static By title = By.xpath("//title");
	
	protected void clear(By by)
	{
		find(by).clear();
	}
	
	protected void clearAndSendKeys(By by, String value)
	{
		clear(by);
		sendKeys(by, value);
	}
	
	protected void click(By by)
	{
		find(by).click();
	}
	
	protected WebElement find(By by)
	{
		return _driver.findElement(by);
	}
	
	protected Collection<WebElement> findAll(By by)
	{
		return _driver.findElements(by);
	}
	
	protected String getInnerHtml(By by)
	{
		return find(by).getAttribute("innerHTML");
	}
	
	protected String getText(By by)
	{
		return find(by).getText();
	}
	
	public String getTitle()
	{
		return getInnerHtml(title);
	}
	
	public String getUrl()
	{
		return _driver.getCurrentUrl();
	}
	
	public void goTo(String url)
	{
		goTo(url, "optionalTitle");
	}
	
	public void goTo(String url, String expectedTitle)
	{
		_driver.get(url);
		
		if("optionalTitle" != expectedTitle &&
				!getTitle().contains(expectedTitle))
		{
			String errMsg = String.format(
					"PageObject: We're not on the expected page! " +
					"Expected: %s; Actual: %s",
					expectedTitle, getTitle());
			Assert.fail(errMsg);
		}
	}
	
	protected void sendKeys(By by, String value)
	{
		find(by).sendKeys(value);
	}
	
	protected void selectByText(By by, String optionText)
	{
		Select select = new Select(find(by));
		if (!select.equals(null))
		{
			try
			{
				select.selectByVisibleText(optionText);
			}
			catch (Exception ex)
			{
				String errMsg = String.format(
						"PageObject: There is no option '%s' in '%s'.",
						optionText, by);
				Assert.fail(errMsg);
			}
		}
		else
		{
			String errMsg = "Cannot find element " + by.toString();
			throw new ElementNotVisibleException(errMsg);
		}
	}
	
	public void sleep(long timeout)
	{
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void submit(By by)
	{
		find(by).submit();
	}
	
	protected void waitForElementToBeDeleted(By by)
	{
		waitForElementToBeDeleted(by, _defaultTimeout);
	}
	
	protected void waitForElementToBeDeleted(By by, long timeout)
	{
		long startTime = System.currentTimeMillis();
		
		while(findAll(by).size() > 0)
		{
			if (System.currentTimeMillis() - startTime > timeout)
			{
				Assert.fail(String.format("Element '%s' was still visible after %s seconds",
						by.toString(),
						timeout / 1000));
			}
		}
	}
	
	protected void waitForElementToExist(By by)
	{
		waitForElementToExist(by, _defaultTimeout);
	}
	
	protected void waitForElementToExist(By by, long timeout)
	{
		long startTime = System.currentTimeMillis();
		
		while(findAll(by).size() == 0)
		{
			if (System.currentTimeMillis() - startTime > timeout)
			{
				Assert.fail(String.format("Could not find element '%s' after %s seconds",
						by.toString(),
						timeout / 1000));
			}
		}
	}

    protected void waitForPartialUrl(String partialUrl)
    {
        waitForPartialUrl(partialUrl, _defaultTimeout);
    }
    
    protected void waitForPartialUrl(String partialUrl, int timeout)
    {
		long startTime = System.currentTimeMillis();
		
		while(!getUrl().contains(partialUrl))
		{
			if (System.currentTimeMillis() - startTime > timeout)
			{
                String errMsg = String.format(
                        "Url did not contain string '%s' after %d seconds!",
                        partialUrl, timeout / 1000);
                Assert.fail(errMsg);
			}
		}
    }

    protected void waitForTitle(String expectedTitle)
    {
        waitForPartialUrl(expectedTitle, _defaultTimeout);
    }
    
    protected void waitForTitle(String expectedTitle, int timeout)
    {
		long startTime = System.currentTimeMillis();
		
		while(!getTitle().contains(expectedTitle))
		{
			if (System.currentTimeMillis() - startTime > timeout)
			{
                String errMsg = String.format(
                        "Title did not contain string '%s' after %d seconds!",
                        expectedTitle, timeout / 1000);
                Assert.fail(errMsg);
			}
		}
    }
	
	protected void waitForUrl(String url)
	{
		waitForUrl(url, _defaultTimeout);
	}
	
	protected void waitForUrl(String url, long timeout)
	{
		long startTime = System.currentTimeMillis();
		
		while(getUrl() != url)
		{
			if (System.currentTimeMillis() - startTime > timeout)
			{
				Assert.fail(String.format("Was not on url '%s' after %s seconds",
						url,
						timeout / 1000));
			}
		}
	}
}
