public class Campusweg {
     
    public static double berechneLaenge(Punkt p1, Punkt p2, Punkt p3, Punkt p4) {
        return p1.streckeBis(p2) + p2.streckeBis(p3) + p3.streckeBis(p4);
    }
     
}