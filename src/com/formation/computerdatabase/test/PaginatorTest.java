package com.formation.computerdatabase.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.formation.computerdatabase.pagination.Paginator;

public class PaginatorTest {

	private static Paginator paginator;
	private final static int nbEntries = 578;
	private final static int parPage = 10;
	
	
	@BeforeClass
	public static void init() {
		paginator = new Paginator(nbEntries, parPage);
	}
	@AfterClass
	public static void close() {
		paginator = null;
	}
	
	@Test
	public void testNbPages() {
		assertEquals((int) Math.ceil(nbEntries/parPage), paginator.getNbPages());
	}
	
	@Test
	public void testNext(){
		paginator.setPageActuelle(1);
		paginator.next();
		assertEquals(2, paginator.getPageActuelle());
	}
	@Test
	public void testPrev(){
		paginator.setPageActuelle(2);
		paginator.prev();
		assertEquals(1, paginator.getPageActuelle());
	}
}
