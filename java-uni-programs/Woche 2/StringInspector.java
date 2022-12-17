import java.util.Scanner;

public class StringInspector {
    public static void main(String[] args) {
        // Eingabe der Testfaelle einlesen
        Scanner s = new Scanner(System.in);
        
        String eingabeEins = s.nextLine();
        String eingabeZwei = s.nextLine();
        
        // Deklariere die Ergebnisvariablen.
        String schrittEins = "";
        String schrittZwei = "";
        String schrittDrei = "";
        String schrittVier = "";
        String schrittFuenf = "";
        String schrittSechs ="";
        
        // *** Fuegen Sie hier Ihre Loesung ein:
        schrittEins = "Eingaben: " + eingabeEins + ", " + eingabeZwei;
        
        
        int lengthEins = eingabeEins.length();
        int lengthZwei = eingabeZwei.length();
        int lengthBoth = lengthEins + lengthZwei;
        
        schrittZwei = "Längen: " + lengthEins + ", " + lengthZwei + ", Summe: " + lengthBoth;
        
        
        char einsEins = eingabeEins.charAt(0);
        char einsEnde = eingabeEins.charAt(eingabeEins.length() - 1);
        char zweiEins = eingabeZwei.charAt(0);
        char zweiEnde = eingabeZwei.charAt(eingabeZwei.length() - 1);
        
        schrittDrei = "Umpaare: " + einsEins + einsEnde + ", " + zweiEins + zweiEnde;
        
        
        String einsMittel = eingabeEins.substring(1, eingabeEins.length() - 1);
        
        schrittVier = "Mitte der ersten Eingabe: " + einsMittel;
        
        
        boolean einsContainsZwei = eingabeEins.contains(eingabeZwei);
        int einsZweiInEins = eingabeEins.indexOf(eingabeZwei.charAt(0));
        
        schrittFuenf = "Zusammenhang: " + einsContainsZwei + ", " + einsZweiInEins;
        
        
        schrittSechs = "\"Tom Goritz\"";


        // Gib das Ergebnis aus, damit der Testfall es untersuchen kann.        
        System.out.println(schrittEins);
        System.out.println(schrittZwei);
        System.out.println(schrittDrei);
        System.out.println(schrittVier);
        System.out.println(schrittFuenf);
        System.out.println(schrittSechs);
    }
}