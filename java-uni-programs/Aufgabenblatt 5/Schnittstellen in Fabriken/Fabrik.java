import java.util.ArrayList;
import java.util.Scanner;

/**
 * Eine Fabrik walzt Produkte in verschiedenen Schritten platt, und bewegt sie
 * dazu ueber Foerderbaender.
 */
public class Fabrik {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ArrayList<Foerderband> baender = new ArrayList<Foerderband>();
		ArrayList<Presse> pressen = new ArrayList<Presse>();

		int baenderAnzahl = scanner.nextInt();
		int pressenAnzahl = scanner.nextInt();
		int verbindungen = scanner.nextInt();

		// Lege die Foerderbaender der Fabrik an.
		for(int i = 0; i < baenderAnzahl; i++) {
			baender.add(new Foerderband());
		}

		// Lege die Pressen der Fabrik an.
		for(int i = 0; i < pressenAnzahl; i++) {
			double druck = scanner.nextDouble();
			int zielBand = scanner.nextInt();
			pressen.add(new Presse(druck, baender.get(zielBand)));
		}

		// Verbinde Foerderbaender mit Pressen.
		for (int i = 0; i < verbindungen; i++) {
			int band = scanner.nextInt();
			int ziel = scanner.nextInt();
			baender.get(band).setZiel(pressen.get(ziel));
		}

		assert(baender.size() >= 2);

		// Das erste Foerderband ist immer an Stelle 0.
		int objektzahl = scanner.nextInt();
		for (int i = 0; i < objektzahl; i++) {
			String typ = scanner.next();
			if (typ.equals("Platte")) {
				double hoehe = scanner.nextDouble();
				double laenge = scanner.nextDouble();
				double breite = scanner.nextDouble();
				Metallplatte platte = new Metallplatte(hoehe, laenge, breite);
				baender.get(0).verarbeite(platte);
			}
			else if (typ.equals("Gummiwuerfel")) {
				double seitenlaenge = scanner.nextDouble();
				Gummiwuerfel wuerfel = new Gummiwuerfel(seitenlaenge);
				baender.get(0).verarbeite(wuerfel);
			}
			else {
				System.out.println("Unbekannter Produkttyp " + typ + "!");
				return;
			}
		}

		// Bewege die Platten durch die Fabrik.
		boolean weiterbewegen = true;
		while(weiterbewegen) {
			weiterbewegen = false;
			for (Foerderband band : baender) {
				weiterbewegen |= band.getZiel() != null && band.hatLadung();
			}

			for (Foerderband band : baender) {
				band.bewegeProdukte();
			}
			
			// Das Ausgabefoerderband ist immer an Stelle 1.
			if (baender.get(1).hatLadung()) {
				ArrayList<Produkt> produkte = baender.get(1).nehmeLadung();
				for(Produkt produkt : produkte) {
					System.out.print("Produkt: ");
					produkt.print();
				}
			}
		}
	}
}
