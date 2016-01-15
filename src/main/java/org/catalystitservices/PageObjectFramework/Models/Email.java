package org.catalystitservices.PageObjectFramework.Models;

import org.catalystitservices.PageObjectFramework.Framework.PageObject;
import org.catalystitservices.PageObjectFramework.Framework.WindowHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Email extends PageObject {

	public Email(WebDriver driver) {
		super(driver);
		url = "https://mail.catalystitservices.com/";
		goTo(url);
	}

	private static final By inputUsername = By.id("username");
	private static final By inputPassword = By.id("password");
	private static final By btnSignIn = By.xpath("//input[@type='submit']");

	private static final By btnNewEmail = By.xpath("//a[.='New']");
	private static final By inputTo = By.id("divTo");
	private static final By inputSubject = By.id("txtSubj");
	private static final By ifrMessage = By.id("ifBdy");
	private static final By bodyMessage = By.xpath("//style[@id='owaTempEditStyle']/../../body");
	private static final By btnSend = By.id("send");
	private static final By btnAddressBook = By.id("divToolbarButtonaddressbook");

	public Email logInWithCredentials(String username, String password) {
		sendKeys(inputUsername, username);
		sendKeys(inputPassword, password);
		click(btnSignIn);

		return this;
	}

	public Email composeNewEmail(String to, String subject, String message) {
		click(btnNewEmail);

		windowHandler.addNewWindowHandle("Compose Email");
		windowHandler.switchToHandle("Compose Email");

		sendKeys(inputTo, to);
		sleep(1000);
		sendKeys(inputSubject, subject);
		sleep(1000);

		driver.switchTo().frame(find(ifrMessage));
		sendKeys(bodyMessage, message);

		windowHandler.switchToHandle("Compose Email");
		click(btnSend);

		windowHandler.switchToHandle(WindowHandler.MainWindowHandle);

		return this;
	}

	public void openNewEmailWindow() {

		click(btnNewEmail);

		windowHandler.addNewWindowHandle("Compose Email");
		windowHandler.switchToHandle("Compose Email");

	}

	public void openNewEmailWindow2() {

		click(btnNewEmail);

		windowHandler.addNewWindowHandle("Compose Email 2");
		windowHandler.switchToHandle("Compose Email 2");

	}

	public void openNewEmailWindow3() {

		click(btnNewEmail);

		windowHandler.addNewWindowHandle("Compose Email 3");
		windowHandler.switchToHandle("Compose Email 3");

	}

	public void openAddressBook() {

		click(btnAddressBook);

		windowHandler.addNewWindowHandle("Address Book");
		windowHandler.switchToHandle("Address Book");

	}

	public void goToEmailCompose() {
		windowHandler.switchToHandle("Compose Email");
	}

	public void goToEmailList() {
		windowHandler.switchToHandle(WindowHandler.MainWindowHandle);

	}

	public void goToAddressBook() {
		windowHandler.switchToHandle("Address Book");
	}
}
