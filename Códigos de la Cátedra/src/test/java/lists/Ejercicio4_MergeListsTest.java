package lists;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lists.ListMatchers.assertListsMatch;

class Ejercicio4_MergeListsTest {

  @Test
  void testListMerging() {
    SinglyLinkedListNoHeader<Integer> list1 = new SinglyLinkedListNoHeader<>(3, null);
    list1.addLast(5).addLast(9).addLast(2).addLast(4);
    SinglyLinkedListNoHeader<Integer> list2 = new SinglyLinkedListNoHeader<>(10, null);
    list2.addLast(13).addLast(11);

    SinglyLinkedListNoHeader<Integer> answer = Ejercicio4_MergeLists.mergeLists(list1, list2, 1, 3);
    assertListsMatch(answer, Arrays.asList(3, 10, 13, 11, 4));
  }
}