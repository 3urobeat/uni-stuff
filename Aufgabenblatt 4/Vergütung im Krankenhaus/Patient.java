/*
 * Klasse zur Modellierung eines Patienten
*/
abstract class Patient {
    
    private String name;
    private int krankheitsklasse;
    
    // Hier Ihre Loesung einfuegen
    
    /**
     * Konstruktor für einen Patienten
     * @param name Name des Patienten
     * @param krankheitsklasse Die Krankheitsklasse (1, 2 oder 3) des Patienten
     */ 
    public Patient(String name, int krankheitsklasse) {
        this.name = name;
        this.krankheitsklasse = krankheitsklasse;
    }
    
    
    /**
     * Gibt den Namen dieses Patienten zurück
     */ 
    public String getName() {
        return this.name;
    }
    
    
    /**
     * Gibt die Vergütung basierend auf der Krankheitsklasse des Patienten zurück
     */ 
    public double getVerguetung() {
        switch (this.krankheitsklasse) {
            case 1:
                return 150;
            case 2:
                return 500;
            case 3:
                return 1000;
            default:
                return 0;
        }
    }
    
    public abstract void zeigePatient();
}