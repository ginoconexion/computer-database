package com.formation.computerdatabase.test;

public class TestPager {
	/*
	private static Pager pager;
	private final static int nbEntries = 578;
	private final static int parPage = 10;
	private static CompanyDaoServiceImpl companyDaoImpl;
	
	
	@BeforeClass
	public static void init() {
		ServiceFactory service = ServiceFactory.INSTANCE;
		companyDaoImpl = service.getCompanyDaoServiceImpl();
		pager = new Pager(nbEntries, parPage, companyDaoImpl);
	}
	@AfterClass
	public static void close() {
		pager = null;
	}
	
	@Ignore
	@Test
	public void testNbPages() {
		assertEquals((int) Math.ceil(nbEntries/parPage), pager.getNbPages());
	}
	
	@Ignore
	@Test
	public void testNext(){
		pager.setPageActuelle(1);
		List<Company> previousListe = pager.getListe();
		pager.next();
		assertTrue(!previousListe.get(0).equals(pager.getListe().get(0)));
		assertEquals(2, pager.getPageActuelle());
	}
	@Ignore
	@Test
	public void testPrev(){
		pager.setPageActuelle(2);
		List<Company> previousListe = pager.getListe();
		pager.prev();
		assertTrue(!previousListe.get(0).equals(pager.getListe().get(0)));
		assertEquals(1, pager.getPageActuelle());
	}
	*/
}
