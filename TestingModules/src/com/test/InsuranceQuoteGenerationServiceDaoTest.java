package com.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.bean.CreateAccountBean;
import com.bean.CustomerDetails;
import com.bean.PolicyDetails;
import com.bean.ProfileCreation;
import com.dao.InsuranceQuoteGenerationIMPL;
import com.exception.InsuranceQuoteGenerationException;

public class InsuranceQuoteGenerationServiceDaoTest {

	static InsuranceQuoteGenerationIMPL dao;
	CustomerDetails details=new CustomerDetails();
	static CreateAccountBean createaccountBean;
	static ProfileCreation profileCreation;
	static PolicyDetails policyDetails;
	@BeforeClass
	public static void initialize() {
		System.out.println("in before class");
		dao = new InsuranceQuoteGenerationIMPL();
		createaccountBean= new CreateAccountBean();
		profileCreation=new  ProfileCreation ();
		CustomerDetails details=new CustomerDetails();
	}
	
	@Test
	public void testaccountCreation()  {

		createaccountBean.setUsername("Jaya");;
		createaccountBean.setInsuredName("Jaya Ghosh");;
		createaccountBean.setInsuredStreet("kasba");
		createaccountBean.setInsuredCity("Kolkata");
		createaccountBean.setInsuredState("Westbengal");
		createaccountBean.setInsuredZip(700102);
		createaccountBean.setBusinessSegment("Apartment");
		createaccountBean.setAccountNumber(1000000001);
		createaccountBean.setAgentName("Sufall");
		assertTrue("Data Inserted successfully");
		System.out.println("Account creation successfully tested");
		
	}
	
	
	@Test
	public void  testaddProfile() {
		profileCreation.setUserName("Ram");
		profileCreation.setPassword("ram.charan");	
		profileCreation.setRoleCode("admin");
		
		assertTrue("Data Inserted successfully");
		System.out.println("profile  successfully tested");
	} 
	@Test
	public void testViewAll() throws InsuranceQuoteGenerationException, SQLException, IOException {
		assertNotNull(dao.customerDetails());
	}
	@Test
	public void testView() throws InsuranceQuoteGenerationException, SQLException, IOException {
		assertNotNull(dao.createPolicy("Apartment"));
	}
	@Test
	public void testView1() throws InsuranceQuoteGenerationException, SQLException, IOException {
		assertNotNull(dao.getPolicyDetails("Sushanth"));
	}
	
	@Test
	public void findAgent() throws InsuranceQuoteGenerationException, SQLException, IOException {
		assertNotNull(dao.findAgentName("Sushanth"));
	}
	private void assertTrue(String string) {
		// TODO Auto-generated method stub
		
	}
	
}
