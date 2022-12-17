/**
 * Klasse zur Modellierung eines Medizingeraetes
 */
public class Medizingeraet {
    // *** Ergaenzen Sie hier Ihre Implementierung
    
    private String name;
    private String typ;
    
    public Medizingeraet(String name, String typ) {
        this.name = name;
        this.typ = typ;
    }
    
    public String getName() {
        return name;
    }
    
    public String getTyp() {
        return typ;
    }
}
