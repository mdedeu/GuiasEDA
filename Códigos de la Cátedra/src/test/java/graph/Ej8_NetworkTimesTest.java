package graph;

import org.junit.jupiter.api.Test;

public class Ej8_NetworkTimesTest {

  @Test
  void canFinishDegree() {
    int[][] times = new int[][] {
        new int[] { 2, 1, 1 },
        new int[] { 2, 3, 1 },
        new int[] { 3, 4, 1 }
    };

    int delayTime = Ej8_NetworkTimes.delayTime(4, times, 2);
//    assertEquals(2, delayTime);
  }
}
