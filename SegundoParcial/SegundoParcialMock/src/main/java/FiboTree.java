public class FiboTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public FiboTree(int n) {
        if(n<0)
            throw new IllegalArgumentException();
       root= fiboTreeRec(n);
    }
    private Node fiboTreeRec(int n){
        if(n==0)
            return null;
        if(n==1){
            return new Node("*");
        }
        Node node = new Node("*");
        node.left= fiboTreeRec(n-1);
        node.right= fiboTreeRec(n-2);
        return node;
    }




    private class Node implements NodeFiboInterface{
        private String data;
        private Node left;
        private Node right;
        Node(String data){
            this.data=data;
        }
        @Override
        public String getData() {
            return data;
        }

        @Override
        public NodeFiboInterface getLeft() {
            return left;
        }

        @Override
        public NodeFiboInterface getRight() {
            return right;
        }
    }
}
