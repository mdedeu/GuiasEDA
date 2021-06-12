package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaminoDulceTest {
  @Test
  void maxCandies() {
    int[][] candies = new int[][] {
        new int[] { 2, 4, 1, 3 },
        new int[] { 5, 1, 2, 4 },
        new int[] { 2, 2, 3, 2 },
        new int[] { 1, 7, 1, 3 },
    };
    int maxCandies = CaminoDulce.maxCandies(candies);
    assertEquals(maxCandies, 16);
  }

}