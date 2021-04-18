package lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Ejercicio6_middleElementTest {

  @Test
  void testMiddleElement() {
    SinglyLinkedListNoHeader<Integer> list = new SinglyLinkedListNoHeader<>(3, null);
    list.addLast(5).addLast(9).addLast(2).addLast(4);

    assertEquals(9, Ejercicio6_middleElement.middleElement(list));
    list = list.remove(3);
    assertEquals(9, Ejercicio6_middleElement.middleElement(list));
    list = list.remove(5);
    assertEquals(2, Ejercicio6_middleElement.middleElement(list));
    list = list.remove(2);
    assertEquals(9, Ejercicio6_middleElement.middleElement(list));
  }
}