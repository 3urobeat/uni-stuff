public class Weihnachtsbaum {
    
    public String baumStr;
    
    
    public Weihnachtsbaum(int hoehe) {
        this.baumStr = getKrone(hoehe) + getStumpf(hoehe);
    }
    
    
    public String getKrone(int hoehe) {
        String baum = "";

        for (int i = 0; i < hoehe; i++) { //jede Zeile
            for (int j = 1; j < hoehe - i; j++) { //Hoehe ist Breite / 2, da wir nur von links punktieren müssen, passts
                baum += ".";
            }
            
            for (int k = 0; k <= i * 2; k++) {
                baum += "#";
            }
            
            baum += "\n";
        }

        return baum;
    }


    public String getStumpf(int hoehe) {
        String stumpf = "";

        for (int i = 0; i < 2 * (hoehe / 4) + 1; i++) {
            for (int j = 0; j < hoehe - ((2 * (hoehe / 4) + 1) / 2) - 1; j++) {
                stumpf += ".";
            }

            for (int k = 0; k < 2 * (hoehe / 4) + 1; k++) {
                stumpf += "#";
            }

            stumpf += "\n";
        }

        return stumpf;
    }
    
}