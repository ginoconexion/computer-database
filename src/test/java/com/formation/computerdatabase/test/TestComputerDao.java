package com.formation.computerdatabase.test;

public class TestComputerDao {
	
	/*
	private static ComputerDaoImpl computerDaoImpl;
	
	
	@BeforeClass
	public static void init() {
		computerDaoImpl = ComputerDaoImpl.INSTANCE;
	}
	@Ignore
	@Test
	public void testGetById(){
		Computer computer = ComputerDaoImpl.INSTANCE.getById(1);
		assertEquals(1, computer.getId());
	}
	
	@Ignore
	@Test
	public void testGetByIdNull(){
		Computer computer = ComputerDaoImpl.INSTANCE.getById(600);
		assertEquals(null, computer);
	}
	
	@Ignore
	@Test
	public void testUpdate(){
		Computer computer = computerDaoImpl.getById(1);
		System.out.println(computer);
		computer.setName("MacBook Pro 15.6");
		computerDaoImpl.updateComputer(computer);
		computer = computerDaoImpl.getById(1);
		System.out.println(computer.getName());
		assertEquals("MacBook Pro 15.6", computer.getName());
	}
	
	@Ignore
	@Test(expected = DAOException.class)
	public void testUpdateUnknownCompany(){
		Computer computer = computerDaoImpl.getById(1);
		computer.getCompany().setId(600);
		computerDaoImpl.updateComputer(computer);
	}
	
	@Ignore
	@Test(expected = DAOException.class)
	public void testCreateComputerWithNullCompanyAndNullTimestamps(){
		Computer computer = new Computer();
		computer.setName("Ordinateur test");
		computerDaoImpl.createComputer(computer);
	}
	
	@Ignore
	@Test
	public void testCreateAndDelete(){
		Computer computer = new Computer();
		computer.setCompany(CompanyDaoImpl.INSTANCE.getById(1));
		String name = "Test";
		computer.setName(name);
		computerDaoImpl.createComputer(computer);
		
		/** on vérifie qu'on récupère bien le bon ordinateur 
		computer = computerDaoImpl.getComputerByName(name);
		assertEquals(name, computer.getName());
		
		computerDaoImpl.deleteComputer(computer.getId());
		
		/** on vérifie que l'ordinateur a bien été supprimé 
		computer = computerDaoImpl.getComputerByName(name);
		
		assertEquals(null, computer);
	}
	@Ignore
	@Test(expected = DAOException.class)
	public void testFromToWithNegative(){
		computerDaoImpl.getFromTo(-5, 5);
	}
	@Ignore
	@Test
	public void testFromTo(){
		assertTrue(computerDaoImpl.getFromTo(0, 10).size() == 10);
	}
	*/
}
