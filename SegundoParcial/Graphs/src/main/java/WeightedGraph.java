import java.util.*;

public class WeightedGraph{

    private final boolean isDirected;
    Map<String, Node> nodes;

    public WeightedGraph(boolean isDirected) {
        this.isDirected = isDirected;
        nodes = new HashMap<>();
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
        node1.edges.add(new Edge(node2, weight));
        if (!isDirected) {
            node2.edges.add(new Edge(node1, weight));
        }
    }

    public void printDijkstra(String startingLabel) {
        nodes.values().forEach(node -> node.minCost = null);

        Node startingNode = nodes.get(startingLabel);
        PriorityQueue<PqNode> queue = new PriorityQueue<>();
        queue.add(new PqNode(startingNode, 0));
        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            PqNode pqNode = queue.remove();
            if (visited.contains(pqNode.node.label)) {
                continue;
            }
            visited.add(pqNode.node.label);
            System.out.println(pqNode.node.label + " " + pqNode.weight);

            for (Edge edge : pqNode.node.edges) {
                int totalCost = pqNode.weight + edge.weight;
                if (edge.target.minCost == null || totalCost < edge.target.minCost) {
                    edge.target.minCost = totalCost;
                    queue.add(new PqNode(edge.target, totalCost));
                }
            }
        }
    }

    class PqNode implements Comparable<PqNode> {
        Node node;
        int weight;

        public PqNode(Node node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(PqNode o) {
            return Integer.compare(weight, o.weight);
        }
    }

    class Node {
        String label;
        Set<Edge> edges;
        Integer minCost = null;

        public Node(String label) {
            this.label = label;
            edges = new HashSet<>();
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

    class Edge {
        Node target;
        int weight;

        public Edge(Node target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

}