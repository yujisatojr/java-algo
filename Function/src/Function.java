import java.util.Arrays;
import java.util.Scanner;

public class Function {

    public static void main(String[] args) {
        // get input from stdin
        Scanner s = new Scanner(System.in);
        String line1 = s.nextLine();
        String line2 = s.nextLine();
        String line3 = s.nextLine();

        // divide the first line into domain array
        String[] domain = line1.split(" ");
        // divide the second line into co-domain array
        String[] codomain = line2.split(" ");

        // divide the third line into mapped relational array
        String[] func = line3.split(" ");
        int n = func.length / 2;
        // divide the relations array into x values and y values
        String[] x = new String[n];
        String[] y = new String[n];
        for (int i = 0; i < n; i++) {
            x[i] = func[i * 2];
            y[i] = func[i * 2 + 1];
        }

        // print domain and co-domain values
        String domain_out = String.join(", ", domain);
        System.out.println("DOMAIN: { " + domain_out + " }");

        String codomain_out = String.join(", ", codomain);
        System.out.println("CODOMAIN: { " + codomain_out + " }");

        // print mapped relational values
        int length = x.length;
        System.out.print("RELATION: { ");
        for (int i = 0; i < length; i++) {
            if (i < length-1) {
                System.out.print("(" +  x[i]+ "," + y[i]+ ")" + ",");
            }
            else {
                System.out.print("(" +  x[i]+ "," + y[i]+ ")");
            }
        }
        System.out.print(" }");
        System.out.println("");

        // sort through arrays for equals function
        Arrays.sort(domain);
        Arrays.sort(codomain);
        Arrays.sort(x);
        Arrays.sort(y);

        //check to see if it is a function or not
        boolean function = false;
        if (Arrays.equals(domain, x)) {
            function = true;
            System.out.println("This is a function.");
        }
        else {
            System.out.println("This is *not* a function.");
        }

        // if it is a function, check to see if it is one-to-one or/and onto
        if (function == true) {

            // check to see if it is onto function
            boolean onto = false;
            // get unique values from mapped y values
            String[] unique = Arrays.stream(y).distinct().toArray(String[]::new);
            if (Arrays.equals(codomain, unique)) {
                onto = true;
                System.out.println("It is onto.");
            }
            else {
                System.out.println("It is *not* onto.");
            }

            // check to see if it is an one-to-one function
            boolean one_to_one = true;
            for (int i = 0; i < y.length; i++) {
                for (int j = i + 1; j < y.length; j++) {
                    if (y[i].equals(y[j])) {
                        one_to_one = false;
                    }
                }
            }
            // print our result for one-to-one
            if (one_to_one == false) {
                System.out.println("It is *not* one-to-one.");
            }
            else if (one_to_one == true) {
                System.out.println("It is one-to-one.");
            }

            // print our result for bijection
            if (one_to_one == true && onto == true) {
                System.out.println("It is a bijection.");
            }
            else if (one_to_one == false || onto == false) {
                System.out.println("It is *not* a bijection");
            }
        }
    }
}
