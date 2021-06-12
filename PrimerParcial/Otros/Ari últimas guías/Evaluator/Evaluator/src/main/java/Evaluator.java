import java.util.Scanner;
import java.util.Stack;

public class Evaluator {

    public Double evaluate(){
        //estamos leyendo de la entrada stardar
        // antes lo haciamos al crear la clase. Como solo puedo usar evaluator,
        // es lo mismo pero sin gurdar el scanner como variable global
        System.out.print("Introduzca la expresión en notación infija: ");
        Scanner scanner= new Scanner(infijaToPostfija());
        if (! scanner.hasNext())
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
        return s.matches("\\+|-|\\*|/");
    }

    private Double eval(String operand, Double o1, Double o2){
        return OPERANDS.match(operand).getFunction().apply(o1, o2);
    }

    private String infijaToPostfija(){
        Scanner s = new Scanner(System.in).useDelimiter("\\n");
        String ss= s.nextLine();
        Scanner scannerLine = new Scanner(ss).useDelimiter("\\s+");

        Stack<String> operatorStack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        while(scannerLine.hasNext()) {
            String current = scannerLine.next();
            if (isOperand(current))
                builder.append(current).append(" ");
            else if(isOperator(current)){
                while (!operatorStack.isEmpty() && pred(current, operatorStack.peek())) {
                    builder.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(current);
            }
        }
        while (!operatorStack.isEmpty())
            builder.append(operatorStack.pop()).append(" ");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private boolean pred(String s, String peek){
        return OPERANDS.match(s).getPred() <= OPERANDS.match(peek).getPred();
    }

    public static void main(String[] args) {
        System.out.println(new Evaluator().evaluate());
    }
}
