package lists;

public class Ejercicio2_Queue {
  DoublyLinkedListWithHeader<Integer> queue = new DoublyLinkedListWithHeader<>();
  void queue(int value) {
    queue.addLast(value);
  }

  int dequeue() {
    return queue.remove(queue.get(0));
  }

  public DoublyLinkedListWithHeader<Integer> getQueue() {
    return queue;
  }
}
