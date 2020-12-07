import java.util.Scanner;

public class Queue {

    public static void main(String[] args) {
        LinkedList<String> lnl = new LinkedList<>();

        Scanner s = new Scanner(System.in);
        int size = 0;

        while (true) {
            String command = s.next();
            if (command.equals("***")) {
                break;
            }

            if (command.equals("ENQUEUE")) {
                String data = s.next();
                lnl.addNode(data);
                size = size + 1;
            }

            if (command.equals("DEQUEUE")) {
                if (size == 0) {
                    System.out.println("empty");
                } else {
                    String data = lnl.removeAt(0);
                    size = size - 1;
                    System.out.println(data);
                }
            }

            if (command.equals("CLEAR")) {
                lnl.head = null;
                size = 0;
            }

            if (command.equals("PRINT")) {
                lnl.printAll();
            }

        }
        s.close();
    }
}
