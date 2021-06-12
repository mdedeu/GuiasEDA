package lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Ejercicio3_CircularMaxTest {

  @Test
  void getMaxFromList() {
    Ejercicio3_IntCircularList list = new Ejercicio3_IntCircularList(5);
    list.addAfter(5, 7);
    list.addAfter(7, 3);
    list.addAfter(3, 9);
    list.addAfter(5, 20);
    assertEquals(20, list.getMax());
  }
}