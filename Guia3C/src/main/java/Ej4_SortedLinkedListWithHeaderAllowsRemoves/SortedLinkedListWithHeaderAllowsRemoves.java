package Ej4_SortedLinkedListWithHeaderAllowsRemoves;

import Ej3_SortedLinkedListWithHeader.SortedLinkedListWithHeader;

import java.util.Iterator;

public class SortedLinkedListWithHeaderAllowsRemoves<T extends Comparable<? super T>> extends SortedLinkedListWithHeader<T> {

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node previous = firstElement;
            Node current = firstElement;
            Node next = firstElement;
            boolean canRemove = false;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public T next() {
                if(!hasNext()) {
                    throw new IllegalArgumentException("Iterator no has next.");
                }
                previous = current;
                current = next;
                next = next.getNext();
                canRemove = true;
                return current.getValue();
            }

            @Override
            public void remove() {
                if(!canRemove) {
                    throw new IllegalStateException("NEXT");
                }
                previous.setNext(next);
                if(current == firstElement) {
                    firstElement = firstElement.getNext();
                }
                if(current == lastElement) {
                    lastElement = previous;
                }
                canRemove = false;
                size--;
            }
        };
    }
}
