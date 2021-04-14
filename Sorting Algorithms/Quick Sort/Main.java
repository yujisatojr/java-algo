import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
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

        //execute the quick sort here.
        //quickSort(0, n-1);
        //randomizedQuickSort(0, n-1);
        tailRecursiveQuickSort(0, n-1);

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

    // helper method for randomized quick sort
    private int random(int p, int r) {
        //instantiate imported random object here
        Random rand = new Random();
        //create a pivot
        int pivot = rand.nextInt(r-p)+p;
        return pivot;
    }

    //partition method for randomized quick sort
    private int randomizedPartition(int p, int r) {
        //this i is the pivot
        int i = random(p, r);
        //exchange objects
        Employee temp = roster[r];
        roster[r] = roster[i];
        roster[i] = temp;
        return partition(p, r);
    }

    //execute the actual randomized quick sort here
    private void randomizedQuickSort(int p, int r) {
        if (p < r) {
            //the same algorithm as the normal quick sort
            int q = randomizedPartition(p, r);
            randomizedQuickSort(p, q-1);
            randomizedQuickSort(q+1, r);
        }
    }

    //execute the tail recursive quick sort here
    private void tailRecursiveQuickSort(int p, int r) {
        //use while loop instread
        while (p < r) {
            // partition and sort left subarray
            int q = partition(p, r);
            tailRecursiveQuickSort(p, q-1);
            p = q + 1;
        }
    }

    // helper method for quick sort
    private int partition(int p, int r) {
        Employee x = roster[r];
        int i = p - 1;
        //from low to highest
        for (int j=p; j<=r-1; j++) {
            steps++;
            //check to see if the value in array is higher than or lower than pivot
            if (roster[j].getID() <= x.getID()) {
                i = i + 1;
                //exchange objects here
                Employee tmp = roster[i];
                roster[i] = roster[j];
                roster[j] = tmp;
                //print ID numbers
                //printIDs();
            }
        }
        //exchange the value again
        Employee exchange = roster[r];
        roster[r] = roster[i + 1];
        roster[i + 1] = exchange;
        //print ID numbers
        //printIDs();
        return i + 1;
    }

    // here is my quick sort function
    private void quickSort(int p, int r) {
        // continues until low become high
        if (p < r) {
            int q = partition(p, r);
            //recusive function
            quickSort(p, q-1);
            quickSort(q+1, r);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
