public class LinkedList {
    Node head, tail;
    int size;

    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    public LinkedList(LinkedList other) {
        for (Node current = other.head; current != null; current = current.next) {
            Node clone = new Node(current.info, current.freshId);
            clone.content = new LinkedList(current.content);

            if (this.size == 0) {
                head = tail = clone;
            } else {
                tail.next = clone;
                clone.prev = tail;
                tail = clone;
            }
            this.size++;
        }
    }

    public void addToHead(int data, int id) {
        if (size == 0) {
            head = tail = new Node(data);
        } else if (size == 1) {
            head = new Node(data, head, id);
            tail.prev = head;
        } else {
            head.prev = new Node(data, head, id);
            head = head.prev;
        }
        size++;
    }

    public void addToTail(int data, int id) {
        if (size == 0) {
            head = tail = new Node(data, id);
        } else {
            tail.next = new Node(data, id);
            tail.next.prev = tail;
            tail = tail.next;
        }
        size++;
    }

    public void addToTail(Node data) {
        if (size == 0) {
            head = tail = data;
        } else {
            tail.next = data;
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

    public int remove(int id) {
        if (size == 0)
            return -1;
        for (Node current = head; current != null; current = current.next) {
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

    public void display(String preString) {
        for (Node current = head; current != null; current = current.next) {
            System.out.println(preString + " " + current.info);
        }
    }

    public Node find(int id) {
        for (Node current = head; current != null; current = current.next) {
            if (current.info == id) {
                return current;
            }
        }
        return null;
    }

    public Node nodeWithMaxFreshId() {
        Node result = head;
        for (Node current = head; current != null; current = current.next) {
            if (current.freshId > result.freshId) {
                result = current;
            }
        }
        return result;
    }

    public void removeAllContentInstancesOfValue(int value) {
        for (Node current = head; current != null; current = current.next) {
            current.content.remove(value);
        }
    }

    public void sortAscendingly() {
        for (Node i = head; i != null; i = i.next) {
            Node minNode = i;
            for (Node j = i.next; j != null; j = j.next) {
                if (j.info < minNode.info) {
                    minNode = j;
                }
            }
            if (minNode != i) {
                int temp = i.info;
                LinkedList tempContent = i.content;
                i.info = minNode.info;
                i.content = minNode.content;
                i.freshId = minNode.freshId;
                minNode.info = temp;
                minNode.content = tempContent;
            }
        }
    }

}
