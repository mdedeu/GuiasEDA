package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxFlowTest {

  @Test
  void testMaxFlow1() {
    FlowGraph graph = new FlowGraph("A", "D");
    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");
    graph.addNode("D");

    graph.addEdge("A", "B", 5);
    graph.addEdge("A", "C", 3);
    graph.addEdge("B", "C", 2);
    graph.addEdge("B", "D", 2);
    graph.addEdge("C", "D", 4);

    assertEquals(6, graph.maxFlow());
  }

  @Test
  void testMaxFlow2() {
    FlowGraph graph = new FlowGraph("A", "F");
    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");
    graph.addNode("D");
    graph.addNode("E");
    graph.addNode("F");

    graph.addEdge("A", "B", 16);
    graph.addEdge("A", "C", 13);
    graph.addEdge("B", "C", 10);
    graph.addEdge("C", "B", 4);
    graph.addEdge("B", "D", 12);
    graph.addEdge("D", "C", 9);
    graph.addEdge("C", "E", 14);
    graph.addEdge("E", "D", 7);
    graph.addEdge("D", "F", 20);
    graph.addEdge("E", "F", 4);

    assertEquals(23, graph.maxFlow());
  }
}
