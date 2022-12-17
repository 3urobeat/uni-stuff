import java.util.Scanner;

class Zinsen {
    public static void main(String[] args) {
        // Variablen deklarieren
        double guthaben = 0.0;
        double guthabenMitVerzinsung01 = 0.0;
        double guthabenMitVerzinsung05 = 0.0;
        double guthabenMitVerzinsung08 = 0.0;

        // Guthaben einlesen
        Scanner scanner = new Scanner(System.in);
        guthaben = scanner.nextInt();

        // *** Fuegen Sie hier Ihre Loesung ein:
        guthabenMitVerzinsung01 = guthaben * 1.001;
        guthabenMitVerzinsung05 = guthaben * 1.005;
        guthabenMitVerzinsung08 = guthaben * 1.008;

        // Ergebnisse ausgeben
        System.out.println(guthabenMitVerzinsung01);
        System.out.println(guthabenMitVerzinsung05);
        System.out.println(guthabenMitVerzinsung08);
    }
}
