import java.util.Map;

public class DenseGraphGonza {

    static final int CHUNK_SIZE=10;

    Map<String,Integer> indexes;
    int[][]matrix;
    boolean isDirected;
    int size;
    public DenseGraphGonza(boolean isDirected){
        this.isDirected=isDirected;
        matrix = new int[0][0];
    }
    void addNode(String label){
        if(indexes.containsKey(label)){
            return;
        }
        checkMatrixSize();
        indexes.put(label,size);
        size++;
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
    void removeNode(String label){

    }
    void addEdge(String label1, String label2){
        addNode(label1);
        addNode(label2);
        int index1= indexes.get(label1);
        int index2 = indexes.get(label2);
        matrix[index1][index2]=1;
        if(!isDirected)
            matrix[index2][index1]=1;
    }
    void printBfs(String startingLabel){

    }
    int nodeCount(){
        return size;
    }
}
