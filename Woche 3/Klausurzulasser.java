import java.util.Scanner;

public class Klausurzulasser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lies die Punkte fuer alle 7 Zettel ein
        int zettel1 = scanner.nextInt();
        int zettel2 = scanner.nextInt();
        int zettel3 = scanner.nextInt();
        int zettel4 = scanner.nextInt();
        int zettel5 = scanner.nextInt();
        int zettel6 = scanner.nextInt();
        int zettel7 = scanner.nextInt();
        
        // Lies dann noch die Strings fuer Vorjahreszulassung und
        // Klausuranmeldung ein.
        String zulassungAusVorjahr = scanner.next();
        String klausuranmeldungErfolgt = scanner.next();
        
        // Fuegen Sie hier Ihre Loesung ein:
        int maxPunkte = 50 + 50 + 50 + 50 + 50 + 50 + 50;
        int erreichtePunkte = zettel1 + zettel2 + zettel3 + zettel4 + zettel5 + zettel6 + zettel7;
        
        if ((erreichtePunkte >= (maxPunkte / 2) && (zulassungAusVorjahr.equals("ja") || klausuranmeldungErfolgt.equals("ja"))) || (zulassungAusVorjahr.equals("ja") && klausuranmeldungErfolgt.equals("ja"))) {
            System.out.println("zugelassen");
        } else {
            System.out.println("nicht zugelassen");
        }
    }
}