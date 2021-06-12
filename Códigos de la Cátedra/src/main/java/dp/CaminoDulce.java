package dp;

public class CaminoDulce {
  public static int maxCandies(int[][] candies) {
  int[][]dp= new int [candies.length][candies.length];
  int max=0;
  for (int i=0; i<candies.length; i++){
    max=Math.max(max,maxCandiesRec(candies,dp,0,i));
  }
  return max;
  }
  private static int maxCandiesRec(int[][]candies, int[][]dp,int column, int row){
    if(column<0 || row<0 || column>= candies.length || row>= candies.length)
      return 0;
    if(dp[row][column]>0)
      return dp[row][column];
    dp[row][column]= candies[row][column] +Math.max(
            Math.max(
                    maxCandiesRec(candies,dp,column+1,row+1),
                    maxCandiesRec(candies,dp,column+1,row)),
            maxCandiesRec(candies,dp,column+1,row-1)
    );
    return dp[row][column];
  }

}
