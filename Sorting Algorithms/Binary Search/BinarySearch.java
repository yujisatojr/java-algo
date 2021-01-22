import java.util.Scanner;
import static java.util.Arrays.sort;

public class BinarySearch {

    public static void main(String[] args) {
        // read data from standard input
        Scanner s = new Scanner(System.in);

        // n represents number of rows of data the program will read
        int n = s.nextInt();
        s.nextLine();

        // declare variables and instantiate Employee class here
        String line;
        String[] employee;
        Employee e = new Employee(n);

        // read all the lines of rows up to n
        for (int i=0; i<n; i++) {
            line = s.nextLine();
            employee = line.split("\\|");

            // use Employee class to map each column value
            e.name[i] = employee[0];
            e.id[i] = Integer.parseInt(employee[1]);
            e.age[i] = Integer.parseInt(employee[2]);
            e.job[i] = employee[3];
            e.hiredDate[i] = Integer.parseInt(employee[4]);
        }

        // q: how many "queries" will follow
        int q = s.nextInt();

        // prepare for the search here by declaring variables
        double[] comparisonArray = new double[q];
        double average = 0;



        //sort employee object by employee ID to be ready for binary search
        sort(e.id);

        // execute BINARY-SEARCH up to number of queries
        for (int j=0; j<q; j++) {
            // low: the first index in the employee object (i.e. zero)
            int low = 0;
            // high: the last index in the array (i.e. object.length–1)
            int high = e.id.length;
            // mid: the middle value of the object/list
            int mid;

            // to calculate the number of comparisons
            int comparisons = 0;

            // obtain each employee id
            int empId = s.nextInt();

            // index number for the output
            int index = 0;

            // keep looping the list/object until finding the value you are looking for
            while (low < high) {
                // obtain the middle index
                mid = (low + high) / 2;
                if (empId <= e.id[mid]) {
                    // the previous highest index becomes the middle here
                    high = mid;
                    comparisons += 1;
                } else {
                    low = mid + 1;
                    comparisons += 1;
                }
            }

            // return value high corresponds to index # of the employee
            index = high;

            // calculate sum of the comparisons here
            comparisonArray[j] = comparisons;
            double sum = 0;
            for (double difference : comparisonArray) {
                sum += difference;
            }

            // calculate the average
            average = sum / q;

            // print out the results
            System.out.println("FOUND: Name: " + e.name[index] + ", Age: " + e.age[index] + ", Job: " + e.job[index] + ", Hired: " + e.hiredDate[index]);
            System.out.println("at index " + index + " after " + comparisons + " comparisons.");
        }
        System.out.println("Done! Average amount of work per query: " + average + " comparisons.");
        s.close();
    }
}
