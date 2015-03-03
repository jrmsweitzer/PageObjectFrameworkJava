package org.catalystitservices.PageObjectFramework.Models.Heroku;

import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.catalystitservices.PageObjectFramework.Framework.Exceptions.InvalidSelectOptionException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HerokuDropdown extends PageObject implements IHerokuApp {

	public HerokuDropdown(WebDriver driver) {
		super(driver);
	}

    public static final By _ddOptions = By.id("dropdown");

    public static final String Option1 = "Option 1";
    public static final String Option2 = "Option 2";

    public HerokuDropdown SelectOption(String option)
    {
        try {
			selectByText(_ddOptions, option);
		} catch (InvalidSelectOptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        sleep(1000);
        return this;
    }
}
