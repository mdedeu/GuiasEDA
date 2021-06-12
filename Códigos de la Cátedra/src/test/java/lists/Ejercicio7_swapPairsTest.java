package lists;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lists.ListMatchers.assertListsMatch;


class Ejercicio7_swapPairsTest {

  @Test
  void testMiddleElement() {
    LinkedListWithHeader<Integer> list = new DoublyLinkedListWithHeader<>();
    list.addLast(3);
    list.addLast(4);
    list.addLast(2);
    list.addLast(5);
    list.addLast(1);
    list.addLast(4);

    LinkedListWithHeader<Integer> answer = Ejercicio7_swapPairs.swapPairs(list);
    assertListsMatch(answer, Arrays.asList(4, 3, 5, 2, 4, 1));
  }
}