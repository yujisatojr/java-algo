import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Main {

    private final Employee[] roster;
    public int n;
    public int m;

    public Main() {
        var s = new Scanner(System.in);

        // size of the hashtable
        m = s.nextInt();
        // number of incoming objects
        n = s.nextInt();
        // instantiate an Employee object here
        roster = new Employee[n];
        // dirty hack: skip past the newline to put us at the first line of data
        s.nextLine();
        // loop through all the input and instantiate employee objects for each line
        for (int i=0; i<n; i++) {
            roster[i] = new Employee(s.nextLine());
        }

        /* execute each method here */
        //partOne();
        //partTwo();
        partThree();
    }

    private void partOne() {
        // this is the second array
        Employee[] array = new Employee[m];
        int collusion = 0;
        for (int j=0; j<n; j++) {
            // check to see if object is already stored at the index obtained by division method
            if (array[roster[j].getID()%m] == null) {
                // put the object into the aray
                array[roster[j].getID()%m] = roster[j];
                System.out.println("Attempting to hash " + roster[j].name + " at index " + Integer.toString(roster[j].getID()%m) + "... SUCCESS!");
            }
            // if there is already an object at the index..
            else {
                //increment the count
                collusion += 1;
                System.out.println("Attempting to hash " + roster[j].name + " at index " + Integer.toString(roster[j].getID()%m) + "...OUCH! Collision with " + (array[roster[j].getID()%m]).name);
            }
        }
        System.out.println("Total collisions: " + collusion);
    }

    private void partTwo() {
        Employee[] array = new Employee[m];
        int collusion = 0;
        for (int j=0; j<n; j++) {
            // use multiplication method here
            double a = (sqrt(5)-1)/2;
            double k = m * (roster[j].getID()*a%1);

            // check to see if object is already stored at the index obtained by division method
            if (array[(int) k] == null) {
                // put the object into the aray
                array[(int) k] = roster[j];
                System.out.println("Attempting to hash " + roster[j].name + " at index " + Integer.toString((int) k) + "... SUCCESS!");
            }
            // if there is already an object at the index..
            else {
                //increment the count
                collusion += 1;
                System.out.println("Attempting to hash " + roster[j].name + " at index " + Integer.toString((int) k) + "...OUCH! Collision with " + (array[(int) k]).name);
            }
        }
        System.out.println("Total collisions: " + collusion);
    }

    private void partThree() {
        // this is the second array
        Employee[] array = new Employee[m];
        // this array holds the number of count
        int[] count = new int[m];
        // this is for total collusion count
        int collusion = 0;
        for (int i = 0; i < n; i++) {
            count[i] = 0;
        }
        for (int j = 0; j < n; j++) {
            // check to see if there is already an object at the index
            if (array[roster[j].getID() % m] == null) {
                array[roster[j].getID() % m] = roster[j];
                System.out.println("Adding " + roster[j].name + " to table at index " + Integer.toString(roster[j].getID() % m) + " (0 collisions)");
            }
            // if there is collusion at the index..
            else {
                //increment the count
                count[roster[j].getID() % m] += 1;
                collusion += 1;
                System.out.println("Adding " + roster[j].name + " to table at index " + Integer.toString(roster[j].getID() % m) + " (" + Integer.toString(count[roster[j].getID() % m]) + "collisions)");
            }
        }
        System.out.println("Total collisions to resolve: " + collusion);
    }

    public static void main(String[] args) { new Main();
    }
}
