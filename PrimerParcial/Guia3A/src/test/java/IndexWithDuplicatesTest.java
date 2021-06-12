import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IndexWithDuplicatesTest {


    @Test
    void closestPosition() {
        IndexWithDuplicates myIndex = new IndexWithDuplicates();
        myIndex.initialize(new int[]{2,10,20,30,40,50,60,70});
        Assertions.assertEquals(0, myIndex.getClosestPosition(1));
        Assertions.assertEquals(1, myIndex.getClosestPosition(5));
        Assertions.assertEquals(2, myIndex.getClosestPosition(15));
        Assertions.assertEquals(3, myIndex.getClosestPosition(25));
        Assertions.assertEquals(4, myIndex.getClosestPosition(35));
        Assertions.assertEquals(5, myIndex.getClosestPosition(45));
        Assertions.assertEquals(6, myIndex.getClosestPosition(55));
        Assertions.assertEquals(7, myIndex.getClosestPosition(65));
        Assertions.assertEquals(8, myIndex.getClosestPosition(75));



    }
}
