package org.catalystitservices.PageObjectFramework.Models;

import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.catalystitservices.PageObjectFramework.Framework.WindowHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Email extends PageObject {

	public Email(WebDriver driver) {
		super(driver);
        _url = "https://mail.catalystitservices.com/";
        goTo(_url);
	}

    private static final By _inputUsername = By.id("username");
    private static final By _inputPassword = By.id("password");
    private static final By _btnSignIn = By.xpath("//input[@type='submit']");

    private static final By _btnNewEmail = By.xpath("//a[.='New']");
    private static final By _inputTo = By.id("divTo");
    private static final By _inputSubject = By.id("txtSubj");
    private static final By _ifrMessage = By.id("ifBdy");
    private static final By _bodyMessage = By.xpath("//style[@id='owaTempEditStyle']/../../body");
    private static final By _btnSend = By.id("send");
    private static final By _btnAddressBook = By.id("divToolbarButtonaddressbook");

    public Email logInWithCredentials(String username, String password)
    {
        sendKeys(_inputUsername, username);
        sendKeys(_inputPassword, password);
        click(_btnSignIn);

        return this;
    }

    public Email composeNewEmail(String to, String subject, String message)
    {
        click(_btnNewEmail);

        _windowHandler.addNewWindowHandle("Compose Email");
        _windowHandler.switchToHandle("Compose Email");

        sendKeys(_inputTo, to);
        sleep(1000);
        sendKeys(_inputSubject, subject);
        sleep(1000);

        _driver.switchTo().frame(find(_ifrMessage));
        sendKeys(_bodyMessage, message);

        _windowHandler.switchToHandle("Compose Email");
        click(_btnSend);

        _windowHandler.switchToHandle(WindowHandler.MainWindowHandle);

        return this;
    }

	public void openNewEmailWindow() {

		click(_btnNewEmail);
		
		_windowHandler.addNewWindowHandle("Compose Email");
		_windowHandler.switchToHandle("Compose Email");
		
	}

	public void openNewEmailWindow2() {

		click(_btnNewEmail);
		
		_windowHandler.addNewWindowHandle("Compose Email 2");
		_windowHandler.switchToHandle("Compose Email 2");
		
	}

	public void openNewEmailWindow3() {

		click(_btnNewEmail);
		
		_windowHandler.addNewWindowHandle("Compose Email 3");
		_windowHandler.switchToHandle("Compose Email 3");
		
	}

	public void openAddressBook() {
		
		click(_btnAddressBook);

		_windowHandler.addNewWindowHandle("Address Book");
		_windowHandler.switchToHandle("Address Book");
		
	}

	public void goToEmailCompose() {
		_windowHandler.switchToHandle("Compose Email");
	}

	public void goToEmailList() {
        _windowHandler.switchToHandle(WindowHandler.MainWindowHandle);
		
	}

	public void goToAddressBook() {
		_windowHandler.switchToHandle("Address Book");
	}
}
