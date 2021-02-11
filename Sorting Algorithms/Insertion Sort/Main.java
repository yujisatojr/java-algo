import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private final Employee[] roster;
    private int steps;

    public Main() {
        var s = new Scanner(System.in);

        //use the first line of input to initialize my array
        int n = s.nextInt();
        roster = new Employee[n];
        s.nextLine();   //dirty hack: skip past the newline to put us at the first line of data

        //loop through all the input and instantiate employee objects for each line
        for (int i=0; i<n; i++) {
            roster[i] = new Employee(s.nextLine());
        }

        //execute the insertion sort here.
        insertionSort();

        //now, write the sorted array to a file.
        final String filename = "sorted.txt";
        try (FileWriter fw = new FileWriter(filename)) {
            //write the "roster" section of the file
            fw.write(n + "\n");
            for (int i=0; i<n; ++i) {
                fw.write(roster[i].toString());
                fw.write("\n");
            }
            System.out.println("Output written to " + filename);
            System.out.println("It took " + steps + " comparisons to sort this list.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void printIDs() {
        for (var e : roster) {
            System.out.print(e.getID() + " ");
        }
        System.out.println();
    }

    private void insertionSort() {
        Employee key;
        int i;
        for (int j=1; j<roster.length; j++) {
            //declare the pulled out value
            key = roster[j];
            i = j - 1;
            //compare objects in the array here
            // we use 1-based array, thus change > to >=
            while (i >= 0 && roster[i].getID() >key.getID()) {
                // if the digit to the right is lower, swap it into the right place
                roster[i + 1] = roster[i];
                // since one of the value in the array is sorted, decrease number in the array (until it's done).
                i = i - 1;
                //increment the count
                steps += 1;
            }
            roster[i + 1] = key;
            //print out array of ids.
            //printIDs();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
