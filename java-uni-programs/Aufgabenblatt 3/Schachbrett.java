import java.util.Scanner;

public class Schachbrett {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        ausgeben(n, erstellen(n));
    }
    
    public static String[][] erstellen(int n) {
        String[][] feld = new String[n][n];
        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                //check if int is even or not and either put ## or -- into the field
                if ((x+y) % 2 == 0) feld[x][y] = "##";
                    else feld[x][y] = "--";
            }
        }
        
        return feld;
    }
    
    public static void ausgeben(int n, String[][] feld) {
        for (int x = 0; x < n; x++) {
            StringBuilder buffer = new StringBuilder(); //buffer for this row
            
            //iterate over every column in this row
            for (int y = 0; y < n; y++) {
                buffer.append(feld[x][y]);
            }
            
            System.out.println(buffer); //output row
        }
    }
}