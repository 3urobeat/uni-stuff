// Erstellen Sie hier die Klasse Sensor
public class Sensor extends Medizingeraet {
    private int messwert;

    public Sensor(String her, int Alarm) {
        super(her, Alarm);
    }
    
    private void messen() {
        messwert = rand.nextInt(100);
    }

    public void ueberpruefeAlarm() {
        String stBuffer;
        
        if (istEingeschaltet) {
            this.messen();
            stBuffer = messwert <= this.alarmSchwellwert ? "Messwert ist in Ordnung." : "Alarm!";
        } else {
            stBuffer = "Geraet ist ausgeschaltet!";
        }
        
        System.out.println(stBuffer);
    }
}