import java.util.Scanner;

public class Main {

    Node root; // this is the root node of the tree

    public void insert(Node z) {
        Node y = null;
        Node x = root;
        while (x != null) { // if the root node is there
            y = x;
            if (z.key < x.key) { // compare the nodes based on their keys
                x = x.left; // in case the parent node was bigger
            } else {
                x = x.right; // in case the parent node was smaller
            }
        }
        if (y == null) { // if the root does not exist
            root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

    // FIND function
    public Node search(Node x, int k) {
        if (x == null || k == x.key) { // if root node does not exist or the root is the ley
            return x;
        } else if (k < x.key) { // if the key is bigger, moves to the right
            if (x.left != null) {
                System.out.print(x.left.key + " ");
            }
            return search(x.left, k); // if the key is smaller, moves to the left
        } else {
            if (x.right != null) {
                System.out.print(x.right.key + " ");
            }
            return search(x.right, k);
        }
    }

    public void preorder(Node z) { // print before orders
        if (z != null) {
            System.out.print(z.key + " ");
            preorder(z.left);
            preorder(z.right);
        }
    }

    public void inorder(Node z) { // print in the middle of orders
        if (z != null) {
            inorder(z.left);
            System.out.print(z.key + " ");
            inorder(z.right);
        }
    }

    public void postorder(Node z) { // print after the orders
        if (z != null) {
            postorder(z.left);
            postorder(z.right);
            System.out.print(z.key + " ");
        }
    }

    public Main() {
        Scanner s = new Scanner(System.in); // instantiate Scanner variable here
        int n = s.nextInt(); // takes the number of lines
        for (int i=0; i<n; i++) {
            String command = s.next(); // get the command name
            switch (command) {
                case "ADD":
                    int k = s.nextInt(); // get the value we are adding to the tree
                    System.out.println("Adding " + k);
                    insert(new Node(k)); // call insert function
                    break;
                case "FIND":
                    k = s.nextInt(); // get the value we are looking for
                    System.out.print("Looking for " + k + "... ");
                    if (root != null) {
                        System.out.print(root.key + " ");
                    }
                    Node result = search(root, k); // call search function
                    if (result != null) { // if found
                        System.out.print(": found");
                    } else {// if not found
                        System.out.print(": not found");
                    }
                    System.out.println();
                    break;
                case "CLEAR":
                    root = null; // delete the entire tree
                    System.out.println("(tree deleted!)");
                    break;
                case "PREORDER":
                    System.out.print("PREORDER: ");
                    preorder(root); // call preorder method
                    System.out.println();
                    break;
                case "INORDER":
                    System.out.print("INORDER: ");
                    inorder(root); // call inorder method
                    System.out.println();
                    break;
                case "POSTORDER":
                    System.out.print("POSTORDER: ");
                    postorder(root); // call postorder method
                    System.out.println();
                    break;
                default: // if command not found
                    System.out.println("unknown command");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Main(); // call main method
    }
}
