/*
 * Klasse zur Modellierung eines Erwachsenen
 * 
 * Ist von Ihnen anzupassen
 */
class Erwachsener extends Patient {
    
    /**
     * Konstruktor für einen erwachsenen Patienten
     * @param name Name des Patienten
     * @param krankheitsklasse Die Krankheitsklasse (1, 2 oder 3) des Patienten
     */ 
    public Erwachsener(String name, int krankheitsklasse) {
        super(name, krankheitsklasse);
    }
    
    
    /**
     * Gibt den Namen des Patienten auf die Konsole aus
     */ 
    public void zeigePatient() {
        System.out.println(this.getName() + " (Erwachsener)");
    }
}
