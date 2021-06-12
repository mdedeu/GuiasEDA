import com.sun.javafx.geom.Edge;

import java.util.*;

public class GraphGonza {
    Map<String,Node> nodes;
    boolean isDirected;

    public GraphGonza(boolean isDirected){
        this.isDirected=isDirected;
        nodes = new HashMap<>();
    }
    Node addNode(String label){
       return nodes.computeIfAbsent(label,(key)-> new Node(label));
    }
    void removeNode(String label){

    }
    void removeEdge(String label1, String label2){

    }
    void addEdge(String label1, String label2){
        Node node1 = addNode(label1);
        Node node2= addNode(label2);
        node1.edges.add(new WeightedEdge(node2,0));
        if(!isDirected){
            node2.edges.add(new WeightedEdge(node1,0));
        }

    }


    void printBfs(String startingLabel){
        Node startingNode =nodes.get(startingLabel);
        if(startingNode==null)
            return;
        Set<String> visited = new HashSet<>();

        List<Node> currentNodes = new LinkedList<>();
        List<Node> neighbours;
        currentNodes.add(startingNode);

        while(!currentNodes.isEmpty()){
            neighbours = new LinkedList<>();
            for(Node node: currentNodes){
                System.out.println(node.label);
                for(Edge edge : node.edges){
                    Node neighbourNode= edge.target;
                    if(!visited.contains(neighbourNode.label)){
                        visited.add(node.label);
                        neighbours.add(neighbourNode);
                    }
                }
            }
            currentNodes= neighbours;
        }

    }
    void printDfs(String startingLabel){
        Node startingNode =nodes.get(startingLabel);
        if(startingNode==null)
            return;
        Set<String> visited = new HashSet<>();
        visited.add(startingLabel);
        printDfsRec(startingNode,visited);



    }
    void printDfsRec(Node node, Set<String> visited){
        System.out.println(node.label);
        for (Edge edge : node.edges) {
            Node neighbour = edge.target;
            if(!visited.contains(neighbour.label)){
                visited.add(neighbour.label);
                printDfsRec(neighbour,visited);
            }
        }
    }
    void printDijkstra(String startingLabel){
        Node startingNode = nodes.get(startingLabel);
        PriorityQueue<PqNode> queue = new PriorityQueue<>();
        queue.add(new PqNode(startingNode,0));
        Set<String> visited = new HashSet<>();
        while(!queue.isEmpty()){
            PqNode pqnode = queue.remove();
            if(visited.contains(pqnode.node.label)){
                continue;
            }
            visited.add(pqnode.node.label);
            System.out.println(pqnode.node.label+ " "+ pqnode.weight);

            for(WeightedEdge edge : pqnode.node.edges){
                int totalCost= pqnode.weight + edge.weight;
                if(edge.target.minCost == null || totalCost < edge.target.minCost){
                    edge.target.minCost=totalCost;
                    queue.add(new PqNode(edge.target,totalCost));
                }
            }

        }

    }
    int nodeCount(){
        return nodes.size();
    }

    class PqNode implements Comparable<PqNode>{
        Node node;
        int weight;

        public PqNode(Node node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(PqNode o) {
            return Integer.compare(weight,o.weight);
        }
    }
    class Node{
        Set<WeightedEdge> edges = new HashSet<>();
        String label;
        Integer minCost = null;
        boolean visited=false;
        Node(String label){
            this.label=label;
        }
    }
    class Edge{
        Node target;
        Edge(Node target){
            this.target=target;
        }
    }
    class WeightedEdge extends Edge{
        int weight;

        WeightedEdge(Node target,int weight) {
            super(target);
            this.weight=weight;
        }
    }


}
