public class Ejercicio8_swapKgroups {
  static SinglyLinkedListNoHeader<Integer> swapKgroups(SinglyLinkedListNoHeader<Integer> list, int k) {

    SinglyLinkedListNoHeader<Integer> fast = list;
    SinglyLinkedListNoHeader<Integer> slow = list;
    SinglyLinkedListNoHeader<Integer> ans = null;
    SinglyLinkedListNoHeader<Integer> chunk = null;
    //  [1, 4, 6, 2, 7, 3, 4, 5]

    while(fast != null) { // mientras haya elementos seguimos,

      int steps = 0;
      while (steps < k - 1 && fast != null) { // avanzo k veces el puntero fast
        steps++;
        fast = fast.next;
      }

      chunk = null;
      if(fast != null) { // hay k valores para invertir
        while(slow != fast.next) {
          if (chunk == null) {
            chunk = new SinglyLinkedListNoHeader<>(slow.value, null);
          }
          else {
            chunk = chunk.addFirst(slow.value);
          }
          slow = slow.next;
        }
        fast = fast.next;

      } else { // No hay k valores para invertir
        while(slow != null) {
          if (chunk == null) {
            chunk = new SinglyLinkedListNoHeader<>(slow.value, null);
          }
          else {
            chunk = chunk.addLast(slow.value);
          }
          slow = slow.next;
        }
      }
      if(ans == null)
        ans = chunk;
      else {
        while(chunk != null) {
          ans.addLast(chunk.value);
          chunk = chunk.next;
        }
      }
    }

    return ans;
  }
}
