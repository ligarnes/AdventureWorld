package net.alteiar.myapp.selenium;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class SimpleSelenium {

	private WebDriver browser;

	@Before
	public void before() {

		// new HtmlUnitDriver()
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);

		browser = new FirefoxDriver(profile);
	}

	@After
	public void after() {

		browser.quit();
		browser.close();
	}

	@Test
	public void test() {

		// Go to the Google Suggest home page
		browser.get("http://www.google.com/webhp?complete=1&hl=en");

		// Enter the query string "Cheese"
		WebElement query = browser.findElement(By.name("q"));
		query.sendKeys("Cheese");

		// Sleep until the div we want is visible or 5 seconds is over
		long end = System.currentTimeMillis() + 5000;
		while (System.currentTimeMillis() < end) {
			WebElement resultsDiv = browser.findElement(By.className("gssb_e"));

			// If results have been returned, the results are displayed in a
			// drop down.
			if (resultsDiv.isDisplayed()) {
				break;
			}
		}

		// And now list the suggestions
		List<WebElement> allSuggestions = browser.findElements(By.xpath("//td[@class='gssb_a gbqfsf']"));

		for (WebElement suggestion : allSuggestions) {
			System.out.println(suggestion.getText());
		}

	}
}
