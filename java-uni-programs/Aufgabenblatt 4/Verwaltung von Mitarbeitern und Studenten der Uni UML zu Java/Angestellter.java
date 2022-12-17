public class Angestellter extends Person implements Druckbar { //Hier jetzt das tolle Druckbar Interface implementieren (https://www.w3schools.com/java/java_interface.asp)
    
    private double gehalt = 24.465;
    private String abteilung = "ZUV";
    
    
    public Angestellter(String name, String vorname) {
        super(name, vorname);
    }
    
    public double getGehalt() {
        return this.gehalt;
    }
    
    public void setAbteilung(String bezeichnung) {
        this.abteilung = bezeichnung;
    }
    
    public boolean hatUrlaub() {
        return false;
    }
    
    public void drucken() {
        String emailText = "fehlt"; //Temporäre Variable die bei existierender email wieder überschrieben wird
        
        if (this.email.length() > 0) emailText = this.email;
        
        
        System.out.println(this.vorname + " " + this.getName() + ", E-Mail: " + emailText + ", Abteilung: " + this.abteilung);
    }
}