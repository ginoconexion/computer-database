package com.formation.computerdatabase.test.selenium;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;


public class TestSeleniumDashboard {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    //baseUrl = "http://localhost:8080/computerdatabase/";
    baseUrl = "http://localhost:8080/computerdatabase-1.0-SNAPSHOT/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testListe() throws Exception {
    driver.get(baseUrl + "/dashboard");
    driver.findElement(By.linkText("MacBook Pro 15.6")).click();
    driver.findElement(By.cssSelector("a.navbar-brand")).click();
    driver.findElements(By.xpath("//tbody[@id='results']/tr/td"));
    assertTrue(driver.findElements(By.xpath("//tbody[@id='results']/tr/td")).size() > 0);
  }
  
  @Test
  public void testSearch() throws Exception {
	  
	  driver.get(baseUrl + "/dashboard");
	  //driver.get(baseUrl + "/computerdatabase/dashboard?nb=10&search=&page=1");
	  driver.findElement(By.id("searchbox")).clear();
	  driver.findElement(By.id("searchbox")).sendKeys("mac");
	  driver.findElement(By.id("searchsubmit")).click();
	  System.out.println(driver.findElement(By.xpath("//td[2]/a")).getText());
	  assertTrue(driver.findElement(By.xpath("//td[2]/a")).getText().toLowerCase().contains("mac"));	  
  }
  

  @After
  public void tearDown() throws Exception {
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
