package lists;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lists.ListMatchers.assertListsMatch;

class Ejercicio8_swapKgroupsTest {

  @Test
  void testSwapKgroups() {
    SinglyLinkedListNoHeader<Integer> list = new SinglyLinkedListNoHeader<>(1, null);
    list.addLast(4).addLast(6).addLast(2).addLast(7).addLast(3).addLast(4).addLast(5);

    SinglyLinkedListNoHeader<Integer> answer = Ejercicio8_swapKgroups.swapKgroups(list, 3);

    assertListsMatch(answer, Arrays.asList(6, 4, 1, 3, 7, 2, 4, 5));
  }
}