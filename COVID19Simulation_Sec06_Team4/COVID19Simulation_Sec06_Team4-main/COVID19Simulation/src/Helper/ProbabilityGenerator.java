package Helper;

import java.util.Random;

/**
 * Use normal distribution to decide certain probability
 */
public class ProbabilityGenerator {
    private Random random = new Random();
    
    public boolean stayAtHome(){
    	boolean result = false;
    	if (random.nextInt(100) + 1 <= Variables.StayAtHome_Rate*100){
    		result = true;//probability to stay at home for COVID19
    	}
    	return result;
    }
    
    public boolean stayAtHome2() {
    	boolean result = false;
    	if (random.nextInt(100) + 1 <= Variables.StayAtHome_Rate2*100){
    		result = true;//probability to stay at home for SARS
    	}
    	return result;
    }
    
    
    /*
     * COVID19
     */
    public boolean Infected_COVID19(){
        boolean result = false;
        if (random.nextInt(100) + 1<= Variables.Infection_Rate){
            result = true;//5% probability to get infected
        }
        return result;
    }

    public boolean Dead_COVID19(){
        boolean result = false;
        if (random.nextInt(100) + 1 <= Variables.Case_Fatality){
            result = true;//2% probability to die from COVID19
        }
        return result;
    }

    public int RecoveryTime_COVID19(){
        int time;
        if (random.nextInt(100) + 1<= Variables.Mild_Disease_Severity){
            time = Variables.Mild_Recovery_Time; // Mild symptoms' recovery time
        }else   {
            time = Variables.Severe_Recovery_Time;//Severe symptoms' recovery time
        }
        return time;
    }
    
    /*
     * SARS
     */
    public boolean Infected_SARS(){
        boolean result = false;
        if (random.nextInt(1000) <= Variables.Infection_Rate2*10){
            result = true;//1% probability to get infected
        }
        return result;
    }

    public boolean Dead_SARS(){
        boolean result = false;
        if (random.nextInt(100) + 1 <= Variables.Case_Fatality2){
            result = true;//10% probability to die from SARS
        }
        return result;
    }

    public int RecoveryTime_SARS(){
        int time;
        if (random.nextInt(100) + 1<= Variables.Mild_Disease_Severity2){
            time = Variables.Mild_Recovery_Time2; // Mild symptoms' recovery time
        }else   {
            time = Variables.Severe_Recovery_Time2;//Severe symptoms' recovery time
        }
        return time;
    }
    


}