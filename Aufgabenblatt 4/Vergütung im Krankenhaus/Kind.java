/*
 * Klasse zur Modellierung eines Kindes
 * 
 * Ist von Ihnen anzupassen
 */
class Kind extends Patient {
    
    private int alter;
    
    
    /**
     * Konstruktor für ein Kind
     * @param name Name des Patienten
     * @param krankheitsklasse Die Krankheitsklasse (1, 2 oder 3) des Patienten
     * @param alter Das Alter des Kindes
     */ 
    public Kind(String name, int krankheitsklasse, int alter) {
        super(name, krankheitsklasse);
        
        this.alter = alter;
    }
    
    
    /**
     * Gibt die Vergütung basierend auf der Krankheitsklasse des Patienten zurück * 1,25
     */ 
    public double getVerguetung() {
        return (super.getVerguetung()) * 1.25;
    }
    
    
    /**
     * Gibt den Namen und Alter des Patienten auf die Konsole aus
     */ 
    public void zeigePatient() {
        System.out.println(this.getName() + " (Kind, " + this.alter + " Jahre)");
    }
    
}
