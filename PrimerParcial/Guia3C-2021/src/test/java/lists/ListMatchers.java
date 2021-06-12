package lists;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListMatchers {
  static <ValueType> void assertListsMatch(LinkedListNoHeader<ValueType> list1, List<ValueType> list2) {
    assertEquals(list1.size(), list2.size());
    for (int i = 0; i < list1.size(); i++) {
      assertEquals(list1.get(i), list2.get(i));
    }
  }

  static <ValueType> void assertListsMatch(LinkedListWithHeader<ValueType> list1, List<ValueType> list2) {
    assertEquals(list1.size(), list2.size());
    for (int i = 0; i < list1.size(); i++) {
      assertEquals(list1.get(i), list2.get(i));
    }
  }
}
