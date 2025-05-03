public class Node<T> {
    Node<T> next, prev;
    T info;

    Node(T info) {
        this.info = info;
        next = null;
    }

    Node(T info, Node<T> next) {
        this.info = info;
        this.next = next;
        prev = null;
    }

    Node(T info, Node<T> next, Node<T> prev) {
        this.info = info;
        this.next = next;
        this.prev = prev;
    }
}