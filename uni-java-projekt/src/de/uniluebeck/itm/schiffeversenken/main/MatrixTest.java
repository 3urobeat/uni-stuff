package de.uniluebeck.itm.schiffeversenken.main;

/**
 * Exercise about 2D Arrays
 * @author T. Goritz, L. Janßen
 *
 */
public class MatrixTest {
    int rows = 10;
    int cols = 10;

    int[][] field = new int[cols][rows];

    /**
     * Initializes a Matrix
     * @param args default parameter
     */
    public static void main(String[] args) {
        MatrixTest mt = new MatrixTest();

        mt.initMatrix();
        mt.outMatrix();
        mt.updateMainDiagonal(50);
        mt.calcColSum();
        mt.setRowZeroToZero();
    }

    /**
     * Initializes a 2D array with the dimensions of rows and cols
     */
    public void initMatrix() {
        //Iterate over each column in each row and assign the value to that field
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                field[x][y] = y + x * 10;
            }
        }
    }

    /**
     * Convert matrix and output it
     */
    public void outMatrix() {
        for (int x = 0; x < rows; x++) { //iterate over each row
            StringBuilder buffer = new StringBuilder(); //create a buffer string to make formatting easier

            for (int y = 0; y < cols; y++) { //iterate over each column in this row
                int thisField = field[y][x]; //helper variable for less clutter

                //Append this field to the buffer string but prepend a 0 if < 10 to make output uniform
                if (thisField < 10) buffer.append("0").append(thisField).append(" "); //Apparently IntelliJ likes this more because it uses less memory
                    else buffer.append(thisField).append(" ");
            }

            System.out.println(buffer); //output this row
        }
    }


    /**
     * Replaces all values on the main diagonal with a provided value
     * @param newValue The value to replace with
     */
    public void updateMainDiagonal(int newValue) {
        for (int x = 0; x < rows; x++) {
            field[x][x] = newValue;
        }
    }


    /**
     * Calculates the sum of all rows and prints it to the terminal
     */
    public void calcColSum() {
        for (int y = 0; y < cols; y++) {
            int buffer = 0; //the sum of this column

            for (int x = 0; x < rows; x++) {
                buffer += field[x][y];
            }

            System.out.println(buffer);
        }
    }


    /**
     * Set all values of row 0 to the value 0
     */
    public void setRowZeroToZero() {
        for (int x = 0; x < rows; x++) {
            field[x][0] = 0;
        }
    }
}
