import java.util.LinkedList;

public class InfijaToPosfijaMain {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        while(true) {
            try {
                //System.out.print("Enter post-fixed expression: ");
                InfijaToPosfija evaluator = new InfijaToPosfija();

                double ans = evaluator.evaluate();
                System.out.printf("Answer: %.4f \n", ans);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
