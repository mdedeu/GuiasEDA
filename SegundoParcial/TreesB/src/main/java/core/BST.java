package core;

import java.util.*;
enum Traversal {
    BYLEVEL,
    INORDER,
}
public class BST<T extends Comparable<? super T>> implements  BSTreeInterface<T>{
    NodeTreeInterface<T> root=null;
    Traversal traversal;
    @Override
    public void insert(T myData) {
       root=insertRec(myData,(Node) root);
    }
    private Node insertRec(T myData,Node starting){
        if(starting==null){
            return new Node(myData);
        }

        int cmp=starting.getData().compareTo(myData);

        if(cmp>0){
            starting.left=insertRec(myData,starting.left);
        }else{
            starting.right=insertRec(myData,starting.right);
        }
        return starting;
    }
    @Override
    public void preOrder() {
            Stack<NodeTreeInterface<T>> nodeStack = new Stack<>();
            nodeStack.push(root);
            if(root==null)
                return;
            while (!nodeStack.empty()) {
                NodeTreeInterface<T> mynode = 	nodeStack.pop();
                System.out.print(mynode.getData() + " ");

                if (mynode.getRight() != null) {
                    nodeStack.push(mynode.getRight());
                }
                if (mynode.getLeft() != null) {
                    nodeStack.push(mynode.getLeft());
                }
            }
    }
    public void preOrderRec(Node startingNode){
        if(startingNode==null)
            return;
        System.out.print(startingNode.data+ " ");
        preOrderRec(startingNode.left);
        preOrderRec(startingNode.right);
    }

    @Override
    public void postOrder() {
        postOrderRec(root);
    }
    private void postOrderRec(NodeTreeInterface<T> startingNode){
        if(startingNode==null)
            return;
        postOrderRec(startingNode.getLeft());
        postOrderRec(startingNode.getRight());
        System.out.print(startingNode.getData()+ " ");
    }
    @Override
    public void inOrder() {
        inOrderRec(root);
    }
    private void inOrderRec(NodeTreeInterface<T> startingNode){
        if(startingNode==null)
            return;
        inOrderRec(startingNode.getLeft());
        System.out.print(startingNode.getData()+ " ");
        inOrderRec(startingNode.getRight());
    }
    // version iterativa
    public void inOrderIter() {
        Stack<NodeTreeInterface<T>> stack= new Stack<>();
        NodeTreeInterface<T> current = root;
        while ( ! stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current= current.getLeft();
            }
            else {
                NodeTreeInterface<T> elementToProcess = stack.pop();
                System.out.print(elementToProcess.getData() + "\t");
                current= elementToProcess.getRight();
            }
        }
    }
    @Override
    public NodeTreeInterface<T> getRoot() {
        return root;
    }
    @Override
    public int getHeight(){
        return getHeightRec(root,0);
    }

    @Override
    public void printByLevels() {
      if(root==null)
          return;
      Queue<NodeTreeInterface<T>> queue= new LinkedList<>();
      queue.add(root);
      NodeTreeInterface<T> currentNode;

      while(!queue.isEmpty()){
          currentNode= queue.remove();
          System.out.print(currentNode.getData()+ " ");
          if(currentNode.getLeft()!=null){
              queue.add(currentNode.getLeft());
          }
          if(currentNode.getRight()!=null){
              queue.add(currentNode.getRight());
          }
      }
        System.out.println();
    }



    public int getHeightRec(NodeTreeInterface<T> root, int maxHeight){
        if(root==null)
            return -1;
        if(root.getRight()== null && root.getLeft()==null)
            return 0;

        int right=1+getHeightRec(root.getRight(),maxHeight);
        int left= 1+getHeightRec(root.getLeft(),maxHeight);
        maxHeight = Math.max(right, left);
        return maxHeight;
    }

    @Override
    public Iterator<T> iterator() {
        if(traversal==Traversal.INORDER){
            return new BSTinOrderIterator();
        }else{
            return new BSTByLevelIterator();
        }
    }
    class BSTByLevelIterator implements Iterator<T>{
        private Queue<NodeTreeInterface<T>> queue;
        private BSTByLevelIterator() {
            // create an empty queue and enqueue the root node
            queue = new LinkedList<>();
            if (root!= null)
                queue.add(root);
        }
        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
        @Override
        public T next() {
            NodeTreeInterface<T> currentNode = queue.remove();
            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
            return currentNode.getData();
        }
    }
    class BSTinOrderIterator implements  Iterator<T>{
        private Stack<NodeTreeInterface<T>> stack;
        private NodeTreeInterface<T> current;
        private BSTinOrderIterator(){
            stack=new Stack<>();
            current=root;
        }
        @Override
        public boolean hasNext() {
            return (!stack.isEmpty() || current!=null);
        }
        @Override
        public T next() {
                if(current!=null){
                    stack.push(current);
                    current=current.getLeft();
                }
                else{
                    NodeTreeInterface<T> elementToProcess= stack.pop();
                    current=elementToProcess.getRight();
                    return elementToProcess.getData();
                }
                return null;
        }
    }
    class Node implements NodeTreeInterface<T>{
        T data;
        Node left;
        Node right;
        Node(T myData){
            data=myData;
        }

        @Override
        public T getData() {
            return data;
        }

        @Override
        public NodeTreeInterface<T> getLeft() {
            return left;
        }

        @Override
        public NodeTreeInterface<T> getRight() {
            return right;
        }
        private boolean isLeaf() {
            return left == null && right == null;
        }
    }

    public static void main(String[] args) {

        /*BST<Integer> myTree = new BST<>();
        myTree.insert(50);
        myTree.insert(60);
        myTree.insert(80);
        myTree.insert(20);
        myTree.insert(70);
        myTree.insert(40);
        myTree.insert(44);
        myTree.insert(10);
        myTree.insert(40);
        myTree.inOrder();
        System.out.println(" ");
        myTree.preOrder();
        System.out.println(" ");
        myTree.postOrder();
        myTree.printByLevels();
*/
        BST<Integer> myTree2 = new BST<>();
        myTree2.insert(35);
        myTree2.insert(74);
        myTree2.insert(20);
        myTree2.insert(22);
        myTree2.insert(55);
        myTree2.insert(15);
        myTree2.insert(8);
        myTree2.insert(27);
        myTree2.insert(25);
        for (Integer data : myTree2) {
            System.out.print(data + " ");
        }
        // Puedo hacerlo múltiples veces…
        System.out.println("\n\nUna vez más…\n");
        myTree2.forEach( t-> System.out.print(t + " ") );
    }
}
