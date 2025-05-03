public class LinkedList<T> {
    Node<T> head, tail;
    int size;

    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    public void addToHead(T data) {
        if (size == 0) {
            head = tail = new Node<T>(data);
        } else if (size == 1) {
            head = new Node<T>(data, head);
            tail.prev = head;
        } else {
            head.prev = new Node<T>(data, head);
            head = head.prev;
        }
        size++;
    }

    public void addToTail(T data) {
        if (size == 0) {
            head = tail = new Node<T>(data);
        } else {
            tail.next = new Node<T>(data);
            tail.next.prev = tail;
            tail = tail.next;
        }
        size++;
    }

    public int removeHead() {
        if (size == 0)
            return -1;
        else if (size == 1) {
            head = tail = null;
        } else {
            head.next.prev = null;
            head = head.next;
        }
        size--;
        return 0;
    }

    public int removeTail() {
        if (size == 0)
            return -1;
        else if (size == 1) {
            head = tail = null;
        } else {
            tail.prev.next = null;
            tail = tail.prev;
        }
        size--;
        return 0;
    }

    public int remove(T id) {
        if (size == 0)
            return -1;
        for (Node<T> current = head; current != null; current = current.next) {
            if (current.info == id) {
                if (current == head)
                    removeHead();
                else if (current == tail)
                    removeTail();
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return 0;
            }
        }
        return -1;
    }
}
