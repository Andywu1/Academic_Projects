package COVID19;

import Helper.MoveTarget;
import Helper.ProbabilityGenerator;
import Helper.Variables;

import java.util.Random;

public class Person1 {
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

    public Person1(int x, int y) {
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
        ExposedTime = COVID19JPanel.dayTime;
    }

    public void getPositive() {
        status = Status.Positive.toString();
        PositiveTime = COVID19JPanel.dayTime;
    }

    /**
     * Calculate the distance to a target person
     */
    public double distance(Person1 person1) {
        return Math.sqrt(Math.pow(x - person1.getX(), 2) + Math.pow(y - person1.getY(), 2));
    }


    /**
     * The person moves around the city
     */
    public void moveAroundCity(){
        if (probability.stayAtHome()){
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
            for (Person1 person1 : City1.getInstance().getPersonList()) {
                if (distance(person1) < Variables.Safe_Distance){
                    if (person1.getStatus().equals(Status.Negative.toString())) {
                        person1.getExposed();
                        infectedPersonNum++;
                        //Max people one infected person can infect
                        if (infectedPersonNum == Variables.R_Factor_COVID19){
                            break;
                        }
                    }
                }
            }

            /*Pick up a random time to turn Positive based on the Average_Incubation_Time
              use normal distribution to generate a random Dead time with mean = 5 days, standard deviation = 2 day
            */
            double certainTime = Variables.Average_Incubation_Period + random.nextGaussian()*2;
            if (COVID19JPanel.dayTime - ExposedTime > certainTime && status.equals(Status.Exposed.toString()) ){
                if (probability.Infected_COVID19()){
                getPositive();
                }
            }

            //turn to Negative if ExposedTime > 14 days
            if (COVID19JPanel.dayTime - ExposedTime >= Variables.Incubation_Period && status.equals(Status.Exposed.toString())){
                setStatus(Status.Negative.toString());
            }
        }

        //Deal with Positive person
        if (status.equals(Status.Positive.toString())) {
            //To find people around the person within the Safe_Distance
            for (Person1 person1 : City1.getInstance().getPersonList()) {
                if (distance(person1) < Variables.Safe_Distance){
                    if (person1.getStatus().equals(Status.Negative.toString())) {
                        person1.getExposed();
                        infectedPersonNum++;
                        //Max people on infected person can infect
                        if (infectedPersonNum == Variables.R_Factor_COVID19){
                            break;
                        }
                    }
                }
            }

            //Positive person has a certain probability to die everyday based on the Case_Fatality ratio
            if (probability.Dead_COVID19()) {
                setStatus(Status.Dead.toString());
            }

            /*Pick up a random time to turn Dead
             *use normal distribution to generate a random Dead time with mean = 19 days, standard deviation = 5 day
            */
            double certainTime = Variables.Average_Days_Death + random.nextGaussian()*5;
            if (COVID19JPanel.dayTime - PositiveTime > certainTime && status.equals(Status.Positive.toString()) ){
                    setStatus(Status.Dead.toString());
            }

            //turn to Recovered if Positive time is greater the Recovery Time
            if (COVID19JPanel.dayTime -PositiveTime >= probability.RecoveryTime_COVID19() && status.equals(Status.Positive.toString())) {
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
