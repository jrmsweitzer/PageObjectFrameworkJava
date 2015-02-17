package org.catalystitservices.PageObjectFramework.Models.Heroku;

import org.catalystitservices.PageObjectFramework.Framework.ByFormatter;
import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.openqa.selenium.WebDriver;

public class HerokuMain extends PageObject implements IHerokuApp {

	public HerokuMain(WebDriver driver) {
		super(driver);
		_url = "http://the-internet.herokuapp.com/";
		_title = "The Internet";
		goTo(_url, _title);
	}

    public final static String Checkboxes = "Checkboxes";
    public final static String Dropdown = "Dropdown";
    public final static String FormAuthentication = "Form Authentication";

    private static final ByFormatter _linkByText = ByFormatter.linkText("%s");

    public IHerokuApp clickLink(String linkText)
    {
        click(_linkByText.format(linkText));
        sleep(1000);
        switch (linkText)
        {
            case Checkboxes:
                return new HerokuCheckboxes(_driver);
            case Dropdown:
                return new HerokuDropdown(_driver);
            case FormAuthentication:
                return new HerokuLogin(_driver);
            default:
                return this;
        }
    }

    public HerokuMain returnToHeroku()
    {
        goTo(_url, _title);
        return new HerokuMain(_driver);
    }
	
	

}
