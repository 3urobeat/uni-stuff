import java.util.*;

public class Weihnachtswald extends Weihnachtszeichner {
    
    ArrayList<Weihnachtsbaum> wald = new ArrayList<Weihnachtsbaum>();
    
    
    public void pflanze(int hoehe) {
        Weihnachtsbaum baum = new Weihnachtsbaum(hoehe);
        wald.add(baum);
    }
    
    public void zeichne() {
        for (Weihnachtsbaum baum: wald) {
            System.out.print(baum.baumStr);
        }
    }
}