import java.util.function.BinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.Predicate;

public enum OPERANDS {
    SUMA(Double::sum, 1),
    RESTA((o1, o2) -> o1 - o2, 1),
    MULTI((o1, o2) -> o1*o2, 2),
    DIV((o1, o2) -> o1/o2, 2);

    private final BinaryOperator<Double> function;
    private final int pred;

    OPERANDS(BinaryOperator<Double> function, int pred) {
        this.function = function;
        this.pred = pred;
    }

    public BinaryOperator<Double> getFunction() {
        return function;
    }

    public int getPred() {
        return pred;
    }

    public static OPERANDS match(String operand){
        switch (operand) {
            case "+":
                return SUMA;
            case "-":
                return RESTA;
            case "*":
                return MULTI;
        }
        return DIV;
    }
}
