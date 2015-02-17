package org.catalystitservices.PageObjectFramework.Models.Heroku;

import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HerokuLogin extends PageObject implements IHerokuApp {

	public HerokuLogin(WebDriver driver) {
		super(driver);
        _url = "http://the-internet.herokuapp.com/login";
        goTo(_url);
	}

    private static final By _inputUsername = By.id("username");
    private static final By _inputPassword = By.id("password");
    private static final By _btnLogin = By.xpath("//button");

    public HerokuLogin appendToUsernameTextBox(String text)
    {
        sendKeys(_inputUsername, text);
        return this;
    }

    public HerokuLogin backspace()
    {
        find(_inputUsername).sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public HerokuLogin clearUsernameTextBox()
    {
        clear(_inputUsername);
        return this;
    }

    public HerokuLogin login()
    {
        writeInUsernameTextBox("tomsmith");
        sendKeys(_inputPassword, "SuperSecretPassword!");
        click(_btnLogin);
        return this;
    }

    public HerokuLogin returnToLogin()
    {
        goTo(_url);
        return new HerokuLogin(_driver);
    }

    public HerokuLogin writeInUsernameTextBox(String text)
    {
        clear(_inputUsername);
        sendKeys(_inputUsername, text);
        return this;
    }

}
