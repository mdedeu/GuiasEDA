package lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ejercicio1_StackTest {

  @Test
  void testStack() {
    Ejercicio1_Stack stack = new Ejercicio1_Stack();
    stack.push(5);
    stack.push(4);
    stack.push(6);
    assertEquals(6, stack.pop());
    assertEquals(4, stack.pop());
    stack.push(7);
    assertEquals(7, stack.pop());
    assertEquals(5, stack.pop());
  }
}