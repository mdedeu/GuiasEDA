package Ej3_SortedLinkedListWithHeader;

import Ej1_SortedLinkedList.SortedListService;

import java.util.Iterator;

public class SortedLinkedListWithHeader<T extends Comparable<? super T>> implements SortedListService<T> {

    protected Node firstElement;
    protected Node lastElement;
    protected int size = 0;

    public void dump() {
        StringBuilder string = new StringBuilder();
        for (Node rec = firstElement; rec != null; rec = rec.next) {
            string.append(rec.value);
            if (rec.next != null) {
                string.append(" -> ");
            }
        }
        System.out.println(string.toString());
    }

    public boolean isEmpty() {
        return firstElement == null;
    }

    public void add(T element) {
        Node prev = firstElement;
        Node rec = firstElement;

        while (rec != null && rec.value.compareTo(element) < 0) {
            // go on
            prev = rec;
            rec = rec.next;
        }

        // repeated?
        if (rec != null && rec.value.compareTo(element) == 0) {
            System.err.printf("Insertion failed. %s repeated%n", element);
            return;
        }

        Node newNode = new Node(element, rec);
        size++;
        // does the first element change?
        if (prev == rec) {
            firstElement = newNode;
        } else {
            prev.next = newNode;
        }
        if (newNode.next == null) {
            lastElement = newNode;
        }
    }

    public void delete(T element) {
        firstElement = deleteRec(firstElement, null, element);
    }

    private Node deleteRec(Node first, Node prev, T elem) {
        int cmp;

        if (first == null || (cmp = first.value.compareTo(elem)) > 0) {
            return first;
        }

        if (cmp == 0) {
            if (first == lastElement) {
                lastElement = prev;
            }
            size--;
            return first.next;
        }

        first.next = deleteRec(first.next, first, elem);
        return first;
    }

    public int size() {
        return size;
    }

    public T getMin() {
        if (this.isEmpty()) {
            //throw new IllegalStateException("List is empty.");
            System.err.println("List is empty");
            return null;
        }
        return firstElement.value;
    }

    public T getMax() {
        if (this.isEmpty()) {
            //throw new IllegalStateException("List is empty.");
            System.err.println("List is empty");
            return null;
        }
        return lastElement.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node current = firstElement;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IllegalArgumentException("Iterator no has next.");
                }
                Node aux = current;
                current = aux.next;
                return aux.value;
            }
        };
    }

    protected final class Node {

        protected final T value;
        protected Node next;

        // do not accept nulls in the data
        Node(T theValue, Node theNext) {
            if (theValue == null)
                throw new RuntimeException("Null is not accepted for data");
            value = theValue;
            next = theNext;
        }

        public T getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
