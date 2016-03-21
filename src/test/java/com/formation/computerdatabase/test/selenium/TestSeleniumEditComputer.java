package com.formation.computerdatabase.test.selenium;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
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
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

public class TestSeleniumEditComputer {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  baseUrl = "http://localhost:8080//computerdatabase-1.0-SNAPSHOT";
	  //baseUrl = "http://localhost:8080//computerdatabase";
	  FirefoxProfile profile = new FirefoxProfile();
	  File noscript = new File(TestSeleniumAddComputer.class.getClassLoader().getResource("noscript.xpi").getFile());
	  //File noscript = new File("/home/excilys/noscript.xpi");
	  try {
		profile.addExtension(noscript);
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
	  driver = new FirefoxDriver(profile);
	  
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	driver.get(baseUrl);
	
	// initialisation de l'objet
	driver.get(baseUrl + "/dashboard");
	String nom = "MacBook Pro 15.7";
	String introduced = "1992-03-30";
	String discontinued = "1993-03-30";
	String company = "Thinking Machines";
	
	driver.findElement(By.xpath("//td[2]/a")).click();
    
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys(nom);
    
    driver.findElement(By.id("introduced")).clear();
    driver.findElement(By.id("introduced")).sendKeys(introduced);
    
    driver.findElement(By.id("discontinued")).clear();
    driver.findElement(By.id("discontinued")).sendKeys(discontinued);
    new Select(driver.findElement(By.id("companyId"))).selectByVisibleText(company);
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
  }
  
  @Test
  public void editWithValidValues() throws Exception {
	
	String nom = "MacBook Pro 15.6";
	String introduced = "1992-03-29";
	String discontinued = "1993-03-29";
	String company = "Apple Inc.";
	int idCompany1 = 1;
	driver.get(baseUrl + "/dashboard");
    
    // on clique sur le premier lien
    driver.findElement(By.xpath("//td[2]/a")).click();
    
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys(nom);
    
    driver.findElement(By.id("introduced")).clear();
    driver.findElement(By.id("introduced")).sendKeys(introduced);
    
    driver.findElement(By.id("discontinued")).clear();
    driver.findElement(By.id("discontinued")).sendKeys(discontinued);
    
    new Select(driver.findElement(By.id("companyId"))).selectByVisibleText(company);
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    
    driver.get(baseUrl + "/dashboard");
    // on clique sur le premier lien
    driver.findElement(By.xpath("//td[2]/a")).click();
    
    //System.out.println(driver.findElement(By.id("computerName")).getText());
    assertTrue(driver.findElement(By.id("computerName")).getAttribute("value").equals(nom));
    assertTrue(driver.findElement(By.id("introduced")).getAttribute("value").equals(introduced));
    assertTrue(driver.findElement(By.id("discontinued")).getAttribute("value").equals(discontinued));
    
    System.out.println(driver.findElement(By.id("companyId")).getAttribute("value"));
    assertTrue(Integer.parseInt(driver.findElement(By.id("companyId")).getAttribute("value")) == idCompany1 );
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
