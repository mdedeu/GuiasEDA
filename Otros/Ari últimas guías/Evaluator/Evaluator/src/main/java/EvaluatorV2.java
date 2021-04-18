import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class EvaluatorV2 {

    private static Map<String, Boolean> precedenceMap = new HashMap<String, Boolean>(){	{

        put("+_+", true); put("+_-", true); put("+_*", false); put("+_/", false); put("+_^", false); put("+_(", false); put("+_)", true);

        put("-_+", true); put("-_-", true); put("-_*", false); put("-_/", false); put("-_^", false); put("-_(", false); put("-_)", true);

        put("*_+", true); put("*_-", true); put("*_*", true); put("*_/", true); put("*_^", false); put("*_(", false); put("*_)", true);

        put("/_+", true); put("/_-", true); put("/_*", true); put("/_/", true); put("/_^", false); put("/_(", false); put("/_)", true);

        put("^_+", true); put("^_-", true); put("^_*", true); put("^_/", true); put("^_^", false); put("^_(", false); put("^_)", true);

        put("(_+", false); put("(_-", false); put("(_*", false); put("(_/", false); put("(_^", false); put("(_(", false); put("(_)", false);

    } };

    private final Map<String, Double> variables;

    public EvaluatorV2(){
        this.variables = null;
    }

    public EvaluatorV2(HashMap<String, Double> variables) {
        this.variables = variables;
    }

    public Double evaluate(){
        //estamos leyendo de la entrada stardar
        // antes lo haciamos al crear la clase. Como solo puedo usar evaluator,
        // es lo mismo pero sin gurdar el scanner como variable global
        System.out.print("Introduzca la expresión en notación infija: ");
        Scanner scanner= new Scanner(infijaToPostfija());
        if (!scanner.hasNext())
            throw new RuntimeException("no hay nada para evaluar");

        // stack para ir guardando los resultados
        Stack<Double> stack = new Stack<>();
        while (scanner.hasNext()){
            String token = scanner.next();

            if (isOperand(token))
                stack.push(Double.valueOf(token));
            else {
                //si no es un operando ni un operador, entonces esta mal
                if (!isOperator(token) )
                    throw new RuntimeException("invalid operator " + token);

                //tengo que hacer los pops con cuidado
                Double operand2;
                if (stack.empty())
                    throw new RuntimeException("operand missing");
                else
                    operand2= stack.pop();

                Double operand1;
                if (stack.empty())
                    throw new RuntimeException("operand missing");
                else
                    operand1= stack.pop();

                //luego de popear los dos operandos, hacer la operacion
                stack.push(eval(token, operand1 , operand2) );
            }
        }
        Double toReturn = stack.pop();
        //si no es empty me falto algo para que me quede el resultado final
        if(!stack.empty())
            throw new IllegalArgumentException("falta operador");
        return toReturn;
    }

    private boolean isOperand(String s){
        //si lo puedo tranformar en Double, es un numero
        try
        {
            Double.valueOf(s);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    private boolean isOperator(String s){
        return s.matches("\\+|-|\\*|/|\\^");
    }

    private boolean isVariable(String s){
        if (variables != null) {
            return variables.containsKey(s);
        }
        else
            return false;
    }

    private Double eval(String operand, Double o1, Double o2){
        switch (operand) {
            case "+":
                return o1 + o2;
            case "-":
                return o1 - o2;
            case "*":
                return o1 * o2;
            case "^":
                return Math.pow(o1, o2);

        }
        return o1 / o2;
    }

    public String infijaToPostfija(){
        Scanner s = new Scanner(System.in).useDelimiter("\\n");
        String ss= s.nextLine();
        Scanner scannerLine = new Scanner(ss).useDelimiter("\\s+");

        Stack<String> operatorStack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        int par = 0;
        while(scannerLine.hasNext()) {
            String current = scannerLine.next();
            if(isVariable(current))
                builder.append(variables.get(current)).append(" ");
            else if (isOperand(current))
                builder.append(current).append(" ");
            else {
                if(current.equals("("))
                    par++;
                if(current.equals(")"))
                    par --;
                while (!operatorStack.isEmpty() && pred(current, operatorStack.peek())) {
                    builder.append(operatorStack.pop()).append(" ");
                }
                if(current.equals(")") && !operatorStack.isEmpty() && operatorStack.peek().equals("("))
                    operatorStack.pop();
                else if(!current.equals(")"))
                    operatorStack.push(current);
            }
        }
        if(par < 0)
            throw new IllegalArgumentException("error falta (");
        else if(par > 0)
            throw new IllegalArgumentException("error falta )");
        while (!operatorStack.isEmpty()) {
            // si son parentesis no lo pongo en el string (solo me fijo "(" porque el otro no lo guarde en el stack)
            if (!operatorStack.peek().equals("(")) {
                builder.append(operatorStack.pop()).append(" ");
            }
            //si es parentesis necesito hacer el pop sin guardarlo
            else
                operatorStack.pop();
        }
        return builder.toString();
    }

    private boolean pred(String s, String peek){
        String precedence = peek + "_" + s;
        return precedenceMap.get(precedence);
    }

    public static void main(String[] args) {
        System.out.println(new EvaluatorV2().evaluate());
        EvaluatorV2 evaluatorV2 = new EvaluatorV2(new HashMap<String, Double>()
        {{  put("v1", 10.2);
            put("v3", 0.5);
        }});
        System.out.println(evaluatorV2.evaluate());
        System.out.println(evaluatorV2.evaluate());
        System.out.println(evaluatorV2.evaluate());
    }
}
