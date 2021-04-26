public class Edge {
    //attributes that a edge contains
    Node n1;
    Node n2;
    int weight;

    //to instantiate Edge in main class
    public Edge(Node a, Node b, int w) {
        n1 = a;
        n2 = b;
        weight = w;
    }
}
