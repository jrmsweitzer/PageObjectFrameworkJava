package org.catalystitservices.PageObjectFramework.Models;

import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookieClicker extends PageObject {

	public CookieClicker(WebDriver driver) {
		super(driver);
		url = "http://orteil.dashnet.org/cookieclicker/";
		goTo(url);
	}

	private static final By btnBigCookie = By.id("bigCookie");
	private static final By btnCursor = By.id("productName0");
	private static final By btnGrandma = By.id("productName1");
	private static final By btnFarm = By.id("productName2");
	private static final By btnFactory = By.id("productName3");

	public CookieClicker clickCookie(int numTimes) {
		for (int i = 0; i < numTimes; i++) {
			click(btnBigCookie);
		}
		return this;
	}

	public CookieClicker buyCursor() {
		click(btnCursor);
		return this;
	}

	public CookieClicker buyGrandma() {
		click(btnGrandma);
		return this;
	}

	public CookieClicker buyFarm() {
		click(btnFarm);
		return this;
	}

	public CookieClicker buyFactory() {
		click(btnFactory);
		return this;
	}
}
