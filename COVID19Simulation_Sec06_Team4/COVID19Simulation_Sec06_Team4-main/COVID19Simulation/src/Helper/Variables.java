package Helper;

/**
 * Key variables
 */
public class Variables {
    //person count
    public static int Initial_Infected_Num = 50;
    public static int Total_Population = 5000;

    public static boolean WearMask_COVID19 = false;
    public static boolean Quarantine_COVID19 = false;
    public static boolean GovernmentAction_COVID19 = false; //The prevalence of testing and contact tracing;
    
    public static boolean WearMask_SARS = false;
    public static boolean Quarantine_SARS = false;
    public static boolean GovernmentAction_SARS= false; //The prevalence of testing and contact tracing;
    
    
    public static boolean isWearMask() {
		return WearMask_COVID19;
	}
	public static void setWearMask(boolean wearMask) {
		WearMask_COVID19 = wearMask;
	}
	public static boolean isQuarantine() {
		return Quarantine_COVID19;
	}
	public static void setQuarantine(boolean quarantine) {
		Quarantine_COVID19 = quarantine;
	}
	public static boolean isGovernmentAction() {
		return GovernmentAction_COVID19;
	}
	public static void setGovernmentAction(boolean governmentAction) {
		GovernmentAction_COVID19 = governmentAction;
	}
	
	
	public static boolean isWearMask_SARS() {
		return WearMask_SARS;
	}
	public static void setWearMask_SARS(boolean wearMask_SARS) {
		WearMask_SARS = wearMask_SARS;
	}
	public static boolean isQuarantine_SARS() {
		return Quarantine_SARS;
	}
	public static void setQuarantine_SARS(boolean quarantine_SARS) {
		Quarantine_SARS = quarantine_SARS;
	}
	public static boolean isGovernmentAction_SARS() {
		return GovernmentAction_SARS;
	}
	public static void setGovernmentAction_SARS(boolean governmentAction_SARS) {
		GovernmentAction_SARS = governmentAction_SARS;
	}



	/*
     * Assume that the percentage of people in the city staying at home is 15% before COVID-19
     * Reference:https://www.safegraph.com/data-examples/covid19-shelter-in-place?s=US&d=09-13-2020&t=counties&m=index
     * Data Methodology:https://docs.google.com/document/d/1k_9LGQn95P5gHsSeuBdzgtEWGGCmzXdcOkcphWi0Cas/edit
     *
     * Person's stay_at_home rate range [0,1]
     * 0 is the staying at home
     * 100 is moving around city
     */
    public static float StayAtHome_Rate = Quarantine_COVID19? 0.99f : 0.15f;
    public static float StayAtHome_Rate2 = Quarantine_SARS ? 0.99f : 0.15f;
    
    /**
     * COVID19
     */
    
    /*
     *  The rate of people get infected in USA: 4.5%
     *  Calculated by Confirmed_Cases_in_USA/Total_population_in_USA
     */
    public static int Infection_Rate = GovernmentAction_COVID19? 2 : 5;
    /**
     * Average number of of secondary infections produced by a single infected person
     * https://www.gov.uk/guidance/the-r-number-in-the-uk#latest-r-number-and-growth-rate
     * https://www.bbc.com/news/health-52903787
     * https://med.sina.cn/article_detail_103_1_82609.html
     * 
     * COVID19 and SARS have the same R factor
     */
    public static int R_Factor_COVID19 = Quarantine_COVID19 ? 1:3;

    
    /*
     * The percentage of different severity of symptomatic infection
     * https://www.uptodate.com/contents/coronavirus-disease-2019-covid-19-clinical-features
     *
     * Different severity has different recover time
     */ 
    public static int Mild_Disease_Severity = 81;//Severe:0.14;Critical:0.05

    public static int Mild_Recovery_Time = 140;// 2 weeks, 10 = 1 day
    public static int Severe_Recovery_Time = 420;// 6 weeks, 10 = 1 day

    
    /*
     * Case Fatality Ratio
     * the number of deaths divided by the number of confirmed cases
     * https://coronavirus.jhu.edu/data/mortality
     */
    public static int Case_Fatality= 2;
    public static int Average_Days_Death = 190;//190 = 19 days

    public static int Incubation_Period = 140;// 140 = 14 days
    public static int Average_Incubation_Period = 50;// 50 = 5 days
    public static float Safe_Distance = WearMask_COVID19 ? 3f : 6f; //1f=2 feet
    
    /**
     * SARS
     * https://www.nhs.uk/conditions/sars/
     * https://www.hopkinsmedicine.org/health/conditions-and-diseases/severe-acute-respiratory-syndrome-sars
     * https://www.cidrap.umn.edu/news-perspective/2003/05/estimates-sars-death-rates-revised-upward
     * https://www.who.int/csr/sars/en/WHOconsensus.pdf
     */
    public static float Infection_Rate2 = GovernmentAction_SARS? 0.5f : 1f;
    
    //COVID19 and SARS have the same R factor
    public static int R_Factor_SARS = Quarantine_SARS ? 1:3;
    
    public static int Mild_Disease_Severity2 = 75;//Severe:25%

    public static int Mild_Recovery_Time2 = 100;// 100 = 10 day
    public static int Severe_Recovery_Time2 =  420;// 420 = 42 days
    
    
    public static int Case_Fatality2= 10;
    public static int Average_Days_Death2 = 200;//200 = 20 days
    
    public static int Incubation_Period2 = 100;// 100 = 10 days
    public static int Average_Incubation_Period2 = 60;// 60 = 6 days
    public static float Safe_Distance2 = WearMask_SARS ? 1f : 3f; //1f=2 feet
    
    
    /**
     * The size of the city
     */
    public static final int City_Width = 900;
    public static final int City_Height = 800;

}