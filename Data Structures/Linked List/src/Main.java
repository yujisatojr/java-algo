import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LinkedList<String> lnl = new LinkedList<>();
        lnl.addNode("String 1");
        lnl.addNode("String 2");
        lnl.addNode("String 3");
        lnl.printAll();

        LinkedList<Integer> lnl2 = new LinkedList<>();
        lnl2.addNode(1);
        lnl2.addNode(2);
        lnl2.addNode(3);
        lnl2.printAll();

        // clear linked list
        // lnl.head = null;
    }
}
