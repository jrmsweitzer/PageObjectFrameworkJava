package org.catalystitservices.PageObjectFramework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.catalystitservices.PageObjectFramework.Framework.PageObjectTest;
import org.catalystitservices.PageObjectFramework.Models.CookieClicker;
import org.catalystitservices.PageObjectFramework.Models.Email;
import org.catalystitservices.PageObjectFramework.Models.Google;
import org.catalystitservices.PageObjectFramework.Models.YouTube;
import org.catalystitservices.PageObjectFramework.Models.Heroku.HerokuCheckboxes;
import org.catalystitservices.PageObjectFramework.Models.Heroku.HerokuDropdown;
import org.catalystitservices.PageObjectFramework.Models.Heroku.HerokuLogin;
import org.catalystitservices.PageObjectFramework.Models.Heroku.HerokuMain;
import org.junit.Test;

public class Presentation extends PageObjectTest {

	String NOTSET = "Not Set";
	
	@Test
	public void presentation() {


        String emailUsername = NOTSET;
        String emailPassword = NOTSET;

		HerokuMain heroku = new HerokuMain(getDriver());

		HerokuLogin herokuLogin =
            ((HerokuLogin)heroku
            .clickLink(HerokuMain.FormAuthentication))
            .writeInUsernameTextBox("Hello Catalyst, and welcome to Developer training.");
		herokuLogin.sleep(2000);
        herokuLogin.writeInUsernameTextBox("My name is Jeremy Sweitzer, and I've been with Catalyst since August 2013");
        herokuLogin.sleep(3000);
        herokuLogin.writeInUsernameTextBox("Tonight's topic is going to be Selenium. What is Selenium?");
        herokuLogin.sleep(2800);
        herokuLogin.writeInUsernameTextBox("Selenium is a multi-language tool used to automate things on the Internet.");
        herokuLogin.sleep(2500);
        herokuLogin.writeInUsernameTextBox("I've been using Selenium for almost a year now, for many different tasks.");
        herokuLogin.sleep(3500);
        herokuLogin.writeInUsernameTextBox("I've used it to test web pages here at the office.");
        herokuLogin.sleep(2000);
        herokuLogin.writeInUsernameTextBox("I've taught it to play games, and to simply gather data.");
        herokuLogin.sleep(2500);
        herokuLogin.writeInUsernameTextBox("It's uses are many, limited only by your imagination.");
        herokuLogin.sleep(2500);
        herokuLogin.writeInUsernameTextBox("As you can see, Selenium can fill in text boxes for you...");
        herokuLogin.sleep(2500);
        herokuLogin.writeInUsernameTextBox("And can pause play for any amount of time. ");
        herokuLogin.sleep(1700);
        herokuLogin.appendToUsernameTextBox("1 ");
        herokuLogin.sleep(1000);
        herokuLogin.appendToUsernameTextBox("2 ");
        herokuLogin.sleep(1000);
        herokuLogin.appendToUsernameTextBox("3 ");
        herokuLogin.sleep(1000);
        herokuLogin.appendToUsernameTextBox("4 ");
        herokuLogin.sleep(1000);
        herokuLogin.appendToUsernameTextBox("5 ");
        herokuLogin.sleep(1000);
        herokuLogin.writeInUsernameTextBox("You can check and uncheck checkboxes...");
        herokuLogin.sleep(2000);

        ((HerokuCheckboxes)heroku
            .returnToHeroku()
            .clickLink(HerokuMain.Checkboxes))
            .toggleUncheckedBox(3)
            .toggleCheckedBox(3);

        herokuLogin
            .returnToLogin()
            .writeInUsernameTextBox("As you had just seen, we can jump to different pages...");
        herokuLogin.sleep(2200);
        herokuLogin.writeInUsernameTextBox("We can also select options from select dropdowns.");
        herokuLogin.sleep(3000);

        ((HerokuDropdown)heroku
            .returnToHeroku()
            .clickLink(HerokuMain.Dropdown))
            .SelectOption(HerokuDropdown.Option1)
            .SelectOption(HerokuDropdown.Option2)
            .SelectOption(HerokuDropdown.Option1)
            .SelectOption(HerokuDropdown.Option2);

        herokuLogin
            .returnToLogin()
            .writeInUsernameTextBox("Basically, anything you can do using a browser, Selenium can too!");
        herokuLogin.sleep(3000);
        
        YouTube youtube = new YouTube(getDriver());
        youtube
            .searchYouTube("dQw4w9WgXcQ")
            .clickVideoAtIndex("1");

        youtube.sleep(52000);

        CookieClicker cookieclicker = new CookieClicker(getDriver());
        cookieclicker
            .clickCookie(100)
            .buyGrandma()
            .clickCookie(115)
            .buyGrandma()
            .clickCookie(133)
            .buyGrandma()
            .clickCookie(153)
            .buyGrandma()
            .clickCookie(465)
            .buyFarm()
            .clickCookie(500);
        
        if (emailUsername != NOTSET && emailPassword != NOTSET)
        {
        	String toEmail = "jsweitzer@catalystitservices.com";
        	String subject = "Selenium PageObject Framework Source Code with C#";
        	String message = "Check out this neat Source Code! It's an entire framework just for Selenium," +
                    " using PageObjects! https://github.com/jrmsweitzer/PageObjectFrameworkJava.";

            Email email = new Email(getDriver());
            email
                .logInWithCredentials(emailUsername, emailPassword)
                .composeNewEmail(toEmail, subject, message);
            email.sleep(5000);
        }
	}

}
