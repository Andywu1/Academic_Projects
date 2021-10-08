package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import COVID19.COVID19JPanel;
import COVID19.City1;
import COVID19.Person1;
import COVID19.Person1.Status;
import Helper.Variables;

public class Person1Test {

	@Before
	public void setUp() throws Exception {
		Person1 p = new Person1(40,50);
	}

	@Test
	public void testGetStatus() {
		Person1 p = new Person1(40,50);
		assertEquals("Negative",p.getStatus());
	}

	@Test
	public void testSetStatus() {
		Person1 p = new Person1(0,0);
		p.setStatus("Positive");
		assertEquals("Positive",p.getStatus());
	}

	@Test
	public void testDistance() {
		Person1 p1 = new Person1(0,0);
		Person1 p2 = new Person1(40,30);
		assertEquals(50.0,p1.distance(p2),0);
		Person1 p3 = new Person1(0,0);
		Person1 p4 = new Person1(5,12);
		assertEquals(13.0,p3.distance(p4),0);
	}

	@Test
	// We have a safe distance setting for infection of COVID19. 
	// Here's its validation test.
	public void testInfectedDistance() {
		Variables.Total_Population = 1;
		Variables.Safe_Distance = (float) 20;
		List<Person1> person1List = new ArrayList<Person1>();
		Person1 p1 = new Person1(0,0);
		Person1 p2 = new Person1(30,40);
		p2.setStatus("Exposed");
		person1List.add(p2);
		person1List.add(p1);
		COVID19JPanel.dayTime = 1;
		
		for (Person1 person1 : person1List) {
            if (p2.distance(person1) < Variables.Safe_Distance){
                if (person1.getStatus().equals(Status.Negative.toString())) {
                    person1.getExposed();
                }
            }
        }
		//p2.updateStatus();
		assertEquals("Negative",p1.getStatus());
	}
	
	
	@Test
	// Test for status change from Negative to Exposed.
	// Rise the rate of infection to very high.
	// p1 was exposed and infect p2.
	public void testExposed() { 
		Variables.Total_Population = 2;
		Variables.Infection_Rate = 99;
		COVID19JPanel.dayTime = 0;
		List<Person1> citizens = City1.getInstance().getPersonList();
		Person1 p1 = new Person1(0,0);
		p1.setStatus("Exposed");
		Person1 p2 = new Person1(0,1);
		assertEquals("Negative",p2.getStatus());
		citizens.add(p1);
		citizens.add(p2);
		
		for (Person1 person1 : citizens) {
			person1.updateStatus();
		}
		
		assertEquals("Exposed",p2.getStatus());
	}
	
	
	@Test
	// Test for status change from Exposed to Positive.
	// Rise the rate of infection to very high.
	public void testPositive() { 
		Variables.Total_Population = 2;
		Variables.Infection_Rate = 99;
		COVID19JPanel.dayTime = 0;
		Variables.Case_Fatality = 2;
		List<Person1> citizens = City1.getInstance().getPersonList();
		Person1 p1 = new Person1(0,0);
		p1.setStatus("Exposed");
		Person1 p2 = new Person1(0,1);
		assertEquals("Negative",p2.getStatus());
		citizens.add(p1);
		citizens.add(p2);
		
		for (Person1 person1 : citizens) {
			person1.updateStatus();
		}
		COVID19JPanel.dayTime = 250;
		p2.updateStatus();
		
		assertEquals("Positive",p2.getStatus());
	}
	
	
	
	@Test
	// Test for status change from Exposed to Dead.
	// Rise the risk to infect to 99 and rise the case fatality to 99 as well.
	// So that people will change their status to Positive fast and then turn Dead.
	public void testDead() {
		Variables.Total_Population = 2;
		Variables.Infection_Rate = 99;
		Variables.Case_Fatality = 99;
		COVID19JPanel.dayTime = 0;
		List<Person1> citizens = City1.getInstance().getPersonList();
		Person1 p1 = new Person1(0,0);
		p1.setStatus("Exposed");
		Person1 p2 = new Person1(0,1);
		assertEquals("Negative",p2.getStatus());
		citizens.add(p1);
		citizens.add(p2);
		
		for (Person1 person1 : citizens) {
			person1.updateStatus();
		}
		assertEquals("Exposed",p1.getStatus());
		COVID19JPanel.dayTime = 250;
		p1.updateStatus();
	
		assertEquals("Dead",p1.getStatus());
	}
	
	@Test
	// Test for status change from Exposed to Dead.
	// Deduce the case fatality and recovery time to make sure everyone get cured.
	public void testRecovered() { 
		Variables.Total_Population = 2;
		Variables.Infection_Rate = 99;
		COVID19JPanel.dayTime = 0;
		Variables.Case_Fatality = 0;
		Variables.Mild_Disease_Severity= 99;
		Variables.Mild_Recovery_Time =1;
		List<Person1> citizens = City1.getInstance().getPersonList();
		Person1 p1 = new Person1(0,0);
		p1.setStatus("Exposed");
		Person1 p2 = new Person1(0,1);
		assertEquals("Negative",p2.getStatus());
		citizens.add(p1);
		citizens.add(p2);
		
		for (Person1 person1 : citizens) {
			person1.updateStatus();
		}
		COVID19JPanel.dayTime = 100;
		p2.updateStatus();
		assertEquals("Positive",p2.getStatus());
		COVID19JPanel.dayTime = 110;
		p2.updateStatus();
		assertEquals("Recovered",p2.getStatus());
	}

}
