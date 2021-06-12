import java.util.*;

public class Ej6_CorrelativasGonza {
    //OPCION CON DFS 1
    public static boolean canFinishDegree(int N, int[][]correlativas){
        GraphGonza graphGonza = new GraphGonza(true);
        for(int i=1; i<= N; i++){
            graphGonza.addNode(String.valueOf(i));
        }
        for(int[] correlative : correlativas){
            graphGonza.addEdge(String.valueOf(correlative[0]),String.valueOf(correlative[1]));
        }

        for(String nodeLabel : graphGonza.nodes.keySet()){
            if(findCycle(graphGonza.nodes.get(nodeLabel))){
                return false;
            }
        }
        return true;
    }

    private static boolean findCycle(GraphGonza.Node node){
        for (GraphGonza.Edge edge : node.edges) {
            if(edge.target.visited)
                return true;
            edge.target.visited= true;
            if(findCycle(edge.target))
                return true;
            edge.target.visited=false;
        }
        return  false;
    }
    //Opcion DFS
    //Esta es una optimizacion del anterior con el grado de incidencia
    public static boolean canFinishDegreeDFS(int N, int[][]correlativas){
        GraphGonza graphGonza = new GraphGonza(true);
        for(int i=1; i<= N; i++){
            graphGonza.addNode(String.valueOf(i));
        }
        for(int[] correlative : correlativas){
            graphGonza.addEdge(String.valueOf(correlative[0]),String.valueOf(correlative[1]));
        }
        Map<String,Integer> inDegrees= new HashMap<>();

        for(String nodeLabel : graphGonza.nodes.keySet()){
           GraphGonza.Node node = graphGonza.nodes.get(nodeLabel);
            for (GraphGonza.WeightedEdge edge : node.edges) {
                int currentDegree = inDegrees.getOrDefault(edge.target.label,1);
                inDegrees.put(edge.target.label,currentDegree+1);
            }
        }

        for(String nodeLabel: graphGonza.nodes.keySet()){
            if(inDegrees.getOrDefault(nodeLabel,0)>0){
                continue;
            }
            GraphGonza.Node node = graphGonza.nodes.get(nodeLabel);
            node.visited= true;
            if(findCycle(node)){
                return false;
            }
            node.visited=true;
        }
        return true;
    }

    // CON BFS eliminamos nodos con incidencia 0 hasta que no haya ninguno
    public static boolean canFinishDegreeBFS(int N, int[][]correlativas) {
        Map<String,Integer> inDegrees= new HashMap<>();

        GraphGonza graphGonza = new GraphGonza(true);
        for(int i=1; i<= N; i++){
            String label =String.valueOf(i);
            graphGonza.addNode(label);
            inDegrees.put(label,0);
        }
        for(int[] correlative : correlativas){
            String from=String.valueOf(correlative[0]);
            String to = String.valueOf(correlative[1]);
            graphGonza.addEdge(from,to);

            Integer inDegree= inDegrees.get(to);
            int newInDegree = inDegree+1;
            inDegrees.put(to,newInDegree);
        }

        //Nodes to check ¿ nodes not in Cycle
        Queue<String> nodesToCheck = new LinkedList<>();
        for(Map.Entry<String,Integer> entry: inDegrees.entrySet()){
            if(entry.getValue()==0){
                String nodeLabel = entry.getKey();
                nodesToCheck.add(nodeLabel);
            }
        }
        while(!nodesToCheck.isEmpty()){
            String nodeLabel = nodesToCheck.remove();
            GraphGonza.Node node =  graphGonza.nodes.get(nodeLabel);
            for(GraphGonza.Edge edge : node.edges){
                //Resto 1 a todos los ejes destino del nodo
                int inDegree=inDegrees.get(edge.target.label);
                int newInDegree=inDegree-1;
                inDegrees.put(edge.target.label,newInDegree);
                if(newInDegree==0)
                    nodesToCheck.add(edge.target.label);

            }
        }
        //Revisar si quedo alguno con incidencia mayor a 0
        for(Integer inDegree: inDegrees.values()){
            if(inDegree>0)
                return false;
        }
        return true;
    }
}
