package org.catalystitservices.PageObjectFramework.Models;

import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookieClicker extends PageObject {

	public CookieClicker(WebDriver driver) {
		super(driver);
        _url = "http://orteil.dashnet.org/cookieclicker/";
        goTo(_url);
	}

    private static final By _btnBigCookie = By.id("bigCookie");
    private static final By _btnCursor = By.id("productName0");
    private static final By _btnGrandma = By.id("productName1");
    private static final By _btnFarm = By.id("productName2");
    private static final By _btnFactory = By.id("productName3");

    public CookieClicker clickCookie(int numTimes)
    {
        for (int i = 0; i < numTimes; i++)
        {
            click(_btnBigCookie);
        }
        return this;
    }

    public CookieClicker buyCursor()
    {
        click(_btnCursor);
        return this;
    }

    public CookieClicker buyGrandma()
    {
        click(_btnGrandma);
        return this;
    }

    public CookieClicker buyFarm()
    {
        click(_btnFarm);
        return this;
    }

    public CookieClicker buyFactory()
    {
        click(_btnFactory);
        return this;
    }
}
