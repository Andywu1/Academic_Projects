package SARS;

import Helper.MoveTarget;
import Helper.ProbabilityGenerator;
import Helper.Variables;

import java.util.Random;

public class Person2 {
    private int x; // x coordinate of the person's origin
    private int y; // y coordinate of the person's origin
    int ux = 1; //unit move distance on x axis
    int uy = 1; //unit move distance on y axis
    private String status;
    private int ExposedTime = 0;
    private int PositiveTime = 0;
    int infectedPersonNum = 0;
    private MoveTarget moveTarget;
    private final ProbabilityGenerator probability = new ProbabilityGenerator();
    private final Random random = new Random();

    public Person2(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = Status.Negative.toString();
    }

    /**
     * A person's different Status
     */
    public enum Status{
        Negative("Negative"),
        Exposed("Exposed"),
        Positive("Positive"),
        Dead("Dead"),
        Recovered("Recovered");

        private final String value;
        Status(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void getExposed() {
        status = Status.Exposed.toString();
        ExposedTime = SARSJPanel.dayTime;
    }

    public void getPositive() {
        status = Status.Positive.toString();
        PositiveTime = SARSJPanel.dayTime;
    }

    /**
     * Calculate the distance to a target person
     */
    public double distance(Person2 person) {
        return Math.sqrt(Math.pow(x - person.getX(), 2) + Math.pow(y - person.getY(), 2));
    }


    /**
     * The person moves around the city
     */
    public void moveAroundCity(){
        if (probability.stayAtHome2()){
            return;//stay at home
        }

        //generate new moveTarget if the moveTarget is null or already arrived
        if (moveTarget==null || moveTarget.isArrived()){

            /* use normal distribution to generate the target point with the mean=(x,y),
            *standard deviation = 150
            */
            int targetx = (int)(150 * random.nextGaussian() + x);
            int targety = (int)(150 * random.nextGaussian() + y);
            moveTarget = new MoveTarget(targetx,targety);

            //if the move target is out of city, set the target at the city boundary
            if (moveTarget.getX() > Variables.City_Width){
                moveTarget.setX(Variables.City_Width);
            }
            if (moveTarget.getY() > Variables.City_Height){
                moveTarget.setY(Variables.City_Height);
            }
        }else {
        //calculate the distance to the target point
            double targetDistance = Math.sqrt(Math.pow(moveTarget.getX()-x, 2) + Math.pow(moveTarget.getY()-y, 2));
            if (targetDistance <= 1 ) {
                moveTarget.setArrived();
                return;
            }
        }

        //move to the target
        int ox;//direction on x axis
        int oy;//direction on y axis

        int dx = moveTarget.getX() - x;
        int dy = moveTarget.getY() - y;

        if (dx >= 0){//target point is on the right of the person
            ox = 1;
        }else{
            ox = -1;
        }

        if (dy >= 0){//target point is upper of the person
            oy = 1;
        }else{
            oy =-1;
        }

        //Movement boundary on x axis
        if (getX() > Variables.City_Width || getX() < 0) {
            moveTarget = null;
            ox = -1;
        }
        //Movement boundary on x axis
        if (getY() > Variables.City_Height || getY() < 0) {
            moveTarget = null;
            oy = 1;
        }

        // update this person's position
        x += ux *ox;
        y += uy *oy;
    }

    /**
     * Update the person's status
     */
    public void updateStatus(){

        //Deal with Dead person
        if (status.equals(Status.Dead.toString())){
            return;
        }

        //Deal with Exposed person
        if (status.equals(Status.Exposed.toString())) {
            //To find people around the person within the Safe_Distance
            for (Person2 person : City2.getInstance().getPersonList()) {
                if (distance(person) < Variables.Safe_Distance2){
                    if (person.getStatus().equals(Status.Negative.toString())) {
                        person.getExposed();
                        infectedPersonNum++;
                        //Max people one infected person can infect
                        if (infectedPersonNum == Variables.R_Factor_SARS){
                            break;
                        }
                    }
                }
            }

            /*Pick up a random time to turn Positive based on the Average_Incubation_Time
              use normal distribution to generate a random Dead time with mean = 10 days, standard deviation = 2 day
            */
            double certainTime = Variables.Average_Incubation_Period2 + random.nextGaussian()*2;
            if (SARSJPanel.dayTime - ExposedTime > certainTime && status.equals(Status.Exposed.toString()) ){
                if (probability.Infected_SARS()){
                getPositive();
                }
            }

            //turn to Negative if ExposedTime > 14 days
            if (SARSJPanel.dayTime - ExposedTime >= Variables.Incubation_Period2 && status.equals(Status.Exposed.toString())){
                setStatus(Status.Negative.toString());
            }
        }

        //Deal with Positive person
        if (status.equals(Status.Positive.toString())) {
            //To find people around the person within the Safe_Distance
            for (Person2 person : City2.getInstance().getPersonList()) {
                if (distance(person) < Variables.Safe_Distance2){
                    if (person.getStatus().equals(Status.Negative.toString())) {
                        person.getExposed();
                        infectedPersonNum++;
                        //Max people on infected person can infect
                        if (infectedPersonNum == Variables.R_Factor_SARS){
                            break;
                        }
                    }
                }
            }

            //Positive person has a certain probability to die everyday based on the Case_Fatality ratio
            if (probability.Dead_SARS()) {
                setStatus(Status.Dead.toString());
            }

            /*Pick up a random time to turn Dead
             *use normal distribution to generate a random Dead time with mean = 20 days, standard deviation = 5 day
            */
            double certainTime = Variables.Average_Days_Death2 + random.nextGaussian()*5;
            if (SARSJPanel.dayTime - PositiveTime > certainTime && status.equals(Status.Positive.toString()) ){
                    setStatus(Status.Dead.toString());
            }

            //turn to Recovered if Positive time is greater the Recovery Time
            if (SARSJPanel.dayTime -PositiveTime >= probability.RecoveryTime_SARS() && status.equals(Status.Positive.toString())) {
                setStatus(Status.Recovered.toString());
            }
        }
        moveAroundCity();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
