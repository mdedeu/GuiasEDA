package lists;

public interface LinkedListWithHeader<ValueType> {
  void addFirst(ValueType value);
  void addLast(ValueType value);
  ValueType remove(ValueType value);
  ValueType get(int index);
  int size();
  boolean contains(ValueType value);
}






