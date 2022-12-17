public class Punkt {
    private int punktX;
    private int punktY;
    
    //Methods for getting and setting x & y
    public void setX(int x) {
        this.punktX = x;
    }
    
    public void setY(int y) {
        this.punktY = y;
    }
    
    public int getX() {
        return punktX;
    }
    
    public int getY() {
        return punktY;
    }
    
    
    public double streckeBis(Punkt ziel) {
        double dx = ziel.getX() - this.punktX;
        double dy = ziel.getY() - this.punktY;
        
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}