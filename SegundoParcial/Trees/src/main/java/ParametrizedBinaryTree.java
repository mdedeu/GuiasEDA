import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

public class ParametrizedBinaryTree<T>{

    private Node<T> root;

    private final Scanner inputScanner;
    private final Class<? extends  T> theClass;

    public ParametrizedBinaryTree(String fileName, final Class<? extends T> theClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);

        inputScanner = new Scanner(is);
        inputScanner.useDelimiter("\\s+");

        buildTree();

        inputScanner.close();
        this.theClass=theClass;
    }
    boolean equalsParametrizedBinaryTree(ParametrizedBinaryTree<T> other){
        return other.preorder().equals(preorder());
    }
    void toFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.write(preorder());
        printWriter.close();
    }


    private void buildTree() throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
        Queue<NodeHelper> pendingOps= new LinkedList<>();
        String token;


        root= new Node<>();
        pendingOps.add(new NodeHelper(root, (Node<T> n)->(n) ));

        while(inputScanner.hasNext()) {

            token= inputScanner.next();
            NodeHelper aPendingOp = pendingOps.remove();
            Node<T> currentNode = aPendingOp.getNode();

            if ( token.equals("?") ) {
                // no hace falta poner en null al L o R porque ya esta con null

                // reservar el espacio aunque NULL no tiene hijos
                pendingOps.add( new NodeHelper(null, (Node<T> n)->(n) ) );  // como si hubiera izq. o null
                pendingOps.add( new NodeHelper(null, (Node<T> n)->(n) ) );  // como si hubiera der. o null

            }
            else {
                Function<Node<T>, Node<T>> anAction= aPendingOp.getAction();
                currentNode= anAction.apply(currentNode);

                // armo la info del izq, der o el root

                currentNode.data =(T)token;

                // hijos se postergan

                // version lambda
                pendingOps.add(new NodeHelper(currentNode, (Node<T> n)->(n.setLeftTree(new Node<T>() )) ));

                // version clase anonima
                pendingOps.add(new NodeHelper(currentNode,
                        new Function<Node<T>, Node<T>>() {
                            @Override
                            public Node<T> apply(Node<T> t) {
                                return t.setRightTree(new Node<>());
                            }}
                ));
            }

        }

        if (root.data == null)  // no entre al ciclo jamas
            root= null;

    }
    public int getHeight(){
        return getHeightRec(root,0);
    }
    public int getHeightRec(Node<T> root, int maxHeight){
        if(root==null)
            return -1;
        if(root.isLeaf())
            return 0;

        int right=1+getHeightRec(root.right,maxHeight);
        int left= 1+getHeightRec(root.left,maxHeight);
        maxHeight = Math.max(right, left);
        return maxHeight;
    }



    public String preorder() {
        Stack<Node<T>> nodeStack = new Stack<Node<T>>();
        nodeStack.push(root);
        String ans="";
        if(root==null)
            return ans;

        while (!nodeStack.empty()) {
           Node<T> mynode = 	nodeStack.pop();
            ans=ans.concat(mynode.data + " ");

            if (mynode.right != null) {
                nodeStack.push(mynode.right);
            }
            if (mynode.left != null) {
                nodeStack.push(mynode.left);
            }
        }
        return ans;
    }

    public void printHierarchy(){
        printHierarchyFrom(root);
    }
    private void printHierarchyFrom(Node<T> root){
        if(root==null)
            System.out.println("\t└── "+"null");
        else{
            System.out.println("\t└── "+root.data);
            printHierarchyFrom(root.left);
            printHierarchyFrom(root.right);
        }
    }





    // hasta el get() no se evalua
    class Node<B>{

        public B data;
        public Node<B> left;
        public Node<B> right;

        public Node<B> setLeftTree(Node<B> aNode) {
            left= aNode;
            return left;
        }


        public Node<B> setRightTree(Node<B> aNode) {
            right= aNode;
            return right;
        }



        public Node() {
            // TODO Auto-generated constructor stub
        }

        private boolean isLeaf() {
            return left == null && right == null;
        }


    }  // end Node class


    class NodeHelper{
        private final Node<T> aNode;
        private final Function<Node<T>,Node<T>> anAction;

        public NodeHelper(Node<T> aNode, Function<Node<T>,Node<T>>  anAction ) {
            this.aNode= aNode;
            this.anAction= anAction;
        }


        public Node<T> getNode() {
            return aNode;
        }

        public Function<Node<T>, Node<T>> getAction() {
            return anAction;
        }

    }



    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

        ParametrizedBinaryTree<Jefe> rta = new ParametrizedBinaryTree<>("jefe", Jefe.class);
        String ans=rta.preorder();
        System.out.println(ans);

        ParametrizedBinaryTree<String> rta1 = new ParametrizedBinaryTree<>("data0_3", String.class);
        ans= rta1.preorder();
        System.out.println(ans);

        ParametrizedBinaryTree<Jefe> rta2 = new ParametrizedBinaryTree<>("data1_1", Jefe.class);
        ans= rta2.preorder();
        System.out.println(ans);
    }
}
