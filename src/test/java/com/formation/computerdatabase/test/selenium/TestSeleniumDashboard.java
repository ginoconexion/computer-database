package com.formation.computerdatabase.test.selenium;

import static org.junit.Assert.assertTrue;
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

import com.formation.computerdatabase.pagination.Order;


public class TestSeleniumDashboard {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    //baseUrl = "http://localhost:8080/computerdatabase/";
    baseUrl = "http://localhost:8080/computerdatabase/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testListe() throws Exception {
    driver.get(baseUrl + "/dashboard");
    assertTrue(driver.findElements(By.xpath("//tbody[@id='results']/tr/td")).size() > 0);
  }
  
  @Test
  public void testSearch() throws Exception {
	  driver.get(baseUrl + "/dashboard");
	  driver.findElement(By.id("searchbox")).clear();
	  driver.findElement(By.id("searchbox")).sendKeys("mac");
	  driver.findElement(By.id("searchsubmit")).click();
	  System.out.println(driver.findElement(By.xpath("//td[2]/a")).getText());
	  assertTrue(driver.findElement(By.xpath("//td[2]/a")).getText().toLowerCase().contains("mac"));	  
  }
  
  @Test
  public void testFiltre() throws Exception {
	  driver.get(baseUrl + "/dashboard");
	  driver.findElement(By.xpath("//th[2]/a")).click();
	  assertTrue(driver.findElement(By.xpath("//th[2]/a")).getAttribute("href").contains(Order.BY_NAME + "=desc"));
	  
	  driver.findElement(By.xpath("//th[3]/a")).click();
	  assertTrue(driver.findElement(By.xpath("//th[3]/a")).getAttribute("href").contains(Order.BY_INTRODUCED + "=desc"));
	  
	  driver.findElement(By.xpath("//th[4]/a")).click();
	  assertTrue(driver.findElement(By.xpath("//th[4]/a")).getAttribute("href").contains(Order.BY_DISCONTINUED + "=desc"));
	  
	  driver.findElement(By.xpath("//th[5]/a")).click();
	  assertTrue(driver.findElement(By.xpath("//th[5]/a")).getAttribute("href").contains(Order.BY_COMPANY + "=desc"));
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
