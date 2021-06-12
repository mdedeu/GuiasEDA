package graph;

import java.util.*;

public class Ej9_CiudadesYTrenes {

  public static int trainsToClose(int N, int[][] roads, int[][] inputTrains) {
    Map<Integer, City> cities = new HashMap<>();
    List<Edge> trains = new ArrayList<>();
    for (int i = 1; i <= N; i++) {
      cities.put(i, new City(i));
    }
    for (int[] road : roads) {
      City city1 = cities.get(road[0]);
      City city2 = cities.get(road[1]);
      city1.roads.add(new Edge(city2, road[2]));
      city2.roads.add(new Edge(city1, road[2]));
    }
    for (int[] train : inputTrains) {
      trains.add(new Edge(cities.get(train[0]), train[1]));
    }

    City city1 = cities.get(1);
    city1.visited = true;
    PriorityQueue<PqNode> queue = new PriorityQueue<>();
    for (Edge road : city1.roads) {
      queue.add(new PqNode(road.target, road.length));
    }
    for (Edge train : trains) {
      queue.add(new PqNode(train.target, train.length, train));
    }
    while (!queue.isEmpty()) {
      PqNode node = queue.remove();
      if (node.city.visited) continue;
      node.city.visited = true;
      if (node.startingTrain != null) {
        node.startingTrain.needed = true;
      }
      for (Edge road : node.city.roads) {
        int tripCost = node.cost + road.length;
        if (tripCost < road.target.bestDistance) {
          node.city.bestDistance = tripCost;
          queue.add(new PqNode(road.target, tripCost, node.startingTrain));
        }
      }
    }

    return (int) trains.stream().filter(train -> !train.needed).count();
  }

  static class PqNode implements Comparable<PqNode> {
    City city;
    int cost;
    Edge startingTrain;

    public PqNode(City city, int cost) {
      this(city, cost, null);
    }

    public PqNode(City city, int cost, Edge startingTrain) {
      this.city = city;
      this.cost = cost;
      this.startingTrain = startingTrain;
    }

    @Override
    public int compareTo(PqNode other) {
      int compare = Integer.compare(cost, other.cost);
      if (compare != 0) return compare;
      if (startingTrain != null && other.startingTrain == null) return 1;
      if (startingTrain == null && other.startingTrain != null) return -1;
      return 0;
    }
  }

  static class City {
    public int id;
    public int bestDistance = Integer.MAX_VALUE;
    Set<Edge> roads = new HashSet<>();
    boolean visited;

    public City(int id) {
      this.id = id;
    }
  }

  static class Edge {
    City target;
    int length;
    boolean needed;

    void markNeeded() {
      needed = true;
    }

    public Edge(City target, int length) {
      this.target = target;
      this.length = length;
    }
  }

}
