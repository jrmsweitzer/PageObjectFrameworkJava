package org.catalystitservices.PageObjectFramework.Models;

import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.catalystitservices.PageObjectFramework.Framework.SeleniumLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Google extends PageObject {
	
	// Declare the logger
	private SeleniumLogger _logger;

	public Google(WebDriver driver) {
		super(driver);
		_url = "https://www.google.com";
		goTo(_url);
		
		// Give it a descriptive name...
		_logger = SeleniumLogger.getLogger("Google");
		// ...then you may begin using it. It will show up in 
		// C:/Selenium/Logs/yyyy-MM-dd_descriptiveName.txt
		_logger.logMessage("Making new Google PageObject.");
	}
	
	private static By _inputSearch = By.name("q");
	private static By _btnSearch = By.name("btnG");
	
    public Google appendSearchText(String text)
    {
        sendKeys(_inputSearch, text);
        _logger.logMessage(String.format("Appending text '%s' to google search bar.", text));
        return this;
    }
    public Google enterSearchText(String text)
    {
        clearAndSendKeys(_inputSearch, text);
        _logger.logMessage(String.format("Clearing and entering text '%s' to google search bar.", text));
        return this;
    }
    public Google search()
    {
        click(_btnSearch);
        _logger.logMessage("Clicking Search.");
        return this;
    }
    public Google returnToGoogle()
    {
        goTo(_url, "Google");
        _logger.logMessage("Returning control of driver to google");
        return new Google(_driver);
    }

}
