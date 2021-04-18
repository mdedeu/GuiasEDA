package lists;

public class Ejercicio6_middleElement {

  private static int size;
  static int middleElement(SinglyLinkedListNoHeader<Integer> list) {
    size = 0;
    return rec(list).value;
  }

  private static Pair rec(SinglyLinkedListNoHeader<Integer> list){
    if(list == null)
      return new Pair(0, null);
    size++;
    Pair ret = rec(list.next);
    if(ret.value != null)
      return ret;
    if(ret.idx == size/2)
      return new Pair(ret.idx, list.value);
    return ret.incIdx();
  }
  private static class Pair{
    int idx;
    Integer value;

    public Pair(int idx, Integer value) {
      this.idx = idx;
      this.value = value;
    }

    public Pair incIdx(){
      idx++;
      return this;
    }
  }
}
