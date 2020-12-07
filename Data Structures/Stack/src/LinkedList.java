public class LinkedList<E> {
    Node<E> head;
    int size;

    public LinkedList() {
        head = null;
        size = 0;
    }
    public void addNode(E d) {
        Node<E> newNode = new Node<E>(d);
        if (head == null) {
            // the new node will be the head if it is inserted at index 0
            head = newNode;
        } else {
            Node<E> current = head;
            //loop through the list until it finds the last node
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        // increment the size of the list
        size++;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        Node<E> current = head;
        // lopp through the entire list
        for (int i=0; i<index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public E removeAt(int index) {
        E result = null;
        Node<E> current = head;
        if (index == 0) {
            result = head.data;
            head = head.next;
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            result = current.next.data;
            current.next = current.next.next;
        }
        // there is one less node in the list
        size--;
        return result;
    }

    public void insertAt(int index, E d) {
        Node<E> newNode = new Node<E>(d);
        Node<E> current = head;
        size++;
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public void printAll() {
        Node<E> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
