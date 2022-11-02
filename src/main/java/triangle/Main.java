package triangle;

/*
 Compile with:
 mvn compile
 mvn exec:java -Dexec.mainClass="triangle.Main"
 */

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        /* ------------------ U2 - Task 3 ------------------ */

        // Get three ints from stdin using a Scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("Submit 3 separate ints, representing the 3 sides of our triangle:");

        System.out.print("x: ");
        int x = scanner.nextInt();

        System.out.print("y: ");
        int y = scanner.nextInt();

        System.out.print("z: ");
        int z = scanner.nextInt();

        // Check if triangle is equilateral, isosceles or scalene
        String state = "";

        if (x == y && y == z) { // equilateral, all sides are equally long
            state = "gleichseitig & gleichschenklig";
        } else if (x == y || y == z || x == z) { // isosceles, 2 sides are equally long
            state = "gleichschenklig";
        } else { // scalene, nothing matches each other
            state = "ungleichseitig";
        }

        // Output result
        System.out.println("Das angegebene Dreieck ist " + state + ".");

    }
}