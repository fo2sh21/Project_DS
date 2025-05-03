public class Column<T extends ColumnNode> extends LinkedList<T> {
    public Column() {
        super();
    }

    public void display(String preString) {
        for (Node<T> current = head; current != null; current = current.next) {
            System.out.println(preString + " " + current.info);
        }
    }

    public Node<T> findById(int id) {
        for (Node<T> current = head; current != null; current = current.next) {
            if (current.info.getId() == id) {
                return current;
            }
        }
        return null;
    }

    public int removeById(int id) {
        Node<T> node = findById(id);
        if (node == null) {
            return -1;
        }
        return remove(node.info);
    }

    public Node<T> nodeWithMaxFreshId() {
        Node<T> result = head;
        for (Node<T> current = head; current != null; current = current.next) {
            if (current.info.getFreshId() > result.info.getFreshId()) {
                result = current;
            }
        }
        return result;
    }

    public void removeAllContentInstancesOfValue(int value) {
        for (Node<T> current = head; current != null; current = current.next) {
            current.info.removeFromContent(value);
        }
    }

    public void sortAscendingly() {
        if (size <= 1)
            return;

        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            while (current.next != null) {
                if (current.info.getId() > current.next.info.getId()) {
                    T temp = current.info;
                    current.info = current.next.info;
                    current.next.info = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
}
