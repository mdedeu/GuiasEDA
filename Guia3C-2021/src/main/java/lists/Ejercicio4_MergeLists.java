package lists;

public class Ejercicio4_MergeLists {
  static SinglyLinkedListNoHeader<Integer> mergeLists(SinglyLinkedListNoHeader<Integer> list1,
                                                      SinglyLinkedListNoHeader<Integer> list2, int i, int j) {
    SinglyLinkedListNoHeader<Integer> aux = new SinglyLinkedListNoHeader<>(list1.value, null);
    SinglyLinkedListNoHeader<Integer> currentl1 = list1.next;
    for (int k = 1; k < i; k++) {
      aux.addLast(currentl1.value);
      currentl1 = list1.next;
    }
    SinglyLinkedListNoHeader<Integer> currentl2 = list2;
    while(currentl2 != null){
      aux.addLast(currentl2.value);
      currentl2 = currentl2.next;
    }
    for (int k = i; k < j + 1; k++) {
      currentl1 = currentl1.next;
    }
    while (currentl1 != null){
      aux.addLast(currentl1.value);
      currentl1 = currentl1.next;
    }
    return aux;
  }
}
