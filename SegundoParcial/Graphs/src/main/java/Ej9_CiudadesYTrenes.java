

import java.util.*;

public class Ej9_CiudadesYTrenes {

    public static int trainsToClose(int N, int[][] roads, int[][] inputTrains) {
        Set<Integer> usedTrains = new HashSet<>();
        Map<Integer, List<Edge>> nodes = new HashMap<>();
        // Setea los nodos y aristas del grafo (undirected)
        for (int i = 1; i <= N; i++)
            nodes.put(i, new ArrayList<>());
        for (int i = 0; i < roads.length; i++) {
            nodes.get(roads[i][0]).add(new Edge(roads[i][1], roads[i][2], null));
            nodes.get(roads[i][1]).add(new Edge(roads[i][0], roads[i][2], null));
        }
        for (int i = 0; i < inputTrains.length; i++) {
            nodes.get(1).add(new Edge(inputTrains[i][0], inputTrains[i][1], i));
            nodes.get(inputTrains[i][0]).add(new Edge(1, inputTrains[i][1], i));
        }
        // Calculo los caminos minimos hasta cada ciudad desde la capital y guardo los trenes necesarios para ellos
        dijkstra(1, nodes, usedTrains);
        return inputTrains.length - usedTrains.size();
    }

    private static void dijkstra(int start, Map<Integer, List<Edge>> nodes, Set<Integer> usedTrains) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(start, 0, null));
        Set<Integer> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Edge path = queue.remove();
            if (visited.contains(path.target))
                continue;
            // Es el camino mas optimo, guardo el tren necesario
            if (path.train != null)
                usedTrains.add(path.train);

            visited.add(path.target);
            for (Edge edge : nodes.get(path.target)) {
                int totalCost = path.weight + edge.weight;
                if (!distances.containsKey(edge.target) || totalCost < distances.get(edge.target) || (totalCost == distances.get(edge.target) && edge.train == null)) {
                    distances.put(edge.target, totalCost);
                    queue.add(new Edge(edge.target, totalCost, edge.train));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int target;
        int weight;
        Integer train;

        public Edge(int target, int weight, Integer train) {
            this.target = target;
            this.weight = weight;
            this.train = train;
        }

        @Override
        public int compareTo(Edge o) {
            int c = Integer.compare(weight, o.weight);
            // Si los caminos tienen el mismo costo, desempata en base a cuál no usa train
            if(c == 0) {
                if (train == null)
                    c = -1;
                else
                    c = 1;
            }
            return c;
        }
    }

}
