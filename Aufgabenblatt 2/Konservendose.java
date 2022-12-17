import java.util.Scanner;

class Konservendose {
    public static double power(double b, double x) {
        return Math.pow(b,x);        
    }
    
    public static void main(String[] args) {
        // Variablen deklarieren
        double durchmesser = 0.0;
        double hoehe = 0.0;

        double volumen = 0.0;
        double mantelflaeche = 0.0;
        double oberflaeche = 0.0;

        // Eingaben einlesen
        Scanner scanner = new Scanner(System.in);
        durchmesser = scanner.nextInt();
        hoehe = scanner.nextInt();

        // *** Fuegen Sie hier Ihre Loesung ein:
        volumen = power((durchmesser / 2), 2) * Math.PI * hoehe;
        mantelflaeche = 2 * Math.PI * (durchmesser / 2) * hoehe;
        oberflaeche = 2 * (Math.PI * power((durchmesser / 2), 2)) + mantelflaeche;

        // Ergebnisse ausgeben
        System.out.println(volumen);
        System.out.println(mantelflaeche);
        System.out.println(oberflaeche);
    }
}
