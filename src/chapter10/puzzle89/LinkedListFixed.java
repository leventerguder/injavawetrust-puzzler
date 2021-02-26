package chapter10.puzzle89;
// Fixed but could be MUCH better
public class LinkedListFixed<E> {

    private Node head = null;

    private class Node {
        E value;
        Node next;

        // Node constructor links the node as a new head
        Node(E value) {
            this.value = value;
            this.next = head;
            head = this;
        }
    }

    public void add(E e) {
        new Node(e);
        // Link node as new head
    }

    public void dump() {
        for (Node n = head; n != null; n = n.next)
            System.out.print(n.value + " ");
    }

    public static void main(String[] args) {

        LinkedListFixed<String> list = new LinkedListFixed<String>();
        list.add("world");
        list.add("Hello");
        list.dump();
    }
}
