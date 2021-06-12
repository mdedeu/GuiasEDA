package lists;

public class SinglyLinkedListNoHeader<ValueType> implements LinkedListNoHeader<ValueType> {


  ValueType value;
  SinglyLinkedListNoHeader<ValueType> next;

  public SinglyLinkedListNoHeader(ValueType value, SinglyLinkedListNoHeader<ValueType> next) {
    this.value = value;
    this.next = next;
  }

  @Override
  public SinglyLinkedListNoHeader<ValueType> addFirst(ValueType value) {
    return new SinglyLinkedListNoHeader<>(value, this);
  }

  @Override
  public SinglyLinkedListNoHeader<ValueType> addLast(ValueType value) {
    if (next == null) {
      next = new SinglyLinkedListNoHeader<>(value, null);
    } else {
      next.addLast(value);
    }
    return this;
  }

  @Override
  public SinglyLinkedListNoHeader<ValueType> remove(ValueType value) {
    if (value.equals(this.value)) {
      return this.next;
    } else if (this.next != null) {
      this.next = this.next.remove(value);
    }
    return this;
  }

  @Override
  public ValueType get(int index) {
    int i = 0;
    SinglyLinkedListNoHeader<ValueType> node = this;
    while (i++ < index && node != null) {
      node = node.next;
    }
    if (node == null) {
      return null;
    }
    return node.value;
  }


  public ValueType getRec(int index) {
    if (index == 0) {
      return value;
    }
    if (next == null) {
      return null;
    }
    return next.getRec(index - 1);
  }

  @Override
  public int size() {
    if(value == null) return 0;
    else if (next==null) return 1;
    else return 1 + (next.size());
  }

  @Override
  public boolean contains(ValueType value) {
    if (value.equals(this.value)) {
      return true;
    }

    if (this.next == null) {
      return false;
    }

    return this.next.contains(value);
  }

  public void print(){
    SinglyLinkedListNoHeader<ValueType> current = this;
    while (current != null){
      System.out.print(current.value + " ");
      current = current.next;
    }
    System.out.println();
  }
}
