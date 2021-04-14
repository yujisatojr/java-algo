import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private final Employee[] roster;
    private int steps;
    public int n;
    public int heapSize;

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

        //execute the heap sort here.
        heapSort();

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

    // helper method
    // binary tree is implemented as array
    private void maxHeapify(int i) {
        // RIGHT(i)
        int l = 2 * i + 1;
        // LEFT(i)
        int r = 2 * (i + 1);
        int largest;
        Employee tmp;
        // increment the count here
        steps += 1;
        // compare id and not object itself
        if (l <= heapSize && roster[l].id > roster[i].id) {
            largest = l;
        } else {
            largest = i;
        }
        // increment another count here
        steps += 1;
        if (r <= heapSize && roster[r].id > roster[largest].id) {
            largest = r;
        }
        if (largest != i) {
            tmp = roster[largest];
            roster[largest] = roster[i];
            roster[i] = tmp;
            // make recursive call here
            maxHeapify(largest);
        }
    }

    // helper method
    private void buildMaxHeap() {
        // index of last value in heap array (length-1)
        heapSize = roster.length-1;
        // we use 0-based array
        for (int i=roster.length/2; i>=0; i--) {
            maxHeapify(i);
        }
    }

    // here is my heap sort function
    private void heapSort() {
        // declare variables here
        printIDs();
        buildMaxHeap();
        Employee tmp;
        printIDs();
        // goes down to 1 and not 2
        for (int i=roster.length-1; i>=1; i--) {
            // exchange first value of array to A(i)
            tmp = roster[0];
            roster[0] = roster[i];
            roster[i] = tmp;
            heapSize = heapSize - 1;
            // pass on the first value instead of 1
            maxHeapify(0);
            printIDs();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
