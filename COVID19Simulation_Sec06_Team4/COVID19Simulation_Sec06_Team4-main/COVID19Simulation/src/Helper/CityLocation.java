package Helper;


/**
 *Describe the city
 */
public class CityLocation {
    private int originX;
    private int originY;

    public CityLocation(int originX, int originY) {
        this.originX = originX;
        this.originY = originY;
    }

    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }
}
