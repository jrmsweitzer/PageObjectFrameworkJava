package org.catalystitservices.PageObjectFramework.Framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/// <summary>
/// A custom class combining a By locator with a string formatter.
/// <para> @author: Jeremy Sweitzer</para>
/// </summary>
public class ByFormatter extends By {
	
	private ByFormatter(String locator, String formatter)
	{
		_locator = locator;
		_formatter = formatter;
	}
	
	private String _locator;
	private String _formatter;
	
	private static final String FORMATTER_CLASSNAME = "className";
	private static final String FORMATTER_CSSSELECTOR = "cssSelector";
	private static final String FORMATTER_ID = "id";
	private static final String FORMATTER_LINKTEXT = "linkText";
	private static final String FORMATTER_PARTIALLINKTEXT = "partialLinkText";
	private static final String FORMATTER_NAME = "name";
	private static final String FORMATTER_TAGNAME = "tagName";
	private static final String FORMATTER_XPATH = "xpath";
	
	public static ByFormatter className(String locator)
	{
		return new ByFormatter(locator, ByFormatter.FORMATTER_CLASSNAME);
	}
	
	public static ByFormatter cssSelector(String locator)
	{
		return new ByFormatter(locator, ByFormatter.FORMATTER_CSSSELECTOR);
	}
	
	public static ByFormatter id(String locator)
	{
		return new ByFormatter(locator, ByFormatter.FORMATTER_ID);
	}
	
	public static ByFormatter linkText(String locator)
	{
		return new ByFormatter(locator, ByFormatter.FORMATTER_LINKTEXT);
	}
	
	public static ByFormatter partialLinkText(String locator)
	{
		return new ByFormatter(locator, ByFormatter.FORMATTER_PARTIALLINKTEXT);
	}
	
	public static ByFormatter name(String locator)
	{
		return new ByFormatter(locator, ByFormatter.FORMATTER_NAME);
	}
	
	public static ByFormatter tagName(String locator)
	{
		return new ByFormatter(locator, ByFormatter.FORMATTER_TAGNAME);
	}
	
	public static ByFormatter xpath(String locator)
	{
		return new ByFormatter(locator, ByFormatter.FORMATTER_XPATH);
	}
	
	@Override
	public String toString()
	{
		return String.format("ByFormatter.%s: %s",
				_formatter, _locator);
	}
	
	public By format(String... vars)
	{
		int numVars = vars.length;
		String by = _locator;
		for (int i = 0; i < numVars; i++)
		{
			String replacement = "%s";
			by = by.replaceFirst(replacement, vars[i]);
		}
		switch(_formatter)
		{
        case FORMATTER_CLASSNAME:
            return By.className(by);
        case FORMATTER_CSSSELECTOR:
            return By.cssSelector(by);
        case FORMATTER_ID:
            return By.id(by);
        case FORMATTER_LINKTEXT:
            return By.linkText(by);
        case FORMATTER_PARTIALLINKTEXT:
            return By.name(by);
        case FORMATTER_NAME:
            return By.partialLinkText(by);
        case FORMATTER_TAGNAME:
            return By.tagName(by);
        case FORMATTER_XPATH:
            return By.xpath(by);
        default:
            return null;
		}
	}
	
	

	@Override
	public List<WebElement> findElements(SearchContext context) {
		// TODO Auto-generated method stub
		return null;
	}
}
