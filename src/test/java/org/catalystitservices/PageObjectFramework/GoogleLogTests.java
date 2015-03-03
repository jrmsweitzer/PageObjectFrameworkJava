package org.catalystitservices.PageObjectFramework;

import junit.framework.Assert;

import org.catalystitservices.PageObjectFramework.Framework.PageObjectTest;
import org.catalystitservices.PageObjectFramework.Models.Google;
import org.junit.Test;

public class GoogleLogTests extends PageObjectTest {

	@Test
	public void googleShouldMakeLogForEveryMethod() {
		Google google = new Google(getDriver());
        google.enterSearchText("Example Search Text.");
        google.appendSearchText(" Adding new text.");
        google.enterSearchText("Clearing field for this text.");
        google.search();
	}
	
	@Test
	public void thisTestShouldFail()
	{
		Assert.assertEquals(1, 2);
	}

}
