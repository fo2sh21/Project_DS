public class Node {
    Node next, prev;
    int info;
    LinkedList content;
    int freshId;



    Node(int info, int id) {
        this.info = info;
        this.freshId = id;
        next = null;
        content = new LinkedList();
    }

    Node(int info, Node next, int id) {
        this.freshId = id;
        this.info = info;
        this.next = next;
        prev = null;
        content = new LinkedList();
    }

    Node(int info, Node next, Node prev, int id) {
        this.freshId = id;
        this.info = info;
        this.next = next;
        this.prev = prev;
        content = new LinkedList();
    }
}
