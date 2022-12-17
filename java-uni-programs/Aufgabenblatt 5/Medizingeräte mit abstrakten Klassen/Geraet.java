// Erstellen Sie hier die Klasse Geraet
public abstract class Geraet{
    public boolean istEingeschaltet;
    private String hersteller;
    
    public Geraet(String her) {
        this.hersteller = her;
    }
    
    public String gibHersteller() {
        return this.hersteller;
    }
    
    abstract void drueckePowerKnopf();
}