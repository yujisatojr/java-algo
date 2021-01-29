import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BubbleSort {

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

        // declare necessary variables for bubble sort
        int comparisons = 0;
        int temp;
        String tempStr;

        // execute BUBBLE SORT here
        // program continues until the entire array of object is sorted
        // how many times the sort is executed
        for (int j=0; j<=e.id.length-2; j++) {
            // loop through within the array
            for (int k=e.id.length-1; k>=j+1; k--) {
                // if the right digit is smaller than the left digit
                if (e.id[k] < e.id[k-1]) {
                    // exchange emp id
                    temp = e.id[k];
                    e.id[k] = e.id[k-1];
                    e.id[k-1] = temp;
                    // exchange emp name
                    tempStr = e.name[k];
                    e.name[k] = e.name[k-1];
                    e.name[k-1] = tempStr;
                    // exchange emp age
                    temp = e.age[k];
                    e.age[k] = e.age[k-1];
                    e.age[k-1] = temp;
                    // exchange emp job
                    tempStr = e.job[k];
                    e.job[k] = e.job[k-1];
                    e.job[k-1] = tempStr;
                    // exchange emp hired date
                    temp = e.hiredDate[k];
                    e.hiredDate[k] = e.hiredDate[k-1];
                    e.hiredDate[k-1] = temp;

                    // increment counter
                    comparisons += 1;

                    // print out emp ids in the array
                    for (int l=0; l<e.id.length; l++) {
                        System.out.print(e.id[l] + " ");
                    }
                    System.out.println(" ");
                } else {
                    comparisons += 1;
                }
            }
        }
        // print out total number of comparisons
        System.out.println("It took " + comparisons + " comparisons to sort this list.");
        s.close();

        // export the sorted information of employee into txt file
        // borrowed from RANDOM NAME GENERATOR by Dr.Draper
        /*
        String filename = "roster" + n + ".txt";
        try (FileWriter fw = new FileWriter(filename)) {
            //write the "roster" section of the file
            fw.write(n + "\n");
            for (int m=0; m<n; ++m) {
                fw.write(e.name[m] + "|");
                fw.write(e.id[m] + "|");
                fw.write(e.age[m] + "|");
                fw.write(e.job[m] + "|");
                fw.write(e.hiredDate[m] + "|");
                fw.write("\n");
            }
            System.out.println("Output written to " + filename);
        } catch (IOException io) {
            io.printStackTrace();
        }

         */
    }
}
