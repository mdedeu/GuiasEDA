package Ej1_SortedLinkedList;

import java.util.Iterator;

public class SortedLinkedList<T extends Comparable<? super T>> implements SortedListService<T> {

    private Node firstElement;

    public void dump() {
        StringBuilder string = new StringBuilder();
        for (Node rec= firstElement; rec != null; rec = rec.next) {
            string.append(rec.value);
            if(rec.next != null) {
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

        while(rec != null && rec.value.compareTo(element) < 0) {
            // go on
            prev = rec;
            rec = rec.next;
        }

        // repeated?
        if(rec != null && rec.value.compareTo(element) == 0) {
            System.err.printf("Insertion failed. %s repeated%n", element);
            return;
        }

        Node newNode= new Node(element, rec);
        // does the first element change?
        if (prev == rec) {
            firstElement = newNode;
        } else {
            prev.next = newNode;
        }
    }

    public void delete(T element) {
        firstElement = deleteRec(firstElement, element);
    }

    private Node deleteRec(Node first, T elem) {
        int cmp;

        if(first == null || (cmp = first.value.compareTo(elem)) > 0) {
            return first;
        }

        if(cmp == 0) {
            return first.next;
        }

        first.next = deleteRec(first.next, elem);
        return first;
    }

    public int size() {
        int size = 0;
        Node aux = firstElement;
        while(aux != null) {
            size++;
            aux = aux.next;
        }
        return size;
    }

    public T getMin() {
        if(this.isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
        return firstElement.value;
    }

    public T getMax() {
        if(this.isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
        Node aux = firstElement;
        while(aux.next != null) {
            aux = aux.next;
        }
        return aux.value;
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
                if(!hasNext()) {
                    throw new IllegalArgumentException("Iterator no has next.");
                }
                Node aux = current;
                current = aux.next;
                return aux.value;
            }
        };
    }

    private final class Node {

        private final T value;
        private Node next;

        // do not accept nulls in the data
        Node(T theValue, Node theNext) {
            if (theValue == null)
                throw new RuntimeException("Null is not accepted for data");
            value = theValue;
            next = theNext;
        }
    }
}
