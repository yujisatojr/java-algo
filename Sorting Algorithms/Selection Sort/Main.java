import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private final Employee[] roster;
    private int steps;
    public int n;

    public Main() {
        var s = new Scanner(System.in);

        //use the first line of input to initialize my array
        n = s.nextInt();
        roster = new Employee[n];
        s.nextLine();   //dirty hack: skip past the newline to put us at the first line of data

        //loop through all the input and instantiate employee objects for each line
        for (int i=0; i<n; i++) {
            roster[i] = new Employee(s.nextLine());
        }

        //execute the selection sort here.
        selectionSort();

        //now, write the sorted array to a file.
        final String filename = "sorted.txt";
        try (FileWriter fw = new FileWriter(filename)) {
            //write the "roster" section of the file
            fw.write(n + "\n");
            for (int i=0; i<n; ++i) {
                fw.write(roster[i].toString());
                fw.write("\n");
            }
            // System.out.println("Output written to " + filename);
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

    private void selectionSort() {
        // tmp used for swapping values
        Employee tmp;
        // FOR i = firstIndex TO secondtoLastIndex:
        for (int i=0; i<n-1; i++) {
            // minIndex = i
            int minIndex = i;
            // FOR j = i+1 TO lastIndex:
            for (int j=i+1; j<n;j++)  {
                //  IF A[j] < A[minIndex] THEN
                if (roster[j].id < roster[minIndex].id) {
                    minIndex = j;
                }
                //increment the count
                steps += 1;
            }
            // EXCHANGE A[minIndex] AND A[i]
            tmp = roster[minIndex];
            roster[minIndex] = roster[i];
            roster[i] = tmp;
            // print out array of IDs.
            // printIDs();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
