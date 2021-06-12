package dp;

public class CoinChange {

  public static int coinChange(int[] coins, int amount) {
    Integer[] dp = new Integer[amount + 1];
    return coinChangeRec(coins, amount, dp);
  }

  private static int coinChangeRec(int[] coins, int amount, Integer[] dp) {
    if (amount == 0) {
      return 0;
    }
    if (amount < 0) {
      return -1;
    }

    if (dp[amount] != null) {
      return dp[amount];
    }

    Integer min = null;
    for(int i = 0; i < coins.length; i++) {
      int partialAmount = 1 + coinChangeRec(coins, amount - coins[i], dp);
      if (partialAmount > 0) {
        if (min == null) {
          min = partialAmount;
        } else {
          min = Math.min(min, partialAmount);
        }
      }
    }
    dp[amount] = min == null ? -1 : min;

    return dp[amount];
  }

}




//N
//X
//1 5 10
//17
//1 -> 16 ---> 3
//5 -> 12 ---> 3
//10 -> 7 ---> 3
//
//1 +