package org.catalystitservices.PageObjectFramework.Models;

import org.catalystitservices.PageObjectFramework.Framework.ByFormatter;
import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YouTube extends PageObject {

	public YouTube(WebDriver driver) {
		super(driver);
		url = "http://www.youtube.com";
		goTo(url, "YouTube");
	}

	private static final By inputSearch = By.name("search_query");
	private static final By btnSearch = By.id("search-btn");
	private static final ByFormatter linkVideoByIndex = ByFormatter
			.xpath("(//h3[contains(@class, 'yt-lockup-title')])[%s]");

	public YouTube searchYouTube(String text) {
		clearAndSendKeys(inputSearch, text);
		click(btnSearch);
		sleep(2000);
		return this;
	}

	public YouTube clickVideoAtIndex(String n) {
		click(linkVideoByIndex.format(n));
		return this;
	}
}
