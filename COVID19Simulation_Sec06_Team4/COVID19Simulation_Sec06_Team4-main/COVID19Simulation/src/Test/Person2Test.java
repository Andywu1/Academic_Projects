package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import SARS.SARSJPanel;
import SARS.Person2;
import SARS.City2;
import Helper.Variables;

import org.junit.Before;
import org.junit.Test;

import COVID19.Person1.Status;


public class Person2Test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetStatus() {
		Person2 p = new Person2(40,50);
		assertEquals("Negative",p.getStatus());
	}

	@Test
	public void testSetStatus() {
		Person2 p = new Person2(0,0);
		p.setStatus("Positive");
		assertEquals("Positive",p.getStatus());	}

	@Test
	public void testDistance() {
		Person2 p1 = new Person2(0,0);
		Person2 p2 = new Person2(40,30);
		assertEquals(50.0,p1.distance(p2),0);
		Person2 p3 = new Person2(0,0);
		Person2 p4 = new Person2(5,12);
		assertEquals(13.0,p3.distance(p4),0);
	}
	
	@Test
	// We have a safe distance setting for infection of SARS. 
	// Here's its validation test.
	public void testInfectedDistance() {
		Variables.Total_Population = 1;
		Variables.Safe_Distance = (float) 20;
		List<Person2> person2List = new ArrayList<Person2>();
		Person2 p1 = new Person2(0,0);
		Person2 p2 = new Person2(30,40);
		p2.setStatus("Exposed");
		person2List.add(p2);
		person2List.add(p1);
		SARSJPanel.dayTime = 1;
		
		for (Person2 person2 : person2List) {
            if (p2.distance(person2) < Variables.Safe_Distance){
                if (person2.getStatus().equals(Status.Negative.toString())) {
                    person2.getExposed();
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
		Variables.Infection_Rate2 = 99;
		SARSJPanel.dayTime = 0;
		List<Person2> citizens = City2.getInstance().getPersonList();
		Person2 p1 = new Person2(0,0);
		p1.setStatus("Exposed");
		Person2 p2 = new Person2(0,1);
		assertEquals("Negative",p2.getStatus());
		citizens.add(p1);
		citizens.add(p2);
		
		for (Person2 person1 : citizens) {
			person1.updateStatus();
		}
		
		assertEquals("Exposed",p2.getStatus());
	}

	
	@Test
	// Test for status change from Exposed to Positive.
	// Rise the rate of infection to very high.
	public void testPositive() { 
		Variables.Total_Population = 2;
		Variables.Infection_Rate2 = 99;
		SARSJPanel.dayTime = 0;
		Variables.Case_Fatality2 = 2;
		List<Person2> citizens = City2.getInstance().getPersonList();
		Person2 p1 = new Person2(0,0);
		p1.setStatus("Exposed");
		Person2 p2 = new Person2(0,1);
		assertEquals("Negative",p2.getStatus());
		citizens.add(p1);
		citizens.add(p2);
		
		for (Person2 person1 : citizens) {
			person1.updateStatus();
		}
		SARSJPanel.dayTime = 250;
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
		Variables.Case_Fatality2 = 99;
		SARSJPanel.dayTime = 0;
		List<Person2> citizens = City2.getInstance().getPersonList();
		Person2 p1 = new Person2(0,0);
		p1.setStatus("Exposed");
		Person2 p2 = new Person2(0,1);
		assertEquals("Negative",p2.getStatus());
		citizens.add(p1);
		citizens.add(p2);
		
		for (Person2 person1 : citizens) {
			person1.updateStatus();
		}
		assertEquals("Exposed",p1.getStatus());
		SARSJPanel.dayTime = 250;
		p1.updateStatus();
	
		assertEquals("Dead",p1.getStatus());
	}
	
	
	@Test
	// Test for status change from Exposed to Dead.
	// Deduce the case fatality and recovery time to make sure everyone get cured.
	public void testRecovered() { 
		Variables.Total_Population = 2;
		Variables.Infection_Rate2 = 99;
		SARSJPanel.dayTime = 0;
		Variables.Case_Fatality2 = 0;
		Variables.Mild_Disease_Severity2= 99;
		Variables.Mild_Recovery_Time2 =1;
		List<Person2> citizens = City2.getInstance().getPersonList();
		Person2 p1 = new Person2(0,0);
		p1.setStatus("Exposed");
		Person2 p2 = new Person2(0,1);
		assertEquals("Negative",p2.getStatus());
		citizens.add(p1);
		citizens.add(p2);
		
		for (Person2 person1 : citizens) {
			person1.updateStatus();
		}
		SARSJPanel.dayTime = 100;
		p2.updateStatus();
		assertEquals("Positive",p2.getStatus());
		SARSJPanel.dayTime = 110;
		p2.updateStatus();
		assertEquals("Recovered",p2.getStatus());
	}
}
