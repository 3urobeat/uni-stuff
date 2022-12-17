public class FizzBuzz {
    public static void main(String[] args) {
        // Fuegen Sie hier Ihre Loesung ein:
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.print("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.print("Fizz");
            } else if (i % 5 == 0) {
                System.out.print("Buzz");
            } else {
                System.out.print(i);
            }
            
            if (i < 100) System.out.print(",");
                else System.out.print("\n");
        }
    }
}