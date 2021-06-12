package lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ejercicio2_QueueTest {

  @Test
  void testQueue() {
    Ejercicio2_Queue stack = new Ejercicio2_Queue();
    stack.queue(5);
    stack.queue(4);
    stack.queue(6);
    assertEquals(5, stack.dequeue());
    assertEquals(4, stack.dequeue());
    stack.queue(7);
    assertEquals(6, stack.dequeue());
    assertEquals(7, stack.dequeue());
  }
}
