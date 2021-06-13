import java.util.Scanner;


public class ExpTree {
	
	private Node root;

	public ExpTree() {
	    System.out.print("Introduzca la expresion en notacion infija con todos los parentesis y blancos: ");

		// token analyzer
	    Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
	    String line= inputScanner.nextLine();
	    inputScanner.close();

	    buildTree(line);
	}
	
	private void buildTree(String line)
	{	
		  // space separator among tokens
		  Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
		  root= new Node(lineScanner);
		  lineScanner.close();
	}
    
	
	

	static final class Node {
		private String data;
		private Node left, right;
		
		private Scanner lineScanner;

		public Node(Scanner theLineScanner) {
			lineScanner= theLineScanner;
			
			Node auxi = buildExpression();
			data= auxi.data;
			left= auxi.left;
			right= auxi.right;
			
			if (lineScanner.hasNext() ) 
				throw new RuntimeException("Bad expression");
		}
		
		private Node() 	{
		}
		public Double eval() {
            		if ( Utils.isConstant(data) )
                		return Utils.getDoubleConstant(data);
            			// No hace falta atrapar la excepcion, ya esta validado
            			return Utils.applyOperation(data, left.eval(), right.eval());
        	}
		
		
	
		private Node buildExpression() 	{
		    Node n= new Node();
		    if(lineScanner.hasNext("\\(")) {
                lineScanner.next(); //lo consumo

                n.left = buildExpression();
                //operator
                if (!lineScanner.hasNext())
                    throw new UnsupportedOperationException("Missing or invalid operator");
                //subexpression
                n.right = buildExpression();
                //) expected
                if (lineScanner.hasNext("\\)")) {
                    lineScanner.next();//lo consumo
                } else {
                    throw new IncorrectParenthesisException("missing )");
                }
                return n;
            }
		    //constant
            if(!lineScanner.hasNext())
                throw new IncorrectParenthesisException("missing expression");
            n.data= lineScanner.next();
            if(!Utils.isConstant(n.data)){
                throw new OperandException(String.format("illegal term %s",lineScanner));
            }
            return n;
		}
		
			
	}  // end Node class
	
}  // end ExpTree