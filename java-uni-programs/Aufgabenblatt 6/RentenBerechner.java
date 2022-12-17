import java.util.Scanner;

public class RentenBerechner {
    
    private static double sparbetrag;
    private static double dauer;
    private static double zins;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        sparbetrag = scanner.nextDouble();
        dauer      = scanner.nextDouble();
        zins       = scanner.nextDouble();
        
        System.out.println(berechnen());
    }
    
    
    public static double berechnen() {
        double betrag = 0;
        
        for (int i = 0; i < dauer; i++) {
            betrag += sparbetrag;

            betrag += betrag * (zins / 100);
        }
        
        return Math.round(betrag * 100.0) / 100.0;
    }
    
}