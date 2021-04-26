import java.util.Scanner;

public class LCS {

    // declare global field to create tables
    int b[][];
    int c[][];

    public void LCSLength(String x,String y) {
        // get the length of each string
        int m = x.length();
        int n = y.length();

        // instantiate new tables
        b = new int[m+1][n+1];
        c = new int[m+1][n+1];

        // fill the table with 0s
        for (int i=0;i<=m;i++) {
            c[i][0] = 0;
        }
        // fill the table with 0s
        for (int j=0;j<=n;j++) {
            c[0][j] = 0;
        }
        // compare two strings
        for (int i=1;i<=m;i++) {
            for (int j=1;j<=n;j++) {
                if (x.charAt(i-1) == y.charAt(j-1)) { // if the letter matches
                    c[i][j] = c[i-1][j-1] + 1; // add 1 to the number above
                    b[i][j] = 0; // draw a diagonal arrow
                } else if (c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i-1][j];
                    // indicates we copied the number from the upper cell
                    b[i][j] = 1; // draw an up arrow
                } else {
                    c[i][j] = c[i][j-1];
                    b[i][j] = 2; // draw a left arrow
                }
            }
        }
    }

    // print the longest common sequence
    public void printLCS(String x, int i, int j) {
        // if there is no letters in the string
        if (i == 0 || j == 0) {
            return;
        }
        // look up the table and print depends on number inside of a cell
        if (b[i][j] == 0) {
            printLCS(x, i-1, j-1);
            System.out.print(x.charAt(i-1)); // print out the letter
        } else if (b[i][j] == 1) {
            printLCS(x, i-1,j);
        } else {
            printLCS(x,i,j-1); // continue until you find a cell that indicates matching
        }
    }

    public LCS() {
        Scanner s = new Scanner(System.in); // instantiate Scanner variable here
        // read the first and second strings
        String x = s.nextLine();
        String y = s.nextLine();
        //print out outputs here
        System.out.println("String X: " + x);
        System.out.println("String Y: " + y);
        System.out.print("LCS: ");
        //call the two main methods
        LCSLength(x,y);
        printLCS(x,x.length(),y.length());
    }

    public static void main(String[] args) {
        new LCS();
    }
}
