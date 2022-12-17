import java.util.ArrayList;

public class LogMessage {
    private int level;
    private String message;
    
    private static ArrayList<String> fullLog = new ArrayList<String>();
    private static int nextNum = 0;
    
    private String format(int num) {
        return num + "-" + level + " " + message;
    }
    
    public static void showAll() {
        for (String message : fullLog) {
            System.out.println(message);
        }
    }
    
    // *** Fuegen Sie hier die fehlenden Konstruktor-Deklarationen ein:
    private void levelZeroHandler() { //Task says we should add a curly line if level is 0
        if (this.level == 0) {
            fullLog.add(this.nextNum + "-" + this.level + " ~~~~~~~~~~");
        }
    }
    
    
    //Multiple constructors for different parameters
    public LogMessage(int level, String message) {
        this.level   = level;
        this.message = message;
        
        fullLog.add(format(nextNum));
        
        levelZeroHandler();
        
        nextNum++;
    }
    
    public LogMessage(int level, String message, boolean increase) {
        this.level    = level;
        this.message  = message;
        
        fullLog.add(format(nextNum));
        
        levelZeroHandler();
       
        if (increase) nextNum++;
    }
    
    public LogMessage(String message) {
        this.level   = 3;
        this.message = message;
        
        fullLog.add(format(nextNum));
        
        levelZeroHandler();
        
        nextNum++;
    }
    
    
    public LogMessage(int level, int value) {
        this.level = level;
        this.message = "value is: " + Integer.toString(value);
        
        fullLog.add(format(nextNum));
        
        levelZeroHandler();
        
        nextNum++;
    }
    
    public LogMessage(boolean increase) {
        fullLog.add("----------");
        
        //levelZeroHandler(); //calling here aswell results in an unwanted line according to the tests so let's just leave it out :shrug:
        
        if (increase) nextNum++;
    }
    
}