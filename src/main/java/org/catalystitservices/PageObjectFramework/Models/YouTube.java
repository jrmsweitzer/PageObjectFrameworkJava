package org.catalystitservices.PageObjectFramework.Models;

import org.catalystitservices.PageObjectFramework.Framework.ByFormatter;
import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YouTube extends PageObject {

	public YouTube(WebDriver driver) {
		super(driver);
        _url = "http://www.youtube.com";
        goTo(_url, "YouTube");
	}

    private static final By _inputSearch = By.name("search_query");
    private static final By _btnSearch = By.id("search-btn");
    private static final ByFormatter _linkVideoByIndex = ByFormatter.xpath("(//h3[contains(@class, 'yt-lockup-title')])[%s]");

    public YouTube searchYouTube(String text)
    {
        clearAndSendKeys(_inputSearch, text);
        click(_btnSearch);
        sleep(2000);
        return this;
    }

    public YouTube clickVideoAtIndex(String n)
    {
        click(_linkVideoByIndex.format(n));
        return this;
    }
}
