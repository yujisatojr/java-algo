import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node implements Comparable<Node> {

    public String name;
    public List<Node> neighbors;
    boolean visited;

    // initiate the variables here
    public Node(String n) {
        name = n;
        neighbors = new ArrayList<>();
        visited = false;
    }

    // create edges/connections here
    public void addNeighbor(Node other) {
        neighbors.add(other);
        //sort the neighbors by alph order for algorithms
        Collections.sort(neighbors);
    }

    // compare nodes contained in each name(alph letter)
    @Override
    public int compareTo(Node other) {
        return name.compareTo(other.name);
    }

    // print out all nodes by name(alph letter)
    public void printNeighbors() {
        System.out.print(name + ": ");

        // use for loop since the variable neighbors' type is Node and not String
        for (Node n : neighbors) {
            System.out.print(n.name);
        }
    }
}
