import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TestEj7 {
    @Test
    public void testAmountOperators(){
        Bag<Integer> bag= new HashBag<>();
        bag.add(2);
        bag.add(3);
        assertEquals(1, bag.getCount(2));
        bag.remove(2);
        bag.remove(4);
        assertEquals(0,bag.getCount(2));
        bag.add(10);
        bag.add(10);
        bag.add(10);
        bag.remove(10,1);
        assertEquals(2,bag.getCount(10));


    }
}
