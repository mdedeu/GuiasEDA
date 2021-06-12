package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeTest {

  @Test
  void testCoinChange() {
    assertEquals(3, CoinChange.coinChange(new int[]{1, 2, 5}, 11));
    assertEquals(-1, CoinChange.coinChange(new int[]{2}, 3));
  }
}