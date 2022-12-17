import java.util.Scanner;

/**
 * Assistenzklasse, die das Einlesen von ganzen Saetzen vereinfacht.
 */
public class Satzleser {

    public static void main(String args[]) {
        Satzleser sL = new Satzleser();
        sL.leseSatz();
    }

    /**
     * Liest einen Satz von der Standardeingabe ein. Der Satz wird mit
     * einem Punkt '.' beendet, allerdings koennen noch weitere Zeichen
     * hinter dem Punkt existieren.
     * @returns Der eingelesene Satz.
     */
    public String leseSatz() {
        String satz = new Scanner(System.in).nextLine();

        System.out.println(satz.split("\\.")[0].trim().replaceAll(" +", " ").split(" ").length); //try-and-add-stuff-until-it-works simulator

        return satz;
    }
}
