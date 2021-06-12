import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;


public class BinaryTree{
	
	private Node root;

	private Scanner inputScanner;

	public BinaryTree(String fileName) throws FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		 InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);

		 inputScanner = new Scanner(is);
		 inputScanner.useDelimiter("\\s+");

		buildTree();
		
		 inputScanner.close();
	}
	boolean equalsBinaryTree(BinaryTree other){
		return other.preorder().equals(preorder());
	}
	void toFile(String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.write(preorder());
		printWriter.close();
	}

	
	private void buildTree() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Queue<NodeHelper> pendingOps= new LinkedList<>();
		String token;


		root= new Node();
		pendingOps.add(new NodeHelper(root, (Node n)->(n) ));

		while(inputScanner.hasNext()) {

			token= inputScanner.next();
			NodeHelper aPendingOp = pendingOps.remove();
			Node currentNode = aPendingOp.getNode();

			if ( token.equals("?") ) {
				// no hace falta poner en null al L o R porque ya esta con null

				// reservar el espacio aunque NULL no tiene hijos
				pendingOps.add( new NodeHelper(null, (Node n)->(n) ) );  // como si hubiera izq. o null
				pendingOps.add( new NodeHelper(null, (Node n)->(n) ) );  // como si hubiera der. o null

			}
			else {
				Function<Node, Node> anAction= aPendingOp.getAction();
				currentNode= anAction.apply(currentNode);

				// armo la info del izq, der o el root
				currentNode.data= token;


				// hijos se postergan

				// version lambda
				pendingOps.add(new NodeHelper(currentNode, (Node n)->(n.setLeftTree(new Node() )) ));

				// version clase anonima
				pendingOps.add(new NodeHelper(currentNode,
						new Function<Node, Node>() {
							@Override
							public Node apply(Node t) {
								return t.setRightTree(new Node());
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
	public int getHeightRec(Node root,int maxHeight){
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
		Stack<Node> nodeStack = new Stack<Node>();
		nodeStack.push(root);
		String ans="";
		if(root==null)
			return ans;

		while (!nodeStack.empty()) {
			Node mynode = 	nodeStack.pop();
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
	private void printHierarchyFrom(Node root){
		if(root==null)
			System.out.println("\t└── "+"null");
		else{
			System.out.println("\t└── "+root.data);
			printHierarchyFrom(root.left);
			printHierarchyFrom(root.right);
		}
	}


	


	// hasta el get() no se evalua
	class Node {
		public String data;
		public Node left;
		public Node right;
		
		public Node setLeftTree(Node aNode) {
			left= aNode;
			return left;
		}
		
		
		public Node setRightTree(Node aNode) {
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


	 class NodeHelper {

		private Node aNode;
		private Function<Node, Node> anAction;

		public NodeHelper(Node aNode, Function<Node, Node>  anAction ) {
			this.aNode= aNode;
			this.anAction= anAction;
		}


		public Node getNode() {
			return aNode;
		}

		public Function<Node, Node> getAction() {
			return anAction;
		}

	}
	

	
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		BinaryTree rta = new BinaryTree("data0_3");
		System.out.println(rta.getHeight());
		rta.toFile("texto");
			
	}

}  