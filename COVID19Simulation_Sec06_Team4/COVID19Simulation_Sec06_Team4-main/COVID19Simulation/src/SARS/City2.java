package SARS;

import Helper.CityLocation;
import Helper.Variables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class City2 {
    private CityLocation location;
    private Person2 person;
    private List<Person2> personList = new ArrayList<Person2>();

    private static City2 city2 = new City2();
    public static City2 getInstance() {
        return city2;
    }
    public List<Person2> getPersonList() {
        return personList;
    }

    //COVID19.City constructor
    public City2() {
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
            person = new Person2( x, y);
            personList.add(person);
        }
    }

    //Get the total number of a group of resident with a certain status
    public int getPopulation(String status) {
        int i = 0;
        for (Person2 person : personList) {
            if (person.getStatus().equals(status)) {
                i++;
            }
        }
        return i;
    }
}
