public class Main {

    public static void main(String[] args) {
        // Einen "generischen" Hund namens Bello erstellen
        Hund bello = new Hund("Bello");
        
        // Einen Husky namens Wotan erstellen
        Husky wotan = new Husky("Wotan");
        
        // Einen Schattenwolf Nymeria erstellen
        Schattenwolf nymeria = new Schattenwolf("Nymeria");

        // Hunde bellen lassen
        System.out.print("Bello: ");
        bello.bellen();
        
        System.out.print("Wotan: ");
        wotan.bellen();
        
        System.out.print("Nymeria: ");
        nymeria.bellen();
    }
}
