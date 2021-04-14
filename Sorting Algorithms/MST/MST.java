import java.util.*;

public class MST {

    //this hashmap holds all nodes
    Map<String, Node> allNodes;
    //initialize bucket here (bucket = null) would not work!
    List<Edge> bucket = new ArrayList<>();

    //main method
    public MST() {
        //get input from std.in
        Scanner s = new Scanner(System.in);
        allNodes = new HashMap<>();
        String labels = s.nextLine();
        //label alphabet to each node "A", "B", etc...
        for (int i=0; i<labels.length(); i++) {
            char label = labels.charAt(i);
            Node no = new Node("" + label);
            allNodes.put(no.label, no);
        }
        //get the total number of nodes
        int n = s.nextInt();
        for (int i=0; i<n; i++) {
            //a is node1 : b is node2
            var a = s.next();
            var b = s.next();
            var w = s.nextInt();
            Node n1 = allNodes.get(a);
            Node n2 = allNodes.get(b);
            //create edges
            Edge e = new Edge(n1, n2, w);
            //connect nodes as neighbors
            n1.addNeighbor(e);
            n2.addNeighbor(e);
        }
        //call mst prim method here
        //print out results
        //access to the first value/node within the hashmap and pass the node to MST_PRIM method
        MST_PRIM(allNodes.entrySet().iterator().next().getValue());
    }

    public void MST_PRIM(Node start) {
        //initialize MST_EDGES here and avoid getting null pointer error!
        List<Edge> MST_EDGES = new ArrayList<>();
        int total_weight = 0;
        //add both of the neighbor nodes to the bucket
        bucket.addAll(start.neighbors);
        //call getBestEdge() method here
        Edge E = getBestEdge();

        //while E is not null
        while (E != null) {
            //let n1, n2 be e's nodes
            E.n1.addNeighbor(E);
            E.n2.addNeighbor(E);
            //mark them as visited
            E.n1.visited = true;
            E.n2.visited = true;
            total_weight = total_weight + E.weight;
            //add E to MST_Edges
            MST_EDGES.add(E);
            //add all of n1 and n2's edges to bucket
            bucket.addAll(E.n1.neighbors);
            bucket.addAll(E.n2.neighbors);
            E = getBestEdge();
        }
        //print out weight here
        System.out.println("MST has a weight of " + total_weight + " and consists of these edges:");
        //print out edges by showing node labels
        for (int i=0; i<MST_EDGES.size(); i++) {
            System.out.print(MST_EDGES.get(i).n1.label + " - "); //node one
            System.out.print(MST_EDGES.get(i).n2.label); //node two
            System.out.println();
        }
    }

    //method that searches the best weight among all the weights
    public Edge getBestEdge() {
        Edge best = null;
        //continue if best is not null and bucket is not empty
        while (best == null && bucket.isEmpty() == false) {
            best = bucket.get(0);
            for (Edge e : bucket) {
                //best = min(bucket)
                if (e.weight < best.weight) {
                    best = e;
                }
            }
            //if both of best's nodes are already visited
            if (best.n1.visited == true && best.n2.visited == true) {
                bucket.remove(best);
                best = null;
            }
        }
        //return best node
        return best;
    }

    public static void main(String[] args) {
        new MST();
    }
}
