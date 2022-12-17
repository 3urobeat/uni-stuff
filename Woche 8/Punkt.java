public class Punkt {
    
    double wertX, wertY; //x and y coords for this point
    
    //Contructor for this point
    public Punkt(double i, double j) {
        this.wertX = i;
        this.wertY = j;
    }
    
    
    public Punkt erstelleKopie() {
        return new Punkt(this.wertX, this.wertY);
    }
    
    public double gibX() {
        return wertX;
    }
    
    public double gibY() {
        return wertY;
    }
    
    public double entfernungBerechnen(Punkt b) {
        return Math.sqrt((this.gibX() - b.gibX()) * (this.gibX() - b.gibX()) + (this.gibY() - b.gibY()) * (this.gibY() - b.gibY()));
    }
    
    public boolean liegtImUrsprung() {
        return this.gibX() == 0 && this.gibY() == 0;
    }
    
    public boolean istIdentisch(Punkt b) {
        return this.gibX() == b.gibX() && this.gibY() == b.gibY();
    }
    
    public Punkt addiereHinzu(Punkt b) {
        return this.verschiebe(b.gibX(), b.gibY());
    }
    
    public Punkt verschiebe(double k, double l) {
        this.wertX += k;
        this.wertY += l;
        
        return this;
    }
}