import java.util.ArrayList;
import java.util.List;

public class Node {
    //the attributes that node contains
    //label is the alphabetical letters
    String label;
    boolean visited;
    List<Edge> neighbors;

    //to instantiate Node in main class
    public Node(String n) {
        label = n;
        visited = false;
        neighbors = new ArrayList<>();
    }

    //add neighbor (edge) to the node
    public void addNeighbor(Edge e) {
        neighbors.add(e);
    }
}
