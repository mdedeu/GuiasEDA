import java.util.ArrayList;

public class SacaRepetidos<T extends Comparable<T>> {
    ArrayList<T> sacaRepetidos(ArrayList<T> input) {
        AVLTree tree = new AVLTree();
        for (T element : input) {
            tree.root = tree.insert(tree.root, element);
        }
        ArrayList<T> ans= new ArrayList<>();
        tree.inOrder(ans);
        return ans;
        //recorrer arbol y meterlo en nueva lista


    }

    // Java program for insertion in AVL Tree
    static class Node<T> {
        T key;
        int height;
        Node<T> left, right;

        Node(T d) {
            key = d;
            height = 1;
        }
    }

    class AVLTree {

        Node<T> root;

        // A utility function to get the height of the tree
        int height(Node<T> N) {
            if (N == null)
                return 0;

            return N.height;
        }

        // A utility function to get maximum of two integers
        int max(int a, int b) {
            return Math.max(a, b);
        }

        // A utility function to right rotate subtree rooted with y
        // See the diagram given above.
        Node<T> rightRotate(Node<T> y) {
            Node<T> x = y.left;
            Node<T> T2 = x.right;

            // Perform rotation
            x.right = y;
            y.left = T2;

            // Update heights
            y.height = max(height(y.left), height(y.right)) + 1;
            x.height = max(height(x.left), height(x.right)) + 1;

            // Return new root
            return x;
        }

        // A utility function to left rotate subtree rooted with x
        // See the diagram given above.
        Node<T> leftRotate(Node<T> x) {
            Node<T> y = x.right;
            Node<T> T2 = y.left;

            // Perform rotation
            y.left = x;
            x.right = T2;

            //  Update heights
            x.height = max(height(x.left), height(x.right)) + 1;
            y.height = max(height(y.left), height(y.right)) + 1;

            // Return new root
            return y;
        }

        // Get Balance factor of node N
        int getBalance(Node<T> N) {
            if (N == null)
                return 0;

            return height(N.left) - height(N.right);
        }

        Node<T> insert(Node<T> node, T key) {

            /* 1.  Perform the normal BST insertion */
            if (node == null)
                return (new Node<>(key));
            int cmp = key.compareTo(node.key);

            if (cmp < 0)
                node.left = insert(node.left, key);
            else if (cmp > 0)
                node.right = insert(node.right, key);
            else // Duplicate keys not allowed
                return node;

            /* 2. Update height of this ancestor node */
            node.height = 1 + max(height(node.left),height(node.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
            int balance = getBalance(node);
            // If this node becomes unbalanced, then there
            // are 4 cases Left Left Case
            if (balance > 1 && key.compareTo(node.left.key) < 0)
                return rightRotate(node);
            // Right Right Case
            if (balance < -1 && key.compareTo(node.right.key) > 0)
                return leftRotate(node);

            // Left Right Case
            if (balance > 1 && key.compareTo(node.left.key)  > 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // Right Left Case
            if (balance < -1 && key.compareTo(node.right.key) < 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            /* return the (unchanged) node pointer */
            return node;
        }

        public void inOrder(ArrayList<T> list) {
            inOrderRec(root,list);
        }
        private void inOrderRec(Node<T> startingNode,ArrayList<T> list){
            if(startingNode==null)
                return;
            inOrderRec(startingNode.left,list);
            list.add(startingNode.key);
            inOrderRec(startingNode.right,list);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList= new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        SacaRepetidos<Integer> sacaRepetidos= new SacaRepetidos<>();
        ArrayList<Integer> ans= sacaRepetidos.sacaRepetidos(arrayList);
    }
}
