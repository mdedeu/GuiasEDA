package lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Ejercicio5_kthElementTest {

  @Test
  void testKthElement() {
    SinglyLinkedListNoHeader<Integer> list = new SinglyLinkedListNoHeader<>(3, null);
    list.addLast(5).addLast(9).addLast(2).addLast(4);

    assertEquals(4, Ejercicio5_kthElement.kthElementFromBack(list, 0));
    assertEquals(2, Ejercicio5_kthElement.kthElementFromBack(list, 1));
    assertEquals(9, Ejercicio5_kthElement.kthElementFromBack(list, 2));
    assertEquals(5, Ejercicio5_kthElement.kthElementFromBack(list, 3));
    assertEquals(3, Ejercicio5_kthElement.kthElementFromBack(list, 4));
  }
}