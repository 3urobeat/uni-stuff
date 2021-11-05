import java.lang.*;
import java.io.*;
import java.util.*;

public class Textanalyse {
    public static boolean istPalindrom(String input) {
        StringBuilder str = new StringBuilder();
        str.append(input);
        
        return input.toLowerCase().equals(str.reverse().toString().toLowerCase());
    }
}