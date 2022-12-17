import java.util.Scanner;

class SummeBisN {
    public static void main(String[] args) {
        // Variablen deklarieren
        int n = 0;
        int summe = 0;

        // Eingabe einlesen
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        // *** Fuegen Sie hier Ihre Loesung ein:
        for (int i = 0; i <= n; i++) {
            summe += n;
        }

        System.out.println(summe / 2);
    }
}
