package com.formation.computerdatabase.test.selenium;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.pagination.Pager;
import com.formation.computerdatabase.pagination.mapper.PagerMapper;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;
import com.formation.computerdatabase.services.ServiceFactory;
import com.formation.computerdatabase.services.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.services.impl.ComputerDaoServiceImpl;

public class TestSeleniumDeleteComputer {

	private static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private static ClassPathXmlApplicationContext context;
	private static CompanyDaoServiceImpl companyService;
	private static ComputerDaoServiceImpl computerService;
	private static ComputerDaoImpl computerDao;

	@BeforeClass
	public static void init() {
		context = new ClassPathXmlApplicationContext("webapp-context.xml");
		ServiceFactory service = (ServiceFactory) context.getBean("serviceFactory");
		companyService = service.getCompanyDaoServiceImpl();
		computerService = service.getComputerDaoServiceImpl();
		computerDao = computerService.getComputerDaoImpl();
	}
	@AfterClass
	public static void close() {
		context.close();
	}

	@Before
	public void before() {

		baseUrl = "http://localhost:8080/computerdatabase";
		FirefoxProfile profile = new FirefoxProfile();

		driver = new FirefoxDriver(profile);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.get(baseUrl);
	}

	@Test
	public void add2ComputersAndDelete() throws Exception {


		HashMap<String, Object> filter = new HashMap<>();
		String nom = "Test";
		String introduced = "1992-03-29";
		String discontinued = "1993-03-29";
		String company = "Apple Inc.";

		int idCompany1 = 1;

		driver.get(baseUrl + "/dashboard?page=1");

		Pager<ComputerDTO> pager = PagerMapper.map(new HashMap<>());
		// on set la liste et le nombre d'entrées
		computerService.updatePager(pager);
		
		// ajout d'un computer
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(nom);
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys(introduced);
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys(discontinued);
		new Select(driver.findElement(By.id("companyId"))).selectByVisibleText(company);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

		// ajout d'un autre computer
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(nom);
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys(introduced);
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys(discontinued);
		new Select(driver.findElement(By.id("companyId"))).selectByVisibleText(company);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		driver.get(baseUrl + "/dashboard?page=" + pager.getNbPages());
		// on se rend sur la derniere page
		//Pager<ComputerDTO> pager = new pa
		int nb = computerDao.getCount(filter);
		
		//driver.findElement(By.xpath("//li[4]/a")).click();
		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.xpath("(//input[@name='cb'])[last()]")).click();
		driver.findElement(By.xpath("(//input[@name='cb'])[last() - 1]")).click();
		//driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		driver.findElement(By.cssSelector("span.glyphicon.glyphicon-trash")).click();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(nb, computerDao.getCount(filter));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(nb - 2, driver.findElements(By.xpath("//tbody[@id='results']/tr")).size());
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
