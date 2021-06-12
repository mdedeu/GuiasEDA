import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class InfijaToPosfija {

    Stack<Double> stack;
    private static Map<String, Double> vbles = new HashMap<String, Double>() {
        {
            put("nro1", 0.2);
            put("x", -2.0);
            put("y", 2.0);
        }
    };

    public InfijaToPosfija() {
        stack = new Stack<>();
    }

    public double evaluate() {
        System.out.println("Introduzca la expresión en notación infija: ");
        Scanner postFijaScanner = infijaToPosfija();
        if(!postFijaScanner.hasNext()) {
            throw new RuntimeException("No hay nada para evaluar");
        }

        while (postFijaScanner.hasNext()) {
            String token = postFijaScanner.next();
            if (token.matches("(-?)[0-9]+(\\.?([0-9]+)*)")) {
                stack.push(Double.parseDouble(token));
            }
            else if (token.matches("\\*?|-?|\\+?|/?|\\^?")) {
                if(stack.isEmpty())
                    throw new RuntimeException("Missing operand");
                double var2 = stack.pop();
                if(stack.isEmpty())
                    throw new RuntimeException("Missing operand");
                double var1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(var1 + var2);
                        break;
                    case "-":
                        stack.push(var1 - var2);
                        break;
                    case "/":
                        stack.push(var1 / var2);
                        break;
                    case "*":
                        stack.push(var1 * var2);
                        break;
                    case "^":
                        stack.push(Math.pow(var1, var2));
                }
            }
            else
                throw new RuntimeException("Invalid operator" + token);
        }
        if (!stack.isEmpty()) {
            double toReturn = stack.pop();
            if(!stack.isEmpty())
                throw new RuntimeException("Missing operator");

            return toReturn;
        }
        return Double.MAX_VALUE;
    }

    private Scanner infijaToPosfija() {
        Scanner s = new Scanner(System.in).useDelimiter("\\n");
        String ss = s.nextLine();
        Scanner scannerLine = new Scanner(ss).useDelimiter(("\\s+"));

        Stack<String> stack = new Stack<>();
        StringBuilder output = new StringBuilder();

        while(scannerLine.hasNext()) {
            String currentToken = scannerLine.next();

            if (isVble(currentToken)) {
                output.append(String.format("%s ", vbles.get(currentToken.toString())));
            } else if(isOperand(currentToken)) {
                output.append(String.format("%s ", currentToken));
            } else {
                while(!stack.isEmpty() && getPrecedence(stack.peek(), currentToken)) {
                    output.append(String.format("%s ", stack.pop()));
                }

                if(currentToken.equals(")")) {
                    //Quedo un ( en la pila
                    if(!stack.isEmpty() && stack.peek().equals("(")) {
                        stack.pop();
                    } else {
                        throw new RuntimeException("Missing (");
                    }
                } else {
                    stack.push(currentToken);
                }
            }
        }
        while(!stack.isEmpty()) {
            if(stack.peek().equals("(")) {
                throw new RuntimeException("Missing )");
            } else {
                output.append(String.format("%s ", stack.pop()));
            }
        }
        System.out.println(output.toString());
        return new Scanner(output.toString());
    }

    private boolean isOperand(String string) {
        boolean numeric = true;
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
    }

    private boolean isVble(String string) {
        //return vbles.get(string) != null;
        return string.matches("[A-Za-z][A-Za-z0-9]*");
    }

    private static final Map<String, Integer> mapping = new HashMap<String, Integer>() {
        {
            put("+", 0);
            put("-", 1);
            put("*", 2);
            put("/", 3);
            put("^", 4);
            put("(", 5);
            put(")", 6);
        }
    };

    private static final boolean[][] precedenceMatrix = {
            // +     -      *     /      ^      (     )
            {true, true, false, false, false, false, true},
            {true, true, false, false, false, false, true},
            {true, true, true, true, false, false, true},
            {true, true, true, true, false, false, true},
            {true, true, true, true, false, false, true},
            {false, false, false, false, false, false, false},
            {true, true, true, true, true, false, false}
    };

    private boolean getPrecedence(String top, String current) {
        Integer topIndex;
        Integer currentIndex;
        if((topIndex = mapping.get(top)) == null) {
            throw new RuntimeException(String.format("Top operator %s not found", top));
        }
        if((currentIndex = mapping.get(current)) == null) {
            throw new RuntimeException(String.format("Top operator %s not found", current));
        }
        return precedenceMatrix[topIndex][currentIndex];
    }
}
