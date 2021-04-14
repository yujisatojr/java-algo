import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private final Employee[] roster;
    public int n;
    public int m;
    public int sumKey;
    public int key;
    public ArrayList<ArrayList<Employee>> hashtable;

    public Main() {
        var s = new Scanner(System.in);
        m = s.nextInt(); // size of the hashtable
        n = s.nextInt(); // number of incoming objects
        s.nextLine();
        roster = new Employee[n];
        for (int i=0;i<n;i++) {
            roster[i] = new Employee(s.nextLine());
        }
        hashtable = new ArrayList<>();

        for (int i=0; i<m; i++) {
            hashtable.add(new ArrayList<>());
        }

        for (int j=0;j<n;j++) {
            String name = roster[j].name;
            int ln = name.length();
            for (int t=0;t<ln;t++) {
                char character = name.charAt(t);
                int ascii_val = (int)character;
                sumKey += ascii_val;
            }
            key = sumKey % m;
            hashtable.get(key).add(roster[j]);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
