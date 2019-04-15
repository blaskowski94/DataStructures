package list;

public class LinkedList<X> implements List<X> {

    private Node first, last;
    private int size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    // add to end
    public void add(X item) {
        Node newItem = new Node(item);
        if (first == null) {
            first = newItem;
            last = first;
        } else {
            last.setNext(newItem);
            last = newItem;
        }
        size++;
    }

    // remove from front
    public X remove() {
        if (first == null) {
            throw new IllegalStateException("The LinkedList is empty");
        }
        X firstItem = first.getItem();
        first = first.getNext();
        size--;
        return firstItem;
    }

    public void insert(X item, int position) {
        if (position > size()) {
            throw new IllegalArgumentException("Position is greater than length of list");
        }
        Node currentNode = first;
        Node newNode = new Node(item);

        if (position == 0) {
            newNode.setNext(currentNode);
            first = newNode;
        } else {
            for (int i = 1; i < position; i++) {
                currentNode = currentNode.getNext();
            }
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
        }
        size++;
    }

    public X removeAt(int position) {
        if (position > size()) {
            throw new IllegalArgumentException("Position is greater than length of list");
        }

        if (position == 0) {
            return remove();
        }

        Node currentNode = first;
        for (int i = 1; i < position; i++) {
            currentNode = currentNode.getNext();
        }
        Node removeNode = currentNode.getNext();
        currentNode.setNext(removeNode.getNext());

        // if we removed the last item, update last
        if (position == (size() - 1)) {
            last = currentNode;
        }

        size--;
        return removeNode.getItem();
    }

    public X get(int position) {
        if (size() == 0 || position > size()) {
            throw new IllegalArgumentException("Position is greater than length of list or list is empty");
        }

        Node currentNode = first;
        for (int i = 0; i < position; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getItem();
    }

    public int find(X item) {
        Node currentNode = first;
        for (int i = 0; i < size(); i++) {
            if (currentNode.getItem().equals(item)) return i;
            currentNode = currentNode.getNext();
        }
        return -1;
    }

    public String toString() {
        StringBuilder contents = new StringBuilder();
        Node currentNode = first;

        while (currentNode != null) {
            contents.append(currentNode.getItem());
            currentNode = currentNode.getNext();

            if (currentNode != null) {
                contents.append(", ");
            }
        }
        return contents.toString();
    }

    public int size() {
        return size;
    }

    private class Node {
        private Node next;
        private X item;

        Node(X item) {
            this.next = null;
            this.item = item;
        }

        void setNext(Node next) {
            this.next = next;
        }

        Node getNext() {
            return this.next;
        }

        public X getItem() {
            return this.item;
        }
    }
}
