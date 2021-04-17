

public class EvaluatorTest {
    public static void main(String[] args) {
        while(true) {
            try {
                System.out.print("Enter post-fixed expression: ");
                Evaluator evaluator = new Evaluator();
                double ans = evaluator.evaluate();
                System.out.printf("Answer: %.4f \n", ans);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
