package Test;

import Helper.Variables;
import Helper.ProbabilityGenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProbabilityGeneratorTest {
    @Before
	public void setUp() throws Exception {
	}

	@Test
	// stay at home prob. test for SARS
	public void testStayAtHome() {
		ProbabilityGenerator prob = new ProbabilityGenerator();
		int count = 0;
		for (int i =0; i<10000;i++) {
			if(prob.stayAtHome()) count++;
		}
		//System.out.println(count);
		double low = (Variables.StayAtHome_Rate-0.05)*10000;
		double hi = (Variables.StayAtHome_Rate+0.05)*10000;
		int res;
		if (count>=low && count<=hi) {
			res = 1;
		}
		else res = 0;
		assertEquals(1,res);
	}
	
	@Test
	// stay at home prob. test for SARS
	public void testStayAtHome2() {
		ProbabilityGenerator prob = new ProbabilityGenerator();
		int count = 0;
		for (int i =0; i<10000;i++) {
			if(prob.stayAtHome()) count++;
		}
		//System.out.println(count);
		double low = (Variables.StayAtHome_Rate2-0.05)*10000;
		double hi = (Variables.StayAtHome_Rate2+0.05)*10000;
		int res;
		if (count>=low && count<=hi) {
			res = 1;
		}
		else res = 0;
		assertEquals(1,res);
	}

	@Test
	public void testInfected_COVID19() {
		ProbabilityGenerator prob = new ProbabilityGenerator();
		int count = 0;
		for (int i =0; i<100000;i++) {
			if(prob.Infected_COVID19()) count++;
		}
		//System.out.println(count);
		double low = (Variables.Infection_Rate-0.5)*1000;
		double hi = (Variables.Infection_Rate+0.5)*1000;
		int res;
		if (count>=low && count<=hi) {
			res = 1;
		}
		else res = 0;
		assertEquals(1,res);	
	}

	@Test
	public void testDead_COVID19() {
		ProbabilityGenerator prob = new ProbabilityGenerator();
		int count = 0;
		for (int i =0; i<100000;i++) {
			if(prob.Dead_COVID19()) count++;
		}
		//System.out.println(count);
		double low = (Variables.Case_Fatality-0.5)*1000;
		double hi = (Variables.Case_Fatality+0.5)*1000;
		int res;
		if (count>=low && count<=hi) {
			res = 1;
		}
		else res = 0;
		assertEquals(1,res);	
	}

	@Test
	public void testRecoveryTime_COVID19() {

		ProbabilityGenerator prob = new ProbabilityGenerator();
		int count = 0;
		for (int i =0; i<10000;i++) {
			if(prob.RecoveryTime_COVID19()==Variables.Mild_Recovery_Time) count++;
		}
		System.out.println(count);
		double low = (Variables.Mild_Disease_Severity-5)*100;
		double hi = (Variables.Mild_Disease_Severity+5)*100;
		int res;
		if (count>=low && count<=hi) {
			res = 1;
		}
		else res = 0;
		assertEquals(1,res);
	}

	@Test
	public void testInfected_SARS() {
		ProbabilityGenerator prob = new ProbabilityGenerator();
		int count = 0;
		for (int i =0; i<100000;i++) {
			if(prob.Infected_SARS()) count++;
		}
		//System.out.println(count);
		//System.out.println(random.nextFloat());
		double low = (Variables.Infection_Rate2-0.2)*1000;
		double hi = (Variables.Infection_Rate2+0.2)*1000;
		int res;
		if (count>=low && count<=hi) {
			res = 1;
		}
		else res = 0;
		assertEquals(1,res);
	}

	@Test
	public void testDead_SARS() {
		ProbabilityGenerator prob = new ProbabilityGenerator();
		int count = 0;
		for (int i =0; i<10000;i++) {
			if(prob.Dead_SARS()) count++;
		}
		//System.out.println(count);
		double low = (Variables.Case_Fatality2-1)*100;
		double hi = (Variables.Case_Fatality2+1)*100;
		int res;
		if (count>=low && count<=hi) {
			res = 1;
		}
		else res = 0;
		assertEquals(1,res);
	}

	@Test
	public void testRecoveryTime_SARS() {
		ProbabilityGenerator prob = new ProbabilityGenerator();
		int count = 0;
		for (int i =0; i<10000;i++) {
			if(prob.RecoveryTime_SARS()==Variables.Mild_Recovery_Time2) count++;
		}
		//System.out.println(count);
		double low = (Variables.Mild_Disease_Severity2*0.90)*100;
		double hi = (Variables.Mild_Disease_Severity2*1.1)*100;
		int res;
		if (count>=low && count<=hi) {
			res = 1;
		}
		else res = 0;
		assertEquals(1,res);
	}

}
