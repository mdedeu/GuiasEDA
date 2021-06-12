package lists;

public interface CircularList<ValueType> {
  boolean addAfter(ValueType after, ValueType valueToAdd);
}
