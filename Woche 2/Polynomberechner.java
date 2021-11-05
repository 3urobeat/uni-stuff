import java.util.Scanner;
import java.lang.Math;

public class Polynomberechner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Lese 4 Werte ein: a, b, c des Polynoms und die x-Koordinate
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int x = scanner.nextInt();
        
        int ergebnis = 0;
        
        // *** Fuegen Sie hier Ihre Loesung ein:
        ergebnis = (a * x * x) + (b * x) + c;
        
        // Gib das ermittelte Ergebnis aus.
        System.out.println(ergebnis);
    }
}