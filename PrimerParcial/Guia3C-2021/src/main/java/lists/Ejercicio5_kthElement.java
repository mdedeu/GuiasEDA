package lists;

public class Ejercicio5_kthElement {
  static int kthElementFromBack(SinglyLinkedListNoHeader<Integer> list, int k) {
    SinglyLinkedListNoHeader<Integer> pointer1 = list;
    for (int i = 0; i < k; i++) {
      pointer1 = pointer1.next;
    }
    SinglyLinkedListNoHeader<Integer> pointer2 = list;
    while (pointer1.next != null){
      pointer1 = pointer1.next;
      pointer2 = pointer2.next;
    }
    return pointer2.value;
  }
}
