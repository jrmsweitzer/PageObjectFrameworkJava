package org.catalystitservices.PageObjectFramework.Models.Heroku;

import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HerokuCheckboxes extends PageObject implements IHerokuApp {

	public HerokuCheckboxes(WebDriver driver) {
		super(driver);
	}

    public static final By _cbUnchecked = By.xpath("//form/input[1]");
    public static final By _cbChecked = By.xpath("//form/input[2]");

    public HerokuCheckboxes toggleUncheckedBox(int numTimes)
    {
        for (int i = 0; i < numTimes; i++)
        {
            click(_cbUnchecked);
            sleep(1000);
        }
        return this;
    }

    public HerokuCheckboxes toggleCheckedBox(int numTimes)
    {
        for (int i = 0; i < numTimes; i++)
        {
            click(_cbChecked);
            sleep(1000);
        }
        return this;
    }
}
