package COVID19;

import Helper.CityLocation;
import Helper.Variables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class City1 {
    private CityLocation location;
    private Person1 person1;
    private List<Person1> person1List = new ArrayList<Person1>();

    private static City1 city1 = new City1();
    public static City1 getInstance() {
        return city1;
    }
    public List<Person1> getPersonList() {
        return person1List;
    }

    //COVID19.City constructor
    public City1() {
        //set city location
        this.location = new CityLocation(500,400);

        //set the resident to residentList
        for (int i = 0; i < Variables.Total_Population; i++) {
            Random random = new Random();
            /*
             * Math.sqrt(b)*random.nextGaussian()+a
             * Generate a point(a,b) which follows normal distribution
             * Standard deviation: 150, mean: city location
             */
            int x = (int) (150 * random.nextGaussian() + location.getOriginX());
            int y = (int) ((150 * random.nextGaussian()) + location.getOriginY());
            if (x > Variables.City_Width) {
                x = Variables.City_Width;
            }
            if (y > Variables.City_Height) {
                y = Variables.City_Height;
            }
            person1 = new Person1( x, y);
            person1List.add(person1);
        }
    }

    //Get the total number of a group of resident with a certain status
    public int getPopulation(String status) {
        int i = 0;
        for (Person1 person1 : person1List) {
            if (person1.getStatus().equals(status)) {
                i++;
            }
        }
        return i;
    }
}
