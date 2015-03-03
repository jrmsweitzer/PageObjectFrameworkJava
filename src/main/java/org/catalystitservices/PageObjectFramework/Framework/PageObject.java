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

	/**
	 * Clears an input box
	 * @param by - the by selector for the given element
	 */
	protected void clear(By by)
	{
		find(by).clear();
	}

	/**
	 * Clears then inputs data into an input box
	 * @param by - the by selector for the given element
	 * @param value - the text to input into the input box.
	 */
	protected void clearAndSendKeys(By by, String value)
	{
		clear(by);
		sendKeys(by, value);
	}
	
	/**
	 * Click the element at the given selector.
	 * @param by - the by selector for the given element
	 */
	protected void click(By by)
	{
		find(by).click();
	}

	/**
	 * Finds the element by the given selector
	 * @param by - the by selector for the given element.
	 * @return WebElement - the element found at the given by.
	 */
	protected WebElement find(By by)
	{
		return _driver.findElement(by);
	}

	/**
	 * Finds all elements by the given selector.
	 * @param by - the by selector for the given element.
	 * @return Collection<WebElement> - a list of all the elements found at the given by.
	 */
	protected Collection<WebElement> findAll(By by)
	{
		return _driver.findElements(by);
	}

	/**
	 * Gets everything inside the html tags for the given selector.
	 * @param by - the by selector for the given element.
	 * @return String - the inner html of the given by.
	 */
	protected String getInnerHtml(By by)
	{
		return find(by).getAttribute("innerHTML");
	}

	/**
	 * Gets the text from the given locator.
	 * @param by - the by selector for the given element.
	 * @return String - the text at the given by.
	 */
	protected String getText(By by)
	{
		return find(by).getText();
	}

	/**
	 * Gets the title of the current page.
	 * @return String - the title at the current page.
	 */
	public String getTitle()
	{
		return getInnerHtml(title);
	}

	/**
	 * Gets the URL of the current page.
	 * @return String - the current URL.
	 */
	public String getUrl()
	{
		return _driver.getCurrentUrl();
	}

	/**
	 * Go to the given URL.
	 * @param url - the full url of the page you want to visit
	 */
	public void goTo(String url)
	{
		goTo(url, "optionalTitle");
	}

	/**
	 * Go to the given URL.
	 * @param url - the full url of the page you want to visit
	 * @param expectedTitle - An optional title to add. If you supply a title, the test will
	 * fail if the page you end up on doesn't match the given title. 
	 */
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

	/**
	 * Inputs data into an input box
	 * @param by - the by selector for the given element
	 * @param value - the text to input into the input box.
	 */
	protected void sendKeys(By by, String value)
	{
		find(by).sendKeys(value);
	}

	/**
	 * Selects an option from a select box based on text
	 * @param by - the by selector for the given element
	 * @param optionText - the text to select by
	 */
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

	/**
	 * Suspends the current thread for a specified time.
	 * @param timeout - the timeout in milliseconds
	 */
	public void sleep(long timeout)
	{
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Submits form for the given locator.
	 * @param by - the by selector for the given element
	 */
	protected void submit(By by)
	{
		find(by).submit();
	}

	/**
	 * Pauses play until a given element is no longer on the DOM.
	 * @param by - the by selector for the given element
	 */
	protected void waitForElementToBeDeleted(By by)
	{
		waitForElementToBeDeleted(by, _defaultTimeout);
	}

	/**
	 * Pauses play until a given element is no longer on the DOM.
	 * @param by - the by selector for the given element
	 * @param timeout (optional) - the time, in milliseconds, to wait for the element to be deleted.
	 * If no time is given for the timeout, will use the default timeout.
	 */
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

	/**
	 * Pauses play until a given element becomes visible.
	 * @param by - the by selector for the given element
	 */
	protected void waitForElementToExist(By by)
	{
		waitForElementToExist(by, _defaultTimeout);
	}

	/**
	 * Pauses play until a given element becomes visible.
	 * @param by - the by selector for the given element
	 * @param timeout (optional) - the time, in milliseconds, to wait for the element to exist
	 * If no time is given for the timeout, will use the default timeout.
	 */
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

	/**
	 * Pauses play until the current Url contains partialUrl string.
	 * @param partialUrl - the partial url of the page to wait for
	 */
    protected void waitForPartialUrl(String partialUrl)
    {
        waitForPartialUrl(partialUrl, _defaultTimeout);
    }

    /**
     * Pauses play until the current Url contains partialUrl string.
     * @param partialUrl - the partial url of the page to wait for
     * @param timeout (optional) - the time, in milliseconds, to wait for the url
     * If no time is given for the timeout, will use the default timeout.
     */
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

    /**
     * Pauses play until the current Title contains expectedTitle string.
     * @param expectedTitle - the expected title of the page to wait for.
     */
    protected void waitForTitle(String expectedTitle)
    {
        waitForPartialUrl(expectedTitle, _defaultTimeout);
    }

    /**
     * Pauses play until the current Title contains expectedTitle string.
     * @param expectedTitle - the expected title of the page to wait for
     * @param timeout (optional) - the time, in milliseconds, to wait for the title.
     * If no time is given for the timeout, will use the default timeout.
     */
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

    /**
     * Pauses play until the page is on the given URL.
     * @param url - the url of the page to wait for
     */
	protected void waitForUrl(String url)
	{
		waitForUrl(url, _defaultTimeout);
	}

	/**
	 * Pauses play until the page is on the given URL.
	 * @param url - the url of the page to wait for
	 * @param timeout (optional) - the time, in milliseconds, to wait for the url
	 * If no time is given for the timeout, will use the default timeout.
	 */
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
