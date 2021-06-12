package graph;

import org.junit.jupiter.api.Test;

class GraphTest {

//  Otros ejercicios con Dijkstra:
//  https://codeforces.com/contest/715/problem/B
//  https://codeforces.com/contest/59/problem/E
//  https://codeforces.com/contest/567/problem/E
//  https://www.hackerrank.com/challenges/synchronous-shopping/problem

  @Test
  void testBfs() {
    Graph graph = TestGraphFactory.undirectedUnweightedGraph();
    graph.printBfs("A");
  }

  @Test
  void testDfs() {
    Graph graph = TestGraphFactory.undirectedUnweightedGraph();
    graph.printDfs("A");
  }

  @Test
  void testDijkstra() {
    WeightedGraph graph = TestGraphFactory.directedWeightedGraph();
    graph.printDijkstra("A");
  }

  @Test
  void testDenseBfs() {
    DenseGraph graph = TestGraphFactory.denseGraph();
    graph.printBfs("A");
  }

}