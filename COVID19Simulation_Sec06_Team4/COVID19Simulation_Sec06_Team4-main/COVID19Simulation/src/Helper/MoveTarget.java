package Helper;




public class MoveTarget {
    private int x;
    private int y;
    private boolean arrived=false;// to judge if the person arrives the target point

    public MoveTarget(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived() {
        this.arrived = true;
    }
}
