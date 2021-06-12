public class Ej5_Islands {


  public static int countIslands(boolean[][] map) {
    return createGraph(map).connectedComponents();

  }
  private static Graph createGraph(boolean[][] map){
    Graph g= new Graph(false);
    for(int i=0; i< map.length; i++){
      for (int j=0; j<map[i].length; j++){
        if(map[i][j]){
          Graph.Node n = new Graph.Node((Integer.toString(i)+","+Integer.toString(j)));
          g.addNode(n.label); //agrego el nodo de donde estoy parada
          if(i!=0){
            if(map[i-1][j]){ //si con el de arriba tiene true agrego la conexion
              Graph.Node n1 = new Graph.Node(Integer.toString(i-1)+","+Integer.toString(j));
              g.addNode(n1.label);
              g.addEdge(n.label, n1.label);
            }
          }
          if(j<map[i].length-1 && map[i][j+1]){ //si con el de la derecha tengo true agrego la conexion
            Graph.Node n2 = new Graph.Node(Integer.toString(i)+","+Integer.toString(j+1));
            g.addNode(n2.label);
            g.addEdge(n.label, n2.label);
          }
        }
      }
    }
    return g;
  }

  public static int biggestIsland(boolean[][] map) {
    Graph g = createGraph(map);
    int maxSize = 0;
    for (Graph.Node n : g.nodes.values()){
      if(maxSize < g.sizeOfConnectedComponent(n.label))
        maxSize = g.sizeOfConnectedComponent(n.label);
    }
    return maxSize;
  }
}

