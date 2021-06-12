import java.util.*;

public class FlowGraph {
  Node source;
  Node sink;
  Map<String, Node> nodes;

  public FlowGraph(String source, String sink) {
    nodes = new HashMap<>();
    addNode(source);
    addNode(sink);
    this.source = nodes.get(source);
    this.sink = nodes.get(sink);
  }

  void addNode(String label) {
    nodes.putIfAbsent(label, new Node(label));
  }

  void addEdge(String label1, String label2, int weight) {
    Node node1 = nodes.get(label1);
    Node node2 = nodes.get(label2);
    if (node1 == null || node2 == null) {
      return;
    }
    node1.edges.put(label2, new Edge(node1, node2, weight, false));
  }

  public int maxFlow() {
    Map<Node, Node> path;
    while ((path = findPath()) != null) {
      int flow = findFlowInPath(path);

      Node node = this.sink;
      Node previousNode = path.get(node);
      Edge edge = previousNode.edges.get(node.label);
      edge.flow += flow;

      Edge reverseEdge = node.edges.getOrDefault( // computeIfAbsent
          previousNode.label,
          new Edge(edge.target, edge.source, 0, true)
      );
      reverseEdge.flow -= flow;
      node.edges.putIfAbsent(previousNode.label, reverseEdge);


      while(!previousNode.equals(source)) {
        node = previousNode;
        previousNode = path.get(node);
        edge = previousNode.edges.get(node.label);
        edge.flow += flow;

        reverseEdge = node.edges.getOrDefault(
            previousNode.label,
            new Edge(edge.target, edge.source, 0, true)
        );
        reverseEdge.flow -= flow;
        node.edges.put(previousNode.label, reverseEdge);
      }

    }

    int maxFlow = source.edges.values().stream().mapToInt(edge -> edge.flow).sum();
    return maxFlow;
  }

  Map<Node, Node> findPath() {
    Map<Node, Node> previousNodes = new HashMap<>();

    Queue<Node> queue = new LinkedList<>();
    Set<Node> visited = new HashSet<>();
    visited.add(source);
    queue.add(source);

    while (!queue.isEmpty()) {
      Node node = queue.remove();
      for (Edge edge : node.edges.values()) {
        if (edge.remainingCapacity() == 0 || visited.contains(edge.target)) continue;
        previousNodes.put(edge.target, node);
        if (edge.target.equals(sink)) {
          return previousNodes;
        }
        queue.add(edge.target);
        visited.add(edge.target);
      }
    }

    return null;
  }

  private int findFlowInPath(Map<Node, Node> path) {
    Node node = sink;
    Node previousNode = path.get(node);
    int minFlow = previousNode.edges.get(node.label).remainingCapacity();

    while(!previousNode.equals(source)) {
      node = previousNode;
      previousNode = path.get(node);

      minFlow = Math.min(minFlow, previousNode.edges.get(node.label).remainingCapacity());
    }

    return minFlow;
  }

  static class Node {
    String label;
    Map<String, Edge> edges;

    public Node(String label) {
      this.label = label;
      edges = new HashMap<>();
    }

    @Override
    public String toString() {
      return "label=" + label;
    }
  }

  static class Edge {
    Node source;
    Node target;
    int capacity;
    int flow;
    boolean isReverseEdge;

    public Edge(Node source, Node target, int capacity, boolean isReverseEdge) {
      this.source = source;
      this.target = target;
      this.capacity = capacity;
      this.isReverseEdge = isReverseEdge;
    }

    int remainingCapacity() {
      return capacity - flow;
    }
  }
}
