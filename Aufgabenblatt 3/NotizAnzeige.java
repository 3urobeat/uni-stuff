
// *** Fuegen Sie hier Ihre Loesung ein:
public class NotizAnzeige {
    private String[] feld = new String[10];
    private int feldMenge = 0;
    
    public void speichereNotiz(String notiz) {
        if (feldMenge < 10) {
            feld[feldMenge] = notiz;
            feldMenge++;
        }
    }
    
    public void zeigeNotizen() {
        for (int i = 0; i < feldMenge; i++) {
            System.out.println(feld[i]);   
        }
    }
    
    public void loescheAlle() {
        feld = new String[10]; //create new empty array and overwrite old one
        feldMenge = 0;
    }
    
    
    public int getAnzahl() {
        return feldMenge;
    }
    
    public int getLaenge(int i) {
        return feld[i].length();
    }
    
    public boolean istSortiert(int i) {
        return feld[i].length() < feld[i + 1].length();
    }
    
    public boolean alleSortiert() {
        for (int i = 0; i < (getAnzahl() - 1); i++) { //iterate over all elements and stop if one element is false
            if (!istSortiert(i)) return false;
        }
        
        return true; //...if loop successfully finished, return true
    }
}

