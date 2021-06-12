package lists;

public interface LinkedListNoHeader<ValueType> {
  LinkedListNoHeader<ValueType> addFirst(ValueType value);
  LinkedListNoHeader<ValueType> addLast(ValueType value);
  LinkedListNoHeader<ValueType> remove(ValueType value);
  ValueType get(int index);
  int size();
  boolean contains(ValueType value);
}




