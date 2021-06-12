import java.util.*;

public class Graph {

  private final boolean isDirected;
  Map<String, Node> nodes;
  private Map<String,Integer> inDegrees= new HashMap<>();
  private Map<String,Integer> outDegrees= new HashMap<>();
  public Graph(boolean isDirected) {
    this.isDirected = isDirected;
    nodes = new HashMap<>();
  }

  void addNode(String label) {
    nodes.putIfAbsent(label, new Node(label));
    inDegrees.put(label,0);
    outDegrees.put(label,0);
  }

  public void removeNode(String label) {
    Node toBeRemoved = nodes.get(label);
    if(toBeRemoved== null)
      return;
    for(String aux : nodes.keySet()){
        removeEdge(label,aux);
    }
    nodes.remove(label);
  }

  void addEdge(String label1, String label2) {
    Node node1 = nodes.get(label1);
    Node node2 = nodes.get(label2);
    if (node1 == null || node2 == null) {
      return;
    }
    node1.edges.add(node2);

    int newDegree=inDegrees.getOrDefault(label2,0);
    newDegree+=1;
    inDegrees.put(label2,newDegree);

    int outNewDegree=outDegrees.getOrDefault(label1,0);
    outNewDegree+=1;
    outDegrees.put(label1,outNewDegree);

    if (!isDirected) {
      node2.edges.add(node1);

      int newDegreeSource=inDegrees.getOrDefault(label1,0);
      newDegreeSource+=1;
      inDegrees.put(label1,newDegreeSource);

      int outNewDegree2=outDegrees.getOrDefault(label2,0);
      outNewDegree2+=1;
      outDegrees.put(label2,outNewDegree2);


    }
  }
  public int sizeOfConnectedComponent(String startingLabel) {
    unmarkAllNodes();
    return sizeOfConnectedComponentRec(nodes.get(startingLabel));
  }

  private int sizeOfConnectedComponentRec(Node node) {
    if (node.marked) {
      return 1;
    }
    node.mark();
    int aux = 1;
    for (Node edgeNode : node.edges) {
      if (!edgeNode.marked) {
        aux += sizeOfConnectedComponentRec(edgeNode);
      }
    }
    return aux;
  }
  void removeEdge(String label1, String label2) {
    Node node1 = nodes.get(label1);
    Node node2= nodes.get(label2);

    node1.edges.remove(node2);
    node2.edges.remove(node1);


    int outDegree1= outDegrees.getOrDefault(label1,-1);
    int inDegree2= inDegrees.getOrDefault(label2,-1);

    if(outDegree1>0){
      outDegrees.put(label1,outDegree1-1);
    }
    if(inDegree2>0){
      inDegrees.put(label2,inDegree2-1);
    }

    if(!isDirected){
      int outDegree2= outDegrees.getOrDefault(label2,-1);
      int inDegree1= inDegrees.getOrDefault(label1,-1);
      if(outDegree2>0){
        outDegrees.put(label2,outDegree2-1);
      }
      if(inDegree1>0){
        inDegrees.put(label1,inDegree1-1);
      }
    }



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
    if (node.marked) {
      return;
    }
    node.mark();
    System.out.println(node.label);

    for (Node edgeNode : node.edges) {
      if (!edgeNode.marked) {
        printDfsRec(edgeNode);
      }
    }
  }

  private void unmarkAllNodes() {
    nodes.values().forEach(Node::unmark);
  }

  public int inDegree(String label) {
    return inDegrees.getOrDefault(label,-1);
  }

  public int outDegree(String label) {
    return outDegrees.getOrDefault(label,-1);

  }
  public void searchDfs(String startingLabel) {
    unmarkAllNodes();
    searchDfsRec(nodes.get(startingLabel));
  }

  private void searchDfsRec(Node node) {
    if (node.marked) {
      return;
    }
    node.mark();
    System.out.println(node.label);

    for (Node edgeNode : node.edges) {
      if (!edgeNode.marked) {
        printDfsRec(edgeNode);
      }
    }
  }
  public int connectedComponents() {
    unmarkAllNodes();
    int components=0;
    for(Node node : nodes.values()){
      if(!node.marked){
        connectedComponentsRec(node);
        components+=1;
      }
    }
    return components;
  }
  private void connectedComponentsRec(Node startingNode){
    if(startingNode.marked)
      return ;
    startingNode.mark();
    for (Node edgeNode : startingNode.edges) {
      if (!edgeNode.marked) {
        connectedComponentsRec(edgeNode);
      }
    }
  }


  List<List<String>> getAllPaths(String from, String to) {
    List<List<String>> parameter = new ArrayList<>();
    Node n = nodes.getOrDefault(from,new Node(from));
    List<String> aux = new ArrayList<>();
    rec(n,to,parameter,aux);
    return parameter;
  }
  private void rec(Node node,String to,List<List<String>> parameter,List<String> myList){
    if(node.label.equals(to)){
      myList.add(to);
      parameter.add(new ArrayList<>(myList));
      return ;
    }
    node.mark();
    List<String> aux = new ArrayList<>(myList);
    for (Node n: node.edges){
      if(!n.marked) {
        myList.add(node.label);
        rec(n,to,parameter,myList);
        myList=aux;

      }
    }
    node.unmark();
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

    @Override
    public String toString() {
      return "Node{" +
          "label='" + label + '\'' +
          '}';
    }
  }

}
