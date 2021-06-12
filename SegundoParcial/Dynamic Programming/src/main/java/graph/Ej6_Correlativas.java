package graph;

public class Ej6_Correlativas {

  public static boolean canFinishDegree(int N, int[][] correlativas) {
    Graph graph = new Graph(true);
    for (int i = 1; i <= N; i++) {
      graph.addNode(String.valueOf(i));
    }
    for (int[] correlativa : correlativas) {
      graph.addEdge(String.valueOf(correlativa[0]), String.valueOf(correlativa[1]));
    }
    for (int i = 1; i <= N; i++) {
      String label = String.valueOf(i);
      if (findCycleDfs(graph.nodes.get(label))) {
        return false;
      }
    }
    return true;
  }

  private static boolean findCycleDfs(Graph.Node node) {
    node.mark();
    for (Graph.Node edgeNode : node.edges) {
      if (edgeNode.marked) {
        return true;
      }
      if (findCycleDfs(edgeNode)) {
        return true;
      }
    }
    node.unmark();
    return false;
  }
}
