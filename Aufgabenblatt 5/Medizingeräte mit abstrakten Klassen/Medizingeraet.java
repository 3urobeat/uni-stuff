import java.util.Random;

/**
 * Abstrakte Klasse zur Modellierung eines Medizingeraetes
 *
 * @author Dennis Labitzke
 */
public abstract class Medizingeraet extends Geraet {

    /** Schwellwert fuer das Ausloesen eines Alarms. */
    int alarmSchwellwert;
    /** Instanz einer Klasse zum Erzeugen von Zufallszahlen. */
    Random rand = new Random();

    /**
     * Konstruktor der Klasse.
     *
     * @param hersteller Der Hersteller des modellierten Geraetes.
     * @param alarmSchwellwert Der Wert, welcher, wenn ueberstiegen, einen Alarm ausloest.
     */
    Medizingeraet(String hersteller, int alarmSchwellwert) {
        super(hersteller);
        istEingeschaltet = false;
        this.alarmSchwellwert = alarmSchwellwert;
    }

    /**
     * Methode, welche das modellierte Geraet an- bzw. ausschaltet.
     */
    @Override
    public void drueckePowerKnopf() {
        if (istEingeschaltet) {
            System.out.println("Schalte Geraet aus.");
        }
        else {
            System.out.println("Schalte Geraet ein.");
        }
        istEingeschaltet = !istEingeschaltet;
    }

    /**
     * Ueberpruefen, ob ein Alarm ausgeloest werden muss.
     */
    public abstract void ueberpruefeAlarm();
}
