/**
 * Eine Presse kann Objekte in ihrer Form veraendern, indem sie ganz feste
 * auf das Objekt draufdrueckt.
 */
public class Presse implements Maschine {
	private final double pressfaktor;
	private Maschine ausgabe;

	/**
	 * Erstellt eine neue Presse mit einem Druckfaktor, und einer Maschine, an
	 * die die Presse die fertig gedrueckten Produkte ausgibt.
	 * @param pressfaktor Der Faktor, mit dem diese Presse drueckt.
	 * @param ausgabe Die Zielmaschine, die fertige Produkte erhaelt.
	 */
	public Presse(double pressfaktor, Maschine ausgabe) {
		this.pressfaktor = pressfaktor;
		this.ausgabe = ausgabe;
	}

	/**
	 * Gibt die Ausgabemaschine dieser Presse zurueck.
	 * @return Die Ausgabemaschine dieser Presse.
	 */
	public Maschine getAusgabe() {
		return ausgabe;
	}

	/**
	 * Legt die Ausgabemaschine dieser Presse neu fest.
	 * @param ausgabe Die neue Ausgabemaschine.
	 */
	public void setAusgabe(Maschine ausgabe) {
		this.ausgabe = ausgabe;
	}

	/**
	 * Verarbeitet ein Produkt in dieser Presse, und gibt es danach an die
	 * Ausgabemaschine dieser Presse weiter.
	 * @param objekt Das zu bearbeitende Produkt.
	 */
	public void verarbeite(Produkt objekt) {
		objekt.druecke(pressfaktor);

		if (ausgabe != null) {
			ausgabe.verarbeite(objekt);
		}
	}
}
