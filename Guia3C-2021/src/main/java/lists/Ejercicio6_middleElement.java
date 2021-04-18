package lists;

public class Ejercicio6_middleElement {

  private static int size;
  static int middleElement(SinglyLinkedListNoHeader<Integer> list) {
    SinglyLinkedListNoHeader<Integer> pointer1 = list;
    SinglyLinkedListNoHeader<Integer> pointer2 = list;
    while (pointer1.next != null && pointer1.next.next != null){
      pointer1 = pointer1.next.next;

      pointer2 = pointer2.next;
    }
    return pointer2.value;
  }
}
