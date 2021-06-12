package lists;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import java.util.Collections;

import static lists.ListMatchers.assertListsMatch;
import static org.junit.jupiter.api.Assertions.*;

class Ejercicio1_StackTest {

  @Test
  void testStack() {
    Ejercicio1_Stack stack = new Ejercicio1_Stack();
    stack.push(5);
    stack.push(4);
    stack.push(6);
    //es mas eficiente que el ultime elemneto este al principio
    assertListsMatch(stack.getStack(), Arrays.asList(6, 4, 5));
    assertEquals(6, stack.pop());
    assertEquals(4, stack.pop());
    stack.push(7);
    assertEquals(7, stack.pop());
    assertEquals(5, stack.pop());
    stack.push(1);
    stack.push(2);
    stack.push(1);
    assertListsMatch(stack.getStack(), Arrays.asList(1, 2, 1));
    assertEquals(1, stack.pop());
    assertListsMatch(stack.getStack(), Arrays.asList(2, 1));
    assertEquals(2, stack.pop());
    assertEquals(1, stack.pop());
  }
}
