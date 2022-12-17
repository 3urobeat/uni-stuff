import java.util.Scanner;

public class Weihnachtszeichner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Weihnachtswald wald = new Weihnachtswald();
        
        int anzahlBaeume = scanner.nextInt();
        
        for(int i = 0; i < anzahlBaeume; i++) {
            int hoehe = scanner.nextInt();
            if (hoehe < 3 || hoehe > 29) {
                continue;
            }
            
            wald.pflanze(hoehe);
        }
        
        wald.zeichne();
    }
}