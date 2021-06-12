package graph;

public class Ej5_Islands {

  public static int countIslands(boolean[][] map) {
    int islands = 0;
    boolean[][] marked = new boolean[map.length][map[0].length];

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] && !marked[i][j]) {
          islands++;
          markIsland(map, marked, i, j);
        }
      }
    }

    return islands;
  }

  public static int biggestIsland(boolean[][] map) {
    int biggestIsland = 0;
    boolean[][] marked = new boolean[map.length][map[0].length];

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] && !marked[i][j]) {
          biggestIsland = Math.max(biggestIsland, markIsland(map, marked, i, j));
        }
      }
    }

    return biggestIsland;
  }

  private static int markIsland(boolean[][] map, boolean[][] marked, int i, int j) {
    if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || marked[i][j] || !map[i][j]) {
      return 0;
    }
    marked[i][j] = true;
    return markIsland(map, marked, i + 1, j)
        + markIsland(map, marked, i - 1, j)
        + markIsland(map, marked, i, j - 1)
        + markIsland(map, marked, i, j + 1)
        + 1;
  }

}
