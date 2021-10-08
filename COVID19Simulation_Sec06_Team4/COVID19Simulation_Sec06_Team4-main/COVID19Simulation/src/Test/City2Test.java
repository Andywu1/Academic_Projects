package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import SARS.City2;
import SARS.Person2;

public class City2Test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPopulation() {
		City2 city2 = new City2();
		Person2 p1 = new Person2(40,50);
		p1.setStatus("Dead");
		Person2 p2 = new Person2(40,50);
		p2.setStatus("Negative");
		city2.getPersonList().add(p1);
		city2.getPersonList().add(p2);
		assertEquals(1,city2.getPopulation("Dead"));
	}

}
