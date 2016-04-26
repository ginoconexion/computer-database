package com.formation.computerdatabase.test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:webapp-context.xml" })
public class TestSeleniumAddComputer {

	private static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();


	@BeforeClass
	public static void init() {
		baseUrl = "http://localhost:8080/computerdatabase/";
		FirefoxProfile profile = new FirefoxProfile();
		File noscript = new File(TestSeleniumAddComputer.class.getClassLoader().getResource("noscript.xpi").getFile());
		try {
			profile.addExtension(noscript);
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver = new FirefoxDriver(profile);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test
	public void addValidWithouContinuedAndDiscontinued() throws Exception {
		// add without introduced and discontinued
		driver.get(baseUrl + "dashboard");
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Test");
		new Select(driver.findElement(By.id("companyId"))).selectByVisibleText("Apple Inc.");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		// on teste la redirection si l'ajout s 'est bien passé
		assertTrue(driver.getCurrentUrl().equals(baseUrl + "dashboard"));
	}
	
	@Test
	public void addValidWithContinuedAndDiscontinued() throws Exception {
		// add with valid parameters
		driver.get(baseUrl + "dashboard");
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Test");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("1992-03-29");
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("2016-03-29");
		new Select(driver.findElement(By.id("companyId"))).selectByVisibleText("Apple Inc.");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		// on s'attends à deux erreurs
		assertTrue(driver.getCurrentUrl().equals(baseUrl + "dashboard"));
	}
	@Ignore
	@Test
	public void addWithWrongIntroducedAndDiscontinued() throws Exception {
		// add with wrong parameters introduced discontinued
		driver.get(baseUrl + "/dashboard");
		driver.findElement(By.id("addComputer")).click();

		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Test");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("wrong date");
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("wrong date");
		new Select(driver.findElement(By.id("companyId"))).selectByVisibleText("Apple Inc.");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		// on s'attends à deux erreurs
		assertEquals(2, driver.findElements(By.cssSelector("div.alert.alert-danger")).size());
		assertFalse(driver.getCurrentUrl().equals(baseUrl + "/computerdatabase/dashboard"));
	}
	@Test
	public void addWithNoParameters() throws Exception {
		// add with no parameters
		driver.get(baseUrl + "dashboard");
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		// on s'attends à deux erreurs (nom + company)
		assertEquals(2, driver.findElements(By.cssSelector("div.alert.alert-danger")).size());
		assertFalse(driver.getCurrentUrl().equals(baseUrl + "dashboard"));
	}
	
	@Test
	public void addWithValidParametersButNoCompany() throws Exception {
		// add with valid parameters but no company
		driver.get(baseUrl + "dashboard");
		driver.findElement(By.id("addComputer")).click();

		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Test");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("1992-03-29");
		driver.findElement(By.id("discontinued")).clear();

		driver.findElement(By.id("discontinued")).sendKeys("2016-03-29");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		// on s'attends à deux erreurs
		assertEquals(1, driver.findElements(By.cssSelector("div.alert.alert-danger")).size());
		assertFalse(driver.getCurrentUrl().equals(baseUrl + "/computerdatabase/dashboard"));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
