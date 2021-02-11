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

        //execute the merge sort here.
        mergeSort(0, n-1);

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

    private void merge(int p, int q, int r) {
        // declare variables for nested-loops
        int i;
        int j;

        // should be declared locally
        int n1 = q - p + 1;
        int n2 = r - q;
        // L and R var type should match the array
        Employee[] L = new Employee[n1+1];
        Employee[] R = new Employee[n2+1];
        // for loop start with 0 because we use 0-based arrays
        for (i=0; i<n1; i++) {
            L[i] = roster[p+i];
        }
        for (j=0; j<n2; j++) {
            R[j] = roster[q+j+1];
        }
        // no need to use n1+1/n2+1 because we use 0-based arrays
        // ∞ is expressed as the largest possible int
        L[n1] = new Employee();
        R[n2] = new Employee();
        // initialize i and j
        i = 0;
        j = 0;
        // we will execute comparison here
        for (int k=p; k<=r; k++) {
            // compare values using ids
            if (L[i].id <= R[j].id) {
                // increment the count
                steps += 1;
                roster[k] = L[i];
                i = i + 1;
            } else {
                // increment the count
                steps += 1;
                roster[k] = R[j];
                j = j + 1;
            }
        }
    }

    // here is my merge sort function
    // p is the first value in the array; r is the last value
    private void mergeSort(int p, int r) {
        if (p < r) {
            // q is the mid-point of p and r
            int q = (p + r) / 2;
            mergeSort(p, q);
            mergeSort(q+1, r);
            // then combine all the split values
            merge(p, q, r);
            // print out array of IDs.
            // printIDs();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
