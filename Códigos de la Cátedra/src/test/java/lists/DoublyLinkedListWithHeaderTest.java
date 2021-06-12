package lists;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lists.ListMatchers.assertListsMatch;

class DoublyLinkedListWithHeaderTest {
  @Test
  void doublyLinkedListWorks() {
    LinkedListWithHeader<Integer> list = new DoublyLinkedListWithHeader<>();
    list.addFirst(3);
    list.addLast(4);
    list.addFirst(2);
    list.addLast(5);
    list.addFirst(1);
    list.remove(4);
    assertListsMatch(list, Arrays.asList(1, 2, 3, 5));
  }

}