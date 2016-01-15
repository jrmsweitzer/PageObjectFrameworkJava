package org.catalystitservices.PageObjectFramework.Models;

import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.catalystitservices.PageObjectFramework.Framework.SeleniumLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Google extends PageObject {

	// Declare the logger
	private SeleniumLogger logger;

	public Google(WebDriver driver) {
		super(driver);
		url = "https://www.google.com";
		goTo(url);

		// Give it a descriptive name...
		logger = SeleniumLogger.getLogger("Google");
		// ...then you may begin using it. It will show up in
		// C:/Selenium/Logs/yyyy-MM-dd_descriptiveName.txt
		logger.logMessage("Making new Google PageObject.");
	}

	private static By inputSearch = By.name("q");
	private static By btnSearch = By.name("btnG");

	public Google appendSearchText(String text) {
		sendKeys(inputSearch, text);
		logger.logMessage(String.format("Appending text '%s' to google search bar.", text));
		return this;
	}

	public Google enterSearchText(String text) {
		clearAndSendKeys(inputSearch, text);
		logger.logMessage(String.format("Clearing and entering text '%s' to google search bar.", text));
		return this;
	}

	public Google search() {
		click(btnSearch);
		logger.logMessage("Clicking Search.");
		return this;
	}

	public Google returnToGoogle() {
		goTo(url, "Google");
		logger.logMessage("Returning control of driver to google");
		return new Google(driver);
	}

}
