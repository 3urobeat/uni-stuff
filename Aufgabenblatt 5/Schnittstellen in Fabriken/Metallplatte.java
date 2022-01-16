/**
 * Eine Metallplatte ist ein Werkstueck als Metall, dass eine Form hat.
 */
public class Metallplatte implements Produkt {
	private double breite;
	private double laenge;
	private double hoehe;

	/**
	 * Erstellt eine neue Metallplatte mit bestimmten Ausmaßen.
	 * @param breite Die Breite der neuen Platte.
	 * @param laenge Die Laenge der neuen Platte.
	 * @param hoehe Die Hoehe der neuen Platte.
	 */
	public Metallplatte(double breite, double laenge, double hoehe){
		this.breite = breite;
		this.laenge = laenge;
		this.hoehe = hoehe;
	}

	/**
	 * Ermittelt die Breite dieser Metallplatte.
	 * @return Die Breite der Platte.
	 */
	public double getBreite() {
		return breite;
	}

	/**
	 * Legt die Breite dieser Metallplatte neu fest.
	 * @param breite Die neue Breite der Platte.
	 */
	public void setBreite(double breite) {
		this.breite = breite;
	}

	/**
	 * Ermittelt die Laenge dieser Metallplatte.
	 * @return Die Laenge der Platte.
	 */
	public double getLaenge() {
		return laenge;
	}

	/**
	 * Legt die Laenge dieser Metallplatte neu fest.
	 * @param laenge Die neue Laenge der Platte.
	 */
	public void setLaenge(double laenge) {
		this.laenge = laenge;
	}

	/**
	 * Ermittelt die Hoehe dieser Metallplatte.
	 * @return Die Hoehe der Platte.
	 */
	public double getHoehe() {
		return hoehe;
	}

	/**
	 * Legt die Hoehe dieser Metallplatte neu fest.
	 * @param hoehe Die neue Hoehe der Platte.
	 */
	public void setHoehe(double hoehe) {
		this.hoehe = hoehe;
	}

	/**
	 * Druecke auf diese Metallplatte, so dass sie ihre Form veraendert. Sie
	 * wird um den Pressfaktor flacher, und dehnt sich dafuer in Breite und Laenge
	 * aus.
	 * @param pressfaktor Der Faktor, um den diese Metallplatte gedrueckt wird.
	 */
	public void druecke(double pressfaktor) {
		double sqrtPressfaktor = Math.sqrt(pressfaktor);
		hoehe *= 1.0 / pressfaktor;
		breite *= sqrtPressfaktor;
		laenge *= sqrtPressfaktor;
	}

	/**
	 * Zeigt Informationen über diese Metallplatte auf der Standardausgabe an.
	 */
	public void print() {
        System.out.format("Metallplatte: %.2fx%.2fx%.2f\n", laenge, breite, hoehe);
	}
}
