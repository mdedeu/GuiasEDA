package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class DenseGraph {

  Map<String, Integer> indexes = new HashMap<>();
  Map<Integer, String> labels = new HashMap<>();
  boolean[][] matrix;
  int size;

  boolean isDirected;

  public DenseGraph(boolean isDirected) {
    this.isDirected = isDirected;
    matrix = new boolean[1][1];
  }

  void addNode(String label) {
    size++;
    if (size > matrix.length) {
      boolean[][] newMatrix = new boolean[matrix.length * 2][matrix.length * 2];
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix.length; j++) {
          newMatrix[i][j] = matrix[i][j];
        }
      }
      matrix = newMatrix;
    }
    indexes.put(label, size - 1);
    labels.put(size - 1, label);
  }

  void addEdge(String label1, String label2) {
    int index1 = indexes.get(label1);
    int index2 = indexes.get(label2);
    matrix[index1][index2] = true;
    if (!isDirected) {
      matrix[index2][index1] = true;
    }
  }

  void printBfs(String startingLabel) {
    boolean[] visited = new boolean[size];
    int startingIndex = indexes.get(startingLabel);

    Queue<Integer> queue = new LinkedList<>();
    queue.add(startingIndex);

    while (!queue.isEmpty()) {
      Integer index = queue.remove();
      if (visited[index]) continue;
      System.out.println(labels.get(index));
      visited[index] = true;
      for (int i = 0; i < size; i++) {
        if (matrix[index][i]) {
          queue.add(i);
        }
      }
    }
  }

}
