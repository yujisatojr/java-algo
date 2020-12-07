import java.util.*;

public class BFS {

    static List<Node> nodes;
    static Node starter;

    public BFS() {
        nodes = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        String nodeNames = s.nextLine();
        String[] names = nodeNames.split(" ");
        for (var name : names) {
            Node n = new Node(name);
            nodes.add(n);
        }

        String starterName = s.nextLine();
        starter = findNodeByName(starterName);

        while (true) {
            String a = s.next();
            if (a.equals("***")) {
                break;
            }
            Node n1 = findNodeByName(a);

            String b = s.next();
            Node n2 = findNodeByName(b);

            n1.addNeighbor(n2);
            n2.addNeighbor(n1);
        }

        System.out.println("Node Neighbors:");

        for (Node n : nodes) {
            n.printNeighbors();
            System.out.println("");
        }
        System.out.println("");
    }

    private Node findNodeByName(String name) {
        for (Node n : nodes) {
            if (name.equals(n.name)) {
                return n;
            }
        }
        return null;
    }

    public static void DepthFirst(Node n1) {
        n1.visited = true;
        System.out.print(n1.name + " ");

        for (Node n : n1.neighbors) {
            if (n.visited == false) {
                DepthFirst(n);
            }
        }
    }

    public static void BreadthFirst(Node n2) {
        Queue<Node> q = new ArrayDeque();

        n2.visited = true;
        System.out.print(n2.name + " ");
        q.add(n2);

        while (q.size() > 0) {
            for (Node n : q.peek().neighbors) {
                if (n.visited == false) {
                    n.visited = true;
                    System.out.print(n.name + " ");
                    q.add(n);
                }
            }
            q.remove();
        }
    }

    public static void main(String[] args) {
        BFS m = new BFS();

        System.out.println("Breadth-First Search:");
        BreadthFirst(starter);
    }
}
