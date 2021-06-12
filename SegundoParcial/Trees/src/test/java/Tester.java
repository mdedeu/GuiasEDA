import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuildExpTree {
    @Test
    void testOK1() {
        String input= " ( 2 + 3 ) \n";
        ByteArrayInputStream inputstream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        ExpTree myTree = new ExpTree();
        assertNotNull(myTree);
        System.setIn(System.in);
    }
    @Test
    void testBadOperator() {
        String input= " ( 2 & 3 ) \n";
        ByteArrayInputStream inputstream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        Exception exception=
                assertThrows(RuntimeException.class, ExpTree::new);
        System.setIn(System.in);
    }
    @Test
    void testNotMatchingParenthesis(){
        String input= "( 2 + 3 ) ) \n";
        ByteArrayInputStream inputstream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        Exception exception=
                assertThrows(RuntimeException.class, ExpTree::new);
        System.setIn(System.in);
    }
    @Test
    void testNotUsedParenthesis(){
        String input= "( ( 2 + 3 ) ) \n";
        ByteArrayInputStream inputstream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        Exception exception=
                assertThrows(RuntimeException.class, ExpTree::new);
        System.setIn(System.in);
    }
    @Test
    void testOK2(){
        String input= "( ( 2 + 3.5 ) * ( -5 / -1 ) ) \n";
        ByteArrayInputStream inputstream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        ExpTree myTree = new ExpTree();
        assertNotNull(myTree);
        System.setIn(System.in);
    }
    @Test
    void testOK3(){
        String input= "( ( 2 + 3.5 ) * -10 ) \n";
        ByteArrayInputStream inputstream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        ExpTree myTree = new ExpTree();
        assertNotNull(myTree);
        System.setIn(System.in);
    }
    @Test
    void testCteNotNumber(){
        String input= " ( 2 * hola )  \n";
        ByteArrayInputStream inputstream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        Exception exception=
                assertThrows(RuntimeException.class, ExpTree::new);
        System.setIn(System.in);
    }

}


