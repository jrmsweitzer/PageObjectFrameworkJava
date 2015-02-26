package org.catalystitservices.PageObjectFramework.Framework;

import java.util.Collection;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/// <summary>
/// The Model Base for all Page Objects.
/// <para> </para>
/// <para>Any class implementing PageObjectModelBase should have a constructor like so:</para>
/// <para>public PageObjectNameGoesHere(IWebDriver driver) : base(driver)</para>
/// <para>{</para>
/// <para>    // Everything in here is optional</para>
/// <para>    _url = "https://www.google.com";</para>
/// <para>    _title = "Google";</para>
/// <para>    GoTo(_url, _title);</para>
/// <para>}</para>
/// </summary>
public class PageObject {
	
    // Driver and Page-specific stuff
	protected WebDriver _driver;
	protected String _url;
	protected String _title;
	
	// Config stuff
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
	
    /// <summary>Clears an input box
    /// <para> @param by - the by selector for the given element</para>
    /// </summary>
	protected void clear(By by)
	{
		find(by).clear();
	}
	
    /// <summary>Clears then inputs data into an input box
    /// <para> @param by - the by selector for the given element</para>
    /// <para> @param value - the text to input into the input box.</para>
    /// </summary>
	protected void clearAndSendKeys(By by, String value)
	{
		clear(by);
		sendKeys(by, value);
	}
	
    /// <summary>Click the element at the given selector.
    /// <para> @param by - the by selector for the given element</para>
    /// </summary>
	protected void click(By by)
	{
		find(by).click();
	}
	
    /// <summary>Finds the element by the given selector
    /// <para> @param by - the by selector for the given element</para>
    /// </summary>
	protected WebElement find(By by)
	{
		return _driver.findElement(by);
	}

    /// <summary> Finds all elements by the given selector
    /// <para> @param by - the by selector for the given element</para>
    /// </summary>	
	protected Collection<WebElement> findAll(By by)
	{
		return _driver.findElements(by);
	}

    /// <summary>Gets everything inside the html tags for the given selector
    /// <para> @param by - the by selector for the given element</para>
    /// </summary>
	protected String getInnerHtml(By by)
	{
		return find(by).getAttribute("innerHTML");
	}

    /// <summary>Gets the text from the given locator
    /// <para> @param by - the by selector for the given element</para>
    /// </summary>	
	protected String getText(By by)
	{
		return find(by).getText();
	}

    /// <summary>
    /// Gets the title of the current page
    /// </summary>
	public String getTitle()
	{
		return getInnerHtml(title);
	}

    /// <summary>
    /// Gets the URL of the current page
    /// </summary>
	public String getUrl()
	{
		return _driver.getCurrentUrl();
	}

    /// <summary>Go to the given URL.
    /// <para> @param url - the full url of the page you want to visit</para>
    /// <para> @param expectedTitle - </para>
    /// <para> An optional title to add. If you supply a title, the test will </para>
    /// <para> fail if the page you end up on doesn't match the given title. </para>
    /// </summary>
	public void goTo(String url)
	{
		goTo(url, "optionalTitle");
	}

    /// <summary>Go to the given URL.
    /// <para> @param url - the full url of the page you want to visit</para>
    /// <para> @param expectedTitle - </para>
    /// <para> An optional title to add. If you supply a title, the test will </para>
    /// <para> fail if the page you end up on doesn't match the given title. </para>
    /// </summary>
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

    /// <summary>Inputs data into an input box
    /// <para> @param by - the by selector for the given element</para>
    /// <para> @param value - the text to input into the input box.</para>
    /// </summary>
	protected void sendKeys(By by, String value)
	{
		find(by).sendKeys(value);
	}

    /// <summary>Selects an option from a select box based on text
    /// <para> @param by - the by selector for the given element </para>
    /// <para> @param optionText - the text to select by </para>
    /// </summary>
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

