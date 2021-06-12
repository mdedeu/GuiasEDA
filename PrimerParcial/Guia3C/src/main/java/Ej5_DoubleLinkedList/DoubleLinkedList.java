package Ej5_DoubleLinkedList;

import Ej1_SortedLinkedList.SortedListService;

import java.util.Iterator;

public class DoubleLinkedList<T extends Comparable<? super T>> implements SortedListService<T>,
        Iterable<T> {

    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public void dump() {
        StringBuilder string = new StringBuilder();
        for (Node rec = first; rec != null; rec = rec.next) {
            string.append(rec.value);
            if (rec.next != null) {
                string.append(" -> ");
            }
        }
        System.out.println(string.toString());
    }

    public void reverseDump() {
        StringBuilder string = new StringBuilder();

        for (Node rec = last; rec != null; rec = rec.prev) {
            string.append(rec.value);
            if (rec.prev != null) {
                string.append(" -> ");
            }
        }
        System.out.println(string.toString());
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T element) {
        first = addRec(first, null, element);
    }

    private Node addRec(Node first, Node prev, T element) {
        int cmp;
        if (first == null) {
            Node node = new Node(element, prev, null);
            last = node;
            size++;
            return node;
        }
        cmp = first.value.compareTo(element);
        if (cmp > 0) {
            Node node = new Node(element, first.prev, first);
            if(prev != null) {
                prev.next = node;
            }
            first.prev = node;
            size++;
            return node;
        }
        if(cmp == 0) {
            System.out.println("Insertion failed. Repeated element");
            return first;
        }
        first.next = addRec(first.next, first, element);
        return first;
    }

    @Override
    public void delete(T element) {
        first = deleteRec(first, element);
    }

    private Node deleteRec(Node first, T element) {
        int cmp;
        if(first == null || (cmp = first.value.compareTo(element)) > 0) {
            return first;
        }
        if(cmp == 0) {
            if(first == last) {
                last = first.prev;
            }
            if(first.next != null) {
                first.next.prev = first.prev;
            }
            size--;
            return first.next;
        }
        first.next = deleteRec(first.next, element);
        return first;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getMin() {
        if (this.isEmpty()) {
            System.err.println("List is empty");
            return null;
        }
        return first.value;
    }

    @Override
    public T getMax() {
        if (this.isEmpty()) {
            System.err.println("List is empty");
            return null;
        }
        return last.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node current = null;
            Node next = first;
            boolean canRemove = false;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public T next() {
                if(!hasNext()) {
                    throw new IllegalArgumentException("List no has next");
                }
                current = next;
                next = next.next;
                canRemove = true;
                return current.value;
            }

            @Override
            public void remove() {
                if(!canRemove) {
                    throw new IllegalStateException("No llamó a next()");
                }
                if(current.prev != null) {
                    current.prev.next = next;
                }
                if(next != null) {
                    next.prev = current.prev;
                }
                if(current == first) {
                    first = next;
                    first.prev = null;
                }
                if(current == last) {
                    last = current.prev;
                    last.next = null;
                }
                canRemove = false;
                size--;
            }
        };
    }

    protected final class Node {
        protected final T value;
        protected Node prev;
        protected Node next;

        public Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
