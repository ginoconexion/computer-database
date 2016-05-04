package com.formation.computerdatabase.webapp.selenium;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:webapp-context.xml" })
public class SecurityTestSelenium {

	private static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeClass
	public static void init() {
		baseUrl = "http://localhost:8080/webapp/";
		FirefoxProfile profile = new FirefoxProfile();
		File noscript = new File(AddComputerTestSelenium.class.getClassLoader().getResource("noscript.xpi").getFile());
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
	public void addComputerTest() throws Exception {
		driver.get(baseUrl + "computer/add"); 
		assertTrue(driver.getCurrentUrl().equals(baseUrl + "/login?error"));
	}
	
	@Test
	public void editComputerTest() throws Exception {
		driver.get(baseUrl + "computer/edit/1"); 
		assertTrue(driver.getCurrentUrl().equals(baseUrl + "/login?error"));
	}
	/*
	@Test
	public void deleteComputerTest() throws Exception {
		
		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.xpath("(//input[@name='cb'])[last()]")).click();
		driver.findElement(By.xpath("(//input[@name='cb'])[last() - 1]")).click();
		//driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		driver.findElement(By.cssSelector("span.glyphicon.glyphicon-trash")).click();
	}
	*/
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