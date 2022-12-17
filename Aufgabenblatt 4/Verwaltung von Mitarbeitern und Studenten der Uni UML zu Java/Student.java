public class Student extends Person implements Druckbar { //Hier jetzt das tolle Druckbar Interface implementieren (https://www.w3schools.com/java/java_interface.asp)
    
    private static int naechsteMatrNr; 
    
    private int matrNr = 200200; //Standardwerte aus 
    private int semester;
    
    
    public Student(String name, String vorname, int semester) {
        super(name, vorname);
        
        this.semester       = semester;
        this.matrNr         = naechsteMatrNr;
        this.naechsteMatrNr = naechsteMatrNr + 1;
    }
    
    public static int getNaechsteMatrNr() {
        return naechsteMatrNr;
    }
    
    public int getSemester() {
        return semester;
    }
    
    public void setSemester(int semester) {
        this.semester = semester;
    }
    
    //Student soll Urlaub haben wenn Semester > 6
    public boolean hatUrlaub() {
        if (semester > 6) return true;
            else return false;
    }
    
    
    public void drucken() {
        System.out.println(this.vorname + " " + this.getName() + ", E-Mail: " + this.email + ", Matrikelnummer: " + this.matrNr + ", Semester: " + this.semester);
    }
}