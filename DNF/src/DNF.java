import java.util.Scanner;

public class DNF {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // read the first line of the input
        String line1 = s.nextLine();

        // read variable (column) names
        String[] vars = line1.split(" ");

        // see how many columns in first line
        int numVars = vars.length - 1;
        s.nextLine();

        // declare the final string output
        String last = "";

        // loop through the entire input row by row
        // go up to 2 to the number of columns
        for (int i=0; i<Math.pow(2, numVars); i++) {
            String row = s.nextLine();

            // add each row in rows array
            String[] rows = row.split(" ");
            // this "out" is the output of each row
            var out = Integer.parseInt(rows[numVars]);
            String output = "";

            // create if statement for output that is 1
            if (out == 1) {
                for (int j=0; j<numVars; j++) {
                    // by using "varname", we can get variable name from input
                    String varname = vars[j];
                    int number = Integer.parseInt(rows[j]);

                    // if "number" or the actual value in row is 0
                    if (number == 0) {
                        varname = varname + "'";
                    } else { // if the value is 1
                        varname = varname;
                    }

                    // create product by combining letters
                    output = output + varname;
                }

                // create sum by combining products
                last = last + output + " + ";
            }
        }

        // omits the last + symbol
        last = last.substring(0, last.length() - 2);

        // print the sum of products to output
        System.out.print(last);
    }
}
