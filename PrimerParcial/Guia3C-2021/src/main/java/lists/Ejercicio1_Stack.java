package lists;


public class Ejercicio1_Stack {
  private SinglyLinkedListNoHeader<Integer> stack = new SinglyLinkedListNoHeader<>(null, null);
  void push(int value) {
    stack = stack.addFirst(value);
  }

  int pop() {
    int toReturn = stack.getRec(0);
    stack = stack.remove(toReturn);
    return toReturn;
  }

  public SinglyLinkedListNoHeader<Integer> getStack() {
    return stack;
  }

}
