import java.util.Scanner;

public class SequentialSearch {

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

        // how many "queries" will follow: an ID number of the employees in list
        int q = s.nextInt();

        // prepare for the search here by declaring variables
        double[] comparisonArray = new double[q];
        double average = 0;

        // execute sequential search up to number of queries
        for (int j=0; j<q; j++) {
            int empId = s.nextInt();
            int index = 0;
            int comparisons = 0;

            // loop through the list of employees and find the employee associated with the given ID
            for (int k=0; k<e.id.length; k++) {
                comparisons += 1;

                // when the program finds the employee id that matches the input number
                if (e.id[k] == empId) {
                    index = k;
                    break;
                }
            }

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
