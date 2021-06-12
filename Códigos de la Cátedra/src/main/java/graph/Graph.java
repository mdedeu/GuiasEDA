package graph;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Graph {

  private final boolean isDirected;
  Map<String, Node> nodes;

  public Graph(boolean isDirected) {
    this.isDirected = isDirected;
    nodes = new HashMap<>();
  }

  void addNode(String label) {
    nodes.putIfAbsent(label, new Node(label));
  }

  public void removeNode(String label) {
    Node node = nodes.remove(label);
    if (node == null) {
      return;
    }
    if (!isDirected) {
      for (Node edgeNode : node.edges) {
        edgeNode.removeEdgeTo(node);
      }
    }

  }

  void addEdge(String label1, String label2) {
    Node node1 = nodes.get(label1);
    Node node2 = nodes.get(label2);
    if (node1 == null || node2 == null) {
      return;
    }
    node1.edges.add(node2);
    if (!isDirected) {
      node2.edges.add(node1);
    }
  }

  void removeEdge(String label1, String label2) {
    Node node1 = nodes.get(label1);
    Node node2 = nodes.get(label2);
    node1.removeEdgeTo(node2);
    node2.removeEdgeTo(node1);
  }

  void printBfs(String startingLabel) {
    unmarkAllNodes();

    Queue<Node> nodesToVisit = new LinkedList<>();
    nodesToVisit.add(nodes.get(startingLabel));

    while (!nodesToVisit.isEmpty()) {
      Node current = nodesToVisit.remove();
      if (!current.marked) {
        current.mark();
        System.out.println(current.label);
        for (Node edgeNode : current.edges) {
          if (!edgeNode.marked) {
            nodesToVisit.add(edgeNode);
          }
        }
      }
    }
  }

  void printDfs(String startingLabel) {
    unmarkAllNodes();
    printDfsRec(nodes.get(startingLabel));
  }

  private void printDfsRec(Node node) {
    markDfs(node, markedNode -> System.out.println(markedNode.label));
  }

  private void markDfs(Node node) {
    markDfs(node, markedNode -> {});
  }

  private void markDfs(Node node, Consumer<Node> onMark) {
    if (node.marked) {
      return;
    }
    node.mark();
    onMark.accept(node);

    for (Node edgeNode : node.edges) {
      if (!edgeNode.marked) {
        markDfs(edgeNode, onMark);
      }
    }
  }

  private void unmarkAllNodes() {
    nodes.values().forEach(Node::unmark);
  }

  public int inDegree(String label) {
    int degree = 0;
    for (Node node : nodes.values()) {
      for (Node edgeNode : node.edges) {
        if (edgeNode.label.equals(label)) {
          degree++;
        }
      }
    }
    return degree;
  }

  public int outDegree(String label) {
    return nodes.get(label).edges.size();
  }

  public int connectedComponents() {
    unmarkAllNodes();
    int components = 0;

    for (Node node : nodes.values()) {
      if (!node.marked) {
        components++;
        markDfs(node);
      }
    }

    return components;
  }

  List<List<String>> getAllPaths(String from, String to) {
    unmarkAllNodes();

    Stack<String> currentPath = new Stack<>();
    currentPath.add(from);
    List<List<String>> ans = new ArrayList<>();
    getAllPathsRec(nodes.get(from), nodes.get(to), currentPath, ans);
    return ans;
  }

  void getAllPathsRec(Node currentNode, Node targetNode,
                      Stack<String> currentPath, List<List<String>> possiblePaths) {
    if (!currentPath.isEmpty() && currentPath.peek().equals(targetNode.label)) {
      possiblePaths.add(new ArrayList<>(currentPath));
      return;
    }
    currentNode.mark();
    for (Node edgeNode : currentNode.edges) {
      if (!edgeNode.marked) {
        currentPath.push(edgeNode.label);
        getAllPathsRec(edgeNode, targetNode, currentPath, possiblePaths);
        currentPath.pop();
      }
    }
    currentNode.unmark();
  }

  boolean isBipartite() {
    Map<Node, Integer> colors = new HashMap<>();

    for (Node startingNode : nodes.values()) {
      if (colors.containsKey(startingNode)) continue;

      colors.put(startingNode, 0);
      Queue<Node> queue = new LinkedList<>();
      queue.add(startingNode);

      while (!queue.isEmpty()) {
        Node node = queue.remove();
        Integer currentColor = colors.get(node);

        for (Node nextNode : node.edges) {
          if (!colors.containsKey(nextNode)) {
            colors.put(nextNode, 1 - currentColor);
            queue.add(nextNode);
          } else if (colors.get(nextNode).equals(currentColor)) {
            return false;
          }
        }
      }
    }

    return true;
  }

  static class Node {
    String label;
    Set<Node> edges;
    boolean marked;

    public Node(String label) {
      this.label = label;
      edges = new HashSet<>();
    }

    void mark() {
      marked = true;
    }

    void unmark() {
      marked = false;
    }

    void removeEdgeTo(Node toNode) {
      edges = edges.stream()
          .filter(edge -> !edge.equals(toNode))
          .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node) o;
      return Objects.equals(label, node.label);
    }

    @Override
    public int hashCode() {
      return Objects.hash(label);
    }
  }

}
