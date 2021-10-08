package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import COVID19.City1;
import COVID19.Person1;

public class City1Test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPopulation() {

		City1 city1 = new City1();
		Person1 p1 = new Person1(40,50);
		p1.setStatus("Dead");
		Person1 p2 = new Person1(40,50);
		p2.setStatus("Dead");
		city1.getPersonList().add(p1);
		city1.getPersonList().add(p2);
		assertEquals(2,city1.getPopulation("Dead"));
	}

}
