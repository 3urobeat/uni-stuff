public class Gummiwuerfel implements Produkt{
	double hoehe;
	double laenge;
	double breite;
	double pressfaktor;
	public Gummiwuerfel(double hoehe){
		this.hoehe = hoehe;
		this.breite= hoehe;
		this.laenge= hoehe;
		this.pressfaktor = 1;
	}
	public double getBreite(){
		return this.breite;
	}
    public void setBreite(double breite){
    	this.breite = breite;
    }
    public double getLaenge(){
    	return this.laenge;
    }
    public void setLaenge(double laenge){
    	this.laenge = laenge;
    }
    public double getHoehe(){
    	return this.hoehe;
    }
    public void setHoehe(double hoehe){
    	this.hoehe = hoehe;
    }
    public void druecke(double pressfaktor){
    	this.pressfaktor += pressfaktor;
    	
    	this.hoehe = this.hoehe / Math.round(this.pressfaktor/5);
    	
    }
    public void print() {
    	System.out.println("Gummiwürfel: " + String.format("%.2f", this.laenge) + "x" + String.format("%.2f", this.breite) + "x" + String.format("%.2f", this.hoehe));
    }
}