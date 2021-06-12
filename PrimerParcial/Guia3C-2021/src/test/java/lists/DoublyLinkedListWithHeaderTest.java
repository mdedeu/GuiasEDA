package lists;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static lists.ListMatchers.assertListsMatch;

class DoublyLinkedListWithHeaderTest {
  @Test
  void doublyLinkedListWorks() {
    DoublyLinkedListWithHeader<Integer> list = new DoublyLinkedListWithHeader<>();
    list.addFirst(3);
    list.addLast(4);
    list.addFirst(2);
    list.addLast(5);
    list.addFirst(1);
    list.remove(4);
    assertListsMatch(list, Arrays.asList(1, 2, 3, 5));
    list.remove(1);
    list.remove(3);
    list.remove(2);
    assertListsMatch(list, Collections.singletonList(5));
    list.remove(5);

    list.addLast(2);
    list.addLast(3);

    assertListsMatch(list, Arrays.asList(2, 3));
    list.remove(3);
  }

}