import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DenseGraph<V> implements GraphService<V> {

    static final int CHUNK_SIZE=10;
    Map<V,Integer> indexes;
    int[][]matrix;
    boolean isDirected;
    int size;

    public DenseGraph(boolean isDirected){
        this.isDirected=isDirected;
        matrix = new int[0][0];
    }
    private void checkMatrixSize(){
        if(size== matrix.length){
            int[][] newMatrix= new int[size + CHUNK_SIZE][size+CHUNK_SIZE];
            for(int i=0; i<matrix.length; i++){
                for(int j=0; j< matrix[i].length; j++){
                    newMatrix[i][j] = matrix[i][j];
                }
            }
            matrix = newMatrix;

        }
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean addVertex(V element) {
      if(indexes.containsKey(element)){
            return false;
        }
        checkMatrixSize();
        indexes.put(element,size);
        size++;
        return true;
    }

    @Override
    public boolean removeVertex(V element) {
        Integer removed= indexes.remove(element);
        size--;
        return removed!=null;
    }

    @Override
    public boolean addEdge(V element1, V element2) {
        addVertex(element1);
        addVertex(element2);
        int index1= indexes.get(element1);
        int index2 = indexes.get(element2);
        matrix[index1][index2]=1;
        if(!isDirected)
            matrix[index2][index1]=1;
        return true;
    }

    @Override
    public boolean removeEdge(V element1, V element2) {
        int index1=indexes.getOrDefault(element1,-1);
        int index2=indexes.getOrDefault(element2,-1);
        if(index1==-1 || index2==-1)
            return false;
        matrix[index1][index2]=0;
        if(!isDirected){
            matrix[index2][index1]=0;
        }
        return true;
    }

    @Override
    public void dump() {

    }

    @Override
    public int vertexesSize() {
        return 0;
    }

    @Override
    public int edgesSize() {
        return 0;
    }

    @Override
    public int degree(V node) {
     return 0;
    }

    @Override
    public Iterable<V> getVertices() {
        return null;
    }

    @Override
    public void printBFS(V startNode) {

    }

    @Override
    public void printRecursiveDFS(V startNode) {

    }

    @Override
    public void printDFS(V startNode) {

    }


    @Override
    public Iterable<V> getBFS(V startNode) {
        return null;
    }

    @Override
    public Iterable<V> getDFS(V startNode) {
        return null;
    }

    int connectedComponents() {
        int components = 0;
        Set<V> marked = new HashSet<>();
        for ( V v: getVertices() ) {
            if ( ! marked.contains(v) ) {
                components ++;
                for ( V n: getDFS(v) )
                    marked.add(n);
            }
        }
        return components;
    }
}
