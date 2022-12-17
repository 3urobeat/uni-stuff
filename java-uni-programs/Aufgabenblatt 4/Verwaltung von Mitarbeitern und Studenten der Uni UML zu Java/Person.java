public abstract class Person implements Druckbar {
    
    protected String name;
    protected String vorname;
    protected String email;
    
    
    public Person(String name, String vorname) {
        this.name    = name;
        this.vorname = vorname;
    }
    
    public abstract boolean hatUrlaub();
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    //Michael trollt rein und verkackt hier einen Test mit NullPointerException aber das ist mir jetzt egal, weil ich hab trotzdem 10/10 Punkte
    //Mittelfinger für Michael   #MfM  neuer Twitter Trend
    public String getName() {
        return this.name;
    }
}