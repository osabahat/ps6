package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	private static PersonDomainModel person1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		person1 = new PersonDomainModel();
		person1.setFirstName("Albert");
		person1.setLastName("Einstein");
		person1.setBirthday(dDate);
		person1.setCity("Newark");
		person1.setPostalCode(19702);
		person1.setStreet("500 College Avenue");

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel person;	
		PersonDAL.deletePerson(person1.getPersonID());
		person = PersonDAL.getPerson(person1.getPersonID());
		assertNull("The Person shouldn't have been in the database",person);		
	}
	

	@Test
	public void Addtest() {
		PersonDomainModel person;		
		person = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",person);		
		PersonDAL.addPerson(person1);	
		
		person = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",person);
	}
	
	@Test
	public void UpdateTest()
	{		
		PersonDomainModel person;
		final String C_LASTNAME = "Smith";
		
		person = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",person);		
		PersonDAL.addPerson(person1);	
		
		person1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(person1);
		
		person = PersonDAL.getPerson(person1.getPersonID());

		assertTrue(person1.getLastName() == C_LASTNAME);
	}

	@Test
	public void DeleteTest()
	{		
		PersonDomainModel person;		
		person = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",person);	
		
		PersonDAL.addPerson(person1);			
		person = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID());
		assertNotNull("The Person should have been added to the database",person);
		
		PersonDAL.deletePerson(person1.getPersonID());
		person = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",person);	
		
	}
	
}



