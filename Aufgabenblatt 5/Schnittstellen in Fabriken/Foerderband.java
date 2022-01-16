import java.util.ArrayList;

/**
 * Ein Foerderband bewegt ein Produkt zwischen zwei Maschinen hin- und her.
 */
public class Foerderband implements Maschine{
	private Maschine ziel;
	private ArrayList<Produkt> produkte = new ArrayList<Produkt>();

	/**
	 * Erstellt ein neues Foerderband, dass noch an keine Zielmaschine
	 * angeschlossen ist.
	 */
	public Foerderband() {
	}

	/**
	 * Erstellt ein neues Foerderband, dass bei Befoerderung seine Produkte an
	 * eine Zielmaschine uebergibt.
	 * @param ziel Die Zielmaschine dieses Foerderbands.
	 */
	public Foerderband(Maschine ziel)	{
		this.ziel = ziel;
	}

	/**
	 * Legt die Zielmaschine dieses Foerderbands neu fest.
	 * @param ziel Die neue Zielmaschine.
	 */
	public void setZiel(Maschine ziel) {
		this.ziel = ziel;
	}

	/**
	 * Gibt die Zielmaschine dieses Foerderbands zurueck.
	 * @return Die Zielmaschine dieses Foerderbands.
	 */
	public Maschine getZiel() {
		return ziel;
	}

	/**
	 * Bewege die Produkte auf diesem Foerderband vorwaerts.
	 * Dabei werden alle Produkte auf diesem Band an die Zielmaschine uebergeben,
	 * die diese dann weiterverarbeiten kann. Ist dieses Band noch nicht
	 * angeschlossen, wird nichts getan.
	 */
	public void bewegeProdukte() {
		if (ziel == null) {
			return;
		}

		while(!produkte.isEmpty()) {
			Produkt produkt = produkte.remove(0);
			ziel.verarbeite(produkt);
		}
	}

	/**
	 * Prueft, ob auf diesem Foerderband noch Produkte liegen.
	 * @return true, wenn dieses Foerderband noch Produkte haelt, sonst false.
	 */
	public boolean hatLadung() {
		return !produkte.isEmpty();
	}

	/**
	 * Entnimmt die Ladung manuell von diesem Foerderband. Es ist danach leer.
	 * @return Die bisher auf diesem Foerderband vorhandenen Produkte.
	 */
	public ArrayList<Produkt> nehmeLadung() {
		ArrayList<Produkt> ausladung = this.produkte;
		this.produkte = new ArrayList<Produkt>();
		return ausladung;
	}

	/**
	 * @brief Legt Produkte auf dieses Foerderband.
	 *
	 * Wenn dieses Foerderband Produkte von einer anderen Maschine erhaelt,
	 * werden sie hiermit erstmal nur auf das Band gelegt. Erst mit
	 * `bewegeProdukte` wird der Inhalt dieses Bands weiterbewegt.
	 *
	 * @param produkt Das Produkt, dass auf dieses Band gelegt werden soll.
	 */
	public void verarbeite(Produkt produkt) {
		produkte.add(produkt);
	}
}
