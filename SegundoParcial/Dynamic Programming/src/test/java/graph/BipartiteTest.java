package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BipartiteTest {

  @Test
  void testIsBipartite() {
    Graph graph = new Graph( false);
    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");

    graph.addNode("D");
    graph.addNode("E");
    graph.addNode("F");

    graph.addEdge("A", "D");
    graph.addEdge("A", "E");
    graph.addEdge("B", "D");
    graph.addEdge("B", "F");
    graph.addEdge("C", "F");

    assertTrue(graph.isBipartite());
  }

  @Test
  void testIsNotBipartite() {
    Graph graph = new Graph( false);
    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");

    graph.addNode("D");
    graph.addNode("E");
    graph.addNode("F");

    graph.addEdge("A", "D");
    graph.addEdge("A", "E");
    graph.addEdge("B", "D");
    graph.addEdge("B", "F");
    graph.addEdge("C", "F");

    graph.addEdge("A", "C");

    assertFalse(graph.isBipartite());
  }

  @Test
  void testIsNotBipartite2() {
    Graph graph = new Graph( false);
    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");

    graph.addNode("D");
    graph.addNode("E");
    graph.addNode("F");

    graph.addEdge("A", "D");
    graph.addEdge("A", "E");
    graph.addEdge("B", "D");
    graph.addEdge("B", "F");
    graph.addEdge("C", "F");

    graph.addEdge("E", "F");

    assertFalse(graph.isBipartite());
  }

}
