/**
 * Klasse zur Modellierung eines Operationssaals
 */
public class Operationssaal {
    // *** Ergaenzen Sie hier Ihre Implementierung
    
    private String name;
    private int raumnummer;
    private Medizingeraet[] geraete;

    public Operationssaal(String name, int raumnummer, Medizingeraet[] geraete) {
        this.name = name;
        this.raumnummer = raumnummer;
        this.geraete = geraete;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String neuerName) {
        this.name = neuerName;
    }
    
    public int getRaumnummer() {
        return raumnummer;
    }
    
    public void zeigeGeraete() {
        for (int i = 0; i < geraete.length; i++) {
            System.out.println(geraete[i].getName() + ": " + geraete[i].getTyp());
        }
    }
    
    public boolean istVollstaendig() {
        return geraete.length == 2; //true if exactly two devices are in the array
    }
}
