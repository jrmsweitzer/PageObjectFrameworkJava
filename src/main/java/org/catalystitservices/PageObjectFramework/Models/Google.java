package org.catalystitservices.PageObjectFramework.Models;

import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Google extends PageObject {

	public Google(WebDriver driver) {
		super(driver);
		_url = "https://www.google.com";
		goTo(_url);
	}
	
	private static By _inputSearch = By.name("q");
	private static By _btnSearch = By.name("btnG");
	
	public Google searchFor(String value)
	{
		clearAndSendKeys(_inputSearch, value);
		click(_btnSearch);
		
		return this;
	}

}