    /// <summary> Suspends the current thread for a specified time.
    /// <para>@param millisecondsTimeout - the timeout in milliseconds</para>
    /// </summary>
	public void sleep(long timeout)
	{
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /// <summary> Submits form for the given locator.
    /// <para> @param by - the by selector for the given element </para>
    /// </summary>
	protected void submit(By by)
	{
		find(by).submit();
	}

    /// <summary>
    /// Pauses play until a given element is no longer on the DOM.
    /// <para>@param by - the by selector for the given element</para>
    /// <para>@param timeout (optional) - the time, in milliseconds, to wait for the element to be deleted.</para>
    /// <para>If no time is given for the timeout, will use the default timeout.</para>
    /// </summary>
	protected void waitForElementToBeDeleted(By by)
	{
		waitForElementToBeDeleted(by, _defaultTimeout);
	}

    /// <summary>
    /// Pauses play until a given element is no longer on the DOM.
    /// <para>@param by - the by selector for the given element</para>
    /// <para>@param timeout (optional) - the time, in milliseconds, to wait for the element to be deleted.</para>
    /// <para>If no time is given for the timeout, will use the default timeout.</para>
    /// </summary>
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

    /// <summary>
    /// Pauses play until a given element becomes visible.
    /// <para>@param by - the by selector for the given element</para>
    /// <para>@param timeout (optional) - the time, in milliseconds, to wait for the element to exist</para>
    /// <para>If no time is given for the timeout, will use the default timeout.</para>
    /// </summary>
	protected void waitForElementToExist(By by)
	{
		waitForElementToExist(by, _defaultTimeout);
	}

    /// <summary>
    /// Pauses play until a given element becomes visible.
    /// <para>@param by - the by selector for the given element</para>
    /// <para>@param timeout (optional) - the time, in milliseconds, to wait for the element to exist</para>
    /// <para>If no time is given for the timeout, will use the default timeout.</para>
    /// </summary>
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

    /// <summary>
    /// Pauses play until the current Url contains partialUrl string.
    /// <para>@param partialUrl - the partial url of the page to wait for</para>
    /// <para>@param timeout (optional) - the time, in milliseconds, to wait for the url</para>
    /// <para>If no time is given for the timeout, will use the default timeout.</para>
    /// </summary>
    protected void waitForPartialUrl(String partialUrl)
    {
        waitForPartialUrl(partialUrl, _defaultTimeout);
    }

    /// <summary>
    /// Pauses play until the current Url contains partialUrl string.
    /// <para>@param partialUrl - the partial url of the page to wait for</para>
    /// <para>@param timeout (optional) - the time, in milliseconds, to wait for the url</para>
    /// <para>If no time is given for the timeout, will use the default timeout.</para>
    /// </summary>
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

    /// <summary>
    /// Pauses play until the current Title contains expectedTitle string.
    /// <para>@param expectedTitle - the expected title of the page to wait for</para>
    /// <para>@param timeout (optional) - the time, in milliseconds, to wait for the title</para>
    /// <para>If no time is given for the timeout, will use the default timeout.</para>
    /// </summary>
    protected void waitForTitle(String expectedTitle)
    {
        waitForPartialUrl(expectedTitle, _defaultTimeout);
    }

    /// <summary>
    /// Pauses play until the current Title contains expectedTitle string.
    /// <para>@param expectedTitle - the expected title of the page to wait for</para>
    /// <para>@param timeout (optional) - the time, in milliseconds, to wait for the title</para>
    /// <para>If no time is given for the timeout, will use the default timeout.</para>
    /// </summary>
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

    /// <summary>
    /// Pauses play until the page is on the given URL.
    /// <para>@param url - the url of the page to wait for</para>
    /// <para>@param timeout (optional) - the time, in milliseconds, to wait for the url</para>
    /// <para>If no time is given for the timeout, will use the default timeout.</para>
    /// </summary>
	protected void waitForUrl(String url)
	{
		waitForUrl(url, _defaultTimeout);
	}

    /// <summary>
    /// Pauses play until the page is on the given URL.
    /// <para>@param url - the url of the page to wait for</para>
    /// <para>@param timeout (optional) - the time, in milliseconds, to wait for the url</para>
    /// <para>If no time is given for the timeout, will use the default timeout.</para>
    /// </summary>
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
