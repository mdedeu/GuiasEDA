package lists;

public class DoublyLinkedListWithHeader<ValueType> implements LinkedListWithHeader<ValueType> {

  Node head;
  Node tail;
  int size = 0;

  @Override
  public void addFirst(ValueType value) {
    if (this.head == null) {
      this.head = new Node(value,null,null);
      this.tail = this.head;
    } else {
      Node adding = new Node(value, null, this.head);
      this.head.prev = adding;
      this.head = adding;
    }
    this.size++;
  }

  @Override
  public void addLast(ValueType value) {
    if (this.head == null) {
      this.head = new Node(value,null,null);
      this.tail = this.head;
    } else {
      Node adding = new Node(value, this.tail, null);
      this.tail.next = adding;
      this.tail = adding;
    }
    this.size++;
  }

  @Override
  public ValueType remove(ValueType value) {
    if (head == null) return null;
    ValueType toReturn;
    if (head.value.equals(value)) {
      toReturn = head.value;
      head = head.next;
      if (head == null)
        tail = null;
      else
        head.prev = null;
      this.size--;
      return toReturn;
    } else if (tail.value.equals(value)) {
      toReturn = tail.value;
      tail = tail.prev;
      tail.next = null;
      this.size--;
      return toReturn;
    }
    Node current = this.head;
    while (current != null && !current.value.equals(value)) {
        current = current.next;
    }
    if (current == null) {
       return null;
    }
    current.prev.next = current.next;
    current.next.prev = current.prev;
    this.size--;
    return current.value;
  }

  @Override
  public ValueType get(int index) {
    if (index < 0 || size < index) return null;
    Node current = this.head;
    while (index --> 0) {
      current = current.next;
    }
    return current.value;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean contains(ValueType value) {
    Node current = head;

    while(current != null && !current.value.equals(value))
      current = current.next;

    return current != null;
  }

  class Node {
    ValueType value;
    Node prev;
    Node next;

    public Node(ValueType value) {
      this.value = value;
    }

    public Node(ValueType value, Node prev, Node next) {
      this.value = value;
      this.prev = prev;
      this.next = next;
    }
  }
}
