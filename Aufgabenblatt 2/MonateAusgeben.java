import java.util.Scanner;

class MonateAusgeben {
    public static void main(String[] args) {
        // Lese eine Nummer eines Monats ein.
        Scanner scanner = new Scanner(System.in);
        int nummerMonat = scanner.nextInt();

        // *** Fuegen Sie hier Ihre Loesung ein:
        String[] monate = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
        
        if (nummerMonat > monate.length || nummerMonat < 1) System.out.println(nummerMonat + " Kein gültiger Monat.");
            else System.out.println(nummerMonat + " " + monate[nummerMonat - 1]);
    }
}
